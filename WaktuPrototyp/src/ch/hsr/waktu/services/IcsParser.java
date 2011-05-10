package ch.hsr.waktu.services;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.domain.WorkSession;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QTime;

public class IcsParser {

	/**
	 * Parses contents of an .ics file which is used in iCal, Outlook, etc.
	 * <p>
	 *
	 * <b>ICS FILE FORMAT:</b><br>
	 * An .ics file consists of multiple lines, each line beginning with a tag. 
	 * 
	 * 
	 * <p>
	 * <b>EXAMPLE EVENT:</b>
	 * <br>
	 * BEGIN:VEVENT<br>
	 * CREATED:20100809T062219Z<br>
 	 * UID:2DE909B3-96C6-4D7B-BB99-5CA7487C43A2<br>
	 * DTEND;TZID=Europe/Zurich:20100810T115000<br>
	 * TRANSP:OPAQUE<br>
	 * SUMMARY:Prog2<br>
	 * DTSTART;TZID=Europe/Zurich:20100810T101000<br>
	 * DTSTAMP:20100809T062304Z<br>
	 * SEQUENCE:4<br>
	 * END:VEVENT
	 * <p>
	 * An WorkSession starts with the tag "BEGIN:VEVENT" and should be closed by "END:VEVENT".
	 * A missing "END:VEVENT" could lead to missing WorkSessions as startTime and endTime might
	 * be overwritten.
	 * <p>
	 * Within a WorkSession the following tags may occur in any order:
	 * <ul>
	 * <li>SUMMARY:* 		WorkSession Name
	 * <li>DTEND;...:* 		End time
	 * <li>DTSTART;...:* 	Start time
	 * </ul>
	 * *: Mandatory tags
	 * <p>
	 * All other tags are ignored.
	 * <p>
	 * If the Argument filePath is invalid (file non-existent) or null, parseIcsFile() 
	 * will return null 
	 *
	 * @param  filePath path to the .ics file to parse.
	 * @return a linked list containing all WorkSessions extracted from input file.
	 * @throws WaktuGeneralException 
	 */
	public static LinkedList<WorkSession> parseIcsFile(String filePath) throws WaktuGeneralException {
		
		LinkedList<WorkSession> calendar = new LinkedList<WorkSession>();
		
		BufferedReader br;
		
		//TODO Limit file size?
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
		} catch (Exception e) {
			throw new WaktuGeneralException("Error reading File: " + filePath);
		}
					
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
								
						} else if(currentLine.startsWith("DTSTART")) { 
							//start time read
							QDateTime startTime = stringToQDateTime(currentLine);
							tmpWs.setStart(TimeUtil.convertQDateTimeToGregorian(startTime));
							
						} else if(currentLine.startsWith("DTEND")) { 
							//end time read
							QDateTime endTime = stringToQDateTime(currentLine);
							tmpWs.setEnd(TimeUtil.convertQDateTimeToGregorian(endTime));

						}
						
					} 
					//Appointment end
					if(isValidWorkSession(tmpWs)) {
						calendar.add(tmpWs);
					}
				}
			}
		} catch(Exception e) {
			
		}
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("IO Exception occurred");
		}

		return calendar;
	}

	private static boolean isValidWorkSession(WorkSession workSession) {
		if(workSession == null) {
			return false;
		}
		return (workSession.getDescription() != null && workSession.getStart() != null && workSession.getEnd() != null);
	}

	/**
	 * Converts an .ics dateTime string into a QDateTime object
	 *
	 * The format of the input dateTimeString is yyyyMMddThhmmss, where yyyy denotes the year,
	 * MM the month, dd the day, 'T' a delimiter, hh the hour, mm the minutes, ss the seconds.
	 * Failing to pass the dateTimeString in this format will result in undesirable DateTime Objects 
	 * or a @throws IndexOutOfBoundsException may be thrown in case the dateTimeString is too short.
	 * <p>
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
	private static QDateTime stringToQDateTime(String dateTimeString) {
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
	private static String[] splitTimeDateString(String timeDateString) {
		return timeDateString.split("T"); 
	}
	
	/**
	 * Strips tags from parsed line.
	 *
	 * A .ics tag is a sequence of upper-case letters followed by a colon.
	 * Every parsed .ics line starts with a tag and may only contain a single tag. 
	 * (e.g. stripTag("SUMMARY:CN2:p3") would return "CN2:p3") 
	 *
	 * @param  stringWithTag a string beginning with an .ics tag
	 * @return string without the tag.
	 */
	private static String stripTag(String stringWithTag) {
		return stringWithTag.split(":", 2)[1];
	}

}
