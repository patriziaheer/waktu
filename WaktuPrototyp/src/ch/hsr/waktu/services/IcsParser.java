package ch.hsr.waktu.services;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.LinkedList;

import ch.hsr.waktu.domain.WorkSession;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QTime;

public class IcsParser {

	private static LinkedList<WorkSession> calendar = new LinkedList<WorkSession>();
	
	/**
	 * Parses contents of an .ics file which is used in iCal, Outlook, etc.
	 *
	 *
	 * ICS FILE FORMAT:
	 * <p>
	 * EXAMPLE WORKSESSION:
	 * BEGIN:VEVENT
	 * CREATED:20100809T062219Z
 	 * UID:2DE909B3-96C6-4D7B-BB99-5CA7487C43A2
	 * DTEND;TZID=Europe/Zurich:20100810T115000
	 * TRANSP:OPAQUE
	 * SUMMARY:Prog2
	 * DTSTART;TZID=Europe/Zurich:20100810T101000
	 * DTSTAMP:20100809T062304Z
	 * SEQUENCE:4
	 * END:VEVENT
	 * <p>
	 * An WorkSession starts with the tag "BEGIN:VEVENT" and should be closed by "END:VEVENT".
	 * A missing "END:VEVENT" could lead to missing WorkSessions as startTime and endTime might
	 * be overwritten.
	 * <p>
	 * Within a WorkSession the following tags may occur in any order:
	 * <ul>
	 * <li>CREATED: 		Creation date 
	 * <li>SUMMARY:* 		Title
	 * <li>DTEND;...:* 		End time
	 * <li>DTSTART;...:* 	Start time
	 * <li>SEQUENCE:		Indicates a recurring WorkSession						
	 * </ul>
	 * *: Mandatory tags
	 * <p>
	 * All other tags are ignored.
	 *
	 * @param  filePath path to the .ics file to parse.
	 * @return a linked list containing all WorkSessions extracted from input file.
	 */
	public static LinkedList<WorkSession> parseIcsFile(String filePath) {
		
		BufferedReader br;
		
		try {
			//TODO Limit file size?
			br = new BufferedReader(new FileReader(new File(filePath)));
						
			String currentLine;
			
			try {
				while((currentLine = br.readLine()) != null) {
					WorkSession tmpWs = null;

					
					if(currentLine.startsWith("BEGIN:VEVENT")) { 
						//Appointment begin
						tmpWs = new WorkSession();	
						
						while(!((currentLine = br.readLine()) == null || currentLine.startsWith("END:VEVENT"))) {
							if(currentLine.contains("SUMMARY:")) { 
								//title read
								tmpWs.setDescription(stripTag(currentLine));
									
							} else if(currentLine.startsWith("DTSTART;")) { 
								//start time read
								QDateTime startTime = stringToQDateTime(currentLine);
								tmpWs.setStart(TimeUtil.convertQDateTimeCalToGregorianCal(startTime));
								
							} else if(currentLine.startsWith("DTEND;")) { 
								//end time read
								QDateTime endTime = stringToQDateTime(currentLine);
								tmpWs.setEnd(TimeUtil.convertQDateTimeCalToGregorianCal(endTime));
							}
						} 
						//Appointment end
						calendar.add(tmpWs);
					}

					//TODO Sequence Tag
									
					//Unused Tags
//					if(currentLine.startsWith("CREATED:")) {
//						
//					}
//					
//					if(currentLine.startsWith("UID:")) {
//						
//					}
//					
//					if(currentLine.startsWith("TRANSP:")) {
//						
//					}
//					
//					if(currentLine.startsWith("DTSTAMP:")) {
//						
//					}
//					
//					if(currentLine.startsWith("SEQUENCE:")) {
//						
//					}
					
				}
			} catch(Exception e) {
				
			}
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("IO Exception occurred");
			}
		} catch(FileNotFoundException e) {
			System.out.println("File " + filePath + " was not found");
		}

		return calendar;
	}

	/**
	 * Converts an .ics dateTime string into a QDateTime object
	 *
	 * The format of the input dateTimeString is yyyyMMddThhmmss, where yyyy denotes the year,
	 * MM the month, dd the day, 'T' a delimiter, hh the hour, mm the minutes, ss the seconds.
	 * Failing to pass the dateTimeString in this format will result in undesirable DateTime Objects 
	 * or a @throws IndexOutOfBoundsException may be thrown in case the dateTimeString is too short.
	 * 
	 * Example dateTimeString:
	 * 20110221T124714Z
	 * <p>
	 * Date: 20110221 (2010-02-21)
	 * Time: 124714 (12:47:14)
	 * 
	 *
	 * @param  dateTimeString string containing a date and a time.
	 * @return QDateTime Instance (a Qt DateTime Object, which Combines QDate and QTime).
	 */
	public static QDateTime stringToQDateTime(String dateTimeString) {	
		String[] dateTime = splitTimeDateString(stripTag(dateTimeString));
		int year = new Integer(dateTime[0].substring(0, 4));
		int month = new Integer(dateTime[0].substring(4, 6));
		int day = new Integer(dateTime[0].substring(6, 8));
		int hours = new Integer(dateTime[1].substring(0,2));
		int minutes = new Integer(dateTime[1].substring(2,4));
		int seconds = new Integer(dateTime[1].substring(4,6));
		return new QDateTime(new QDate(year, month, day), new QTime(hours, minutes, seconds));
	}
	
	/**
	 * Splits a string containing a date followed by a time in two separate strings.
	 *
	 * @param  timeDateString string containing date and time delimited by 'T'  
	 * @return array containing a date- and a time-string
	 */
	public static String[] splitTimeDateString(String timeDateString) {
		return timeDateString.split("T"); 
	}
	
	/**
	 * Strips tags from parsed line.
	 *
	 * A .ics tag is a sequence of upper-case letters followed by a colon.
	 * Every parsed .ics line starts with a tag and may only contain a single tag. 
	 * (e.g. stripTag("SUMMARY:CN2:p3") would return "CN2:p3") 
	 *
	 * @param  stringWithTag a string beginning with a .ics tag
	 * @return string without the tag.
	 */
	public static String stripTag(String stringWithTag) {
		return stringWithTag.split(":", 2)[1];
	}
	
	/*
	public static getIcsParameters() {
		
	}
	*/
}
