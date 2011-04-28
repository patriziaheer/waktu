package ch.hsr.waktu.services;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.waktu.domain.WorkSession;

public class TestIcsParser {
	
	private WorkSession ws1 = new WorkSession();
	private WorkSession ws2 = new WorkSession();
	private WorkSession ws3 = new WorkSession();
	private LinkedList<WorkSession> calendar = new LinkedList<WorkSession>();
	
	@Before
	public void setUp() {
		ws1.setDescription("Prog3");
		ws1.setStart(new GregorianCalendar(2010, 8, 10, 8, 0, 0));
		ws1.setEnd(new GregorianCalendar(2010, 8, 10, 10, 0, 0));
		calendar.add(ws1);
		ws2.setDescription("Prog2");
		ws2.setStart(new GregorianCalendar(2010, 8, 10, 10, 10, 0));
		ws2.setEnd(new GregorianCalendar(2010, 8, 10, 11, 50, 0));
		calendar.add(ws2);
		ws3.setDescription("Analysis II");
		ws3.setStart(new GregorianCalendar(2010, 8, 9, 13, 10, 0));
		ws3.setEnd(new GregorianCalendar(2010, 8, 9, 15, 0, 0));
		calendar.add(ws3);
	}
	
	@Test
	public void parseIcsFile_Normal() {
		LinkedList<WorkSession> testCalendar = IcsParser.parseIcsFile("./resources/TestICS/normal.ics");
		calendarEquality(testCalendar, 3);
	}
	
	@Test
	public void parseIcsFile_allBeginVeventMissing_noEventsProcessed() {
		LinkedList<WorkSession> testCalendar = IcsParser.parseIcsFile("./resources/TestICS/allBeginVeventMissing.ics");
		assert(testCalendar.size() == 0);
	}
	
	@Test
	public void parseIcsFile_endVCalendarMissing_allEventsProcessedNoException() {
		LinkedList<WorkSession> testCalendar = IcsParser.parseIcsFile("./resources/TestICS/endVCalendarMissing.ics");
		calendarEquality(testCalendar, 3);
		//TODO assert not failing/no (null pointer) exception
	}

	@Test
	public void parseIcsFile_invalidTimeDelimiter_onlyValidEventsProcessed() {
		LinkedList<WorkSession> testCalendar = IcsParser.parseIcsFile("./resources/TestICS/invalidTimeDelimiter.ics");
		calendarEquality(testCalendar, 2);
		assert(testCalendar.size() == 2);
	}
	
	@Test
	public void parseIcsFile_invalidTimeFormat_onlyValidEventsProcessed() {
		LinkedList<WorkSession> testCalendar = IcsParser.parseIcsFile("./resources/TestICS/invalidTimeFormat.ics");
		calendarEquality(testCalendar, 2);
		assert(testCalendar.size() == 2);
	}
	
	@Test
	public void parseIcsFile_startTimeEndTimeMissing_onlyValidEventsProcessed() {
		LinkedList<WorkSession> testCalendar = IcsParser.parseIcsFile("./resources/TestICS/startTimeEndTimeMissing.ics");
		calendarEquality(testCalendar, 1);
		assert(testCalendar.size() == 1);
	}
	
	@Test
	public void parseIcsFile_summaryMissing_onlyValidEventsProcessed() {
		LinkedList<WorkSession> testCalendar = IcsParser.parseIcsFile("./resources/TestICS/summaryMissing.ics");
		calendarEquality(testCalendar, 2);
		assert(testCalendar.size() == 2);
	}
	
	private void calendarEquality(LinkedList<WorkSession> testCalendar, int noOfValidEntries) {
		for(int i=0; i < noOfValidEntries; i++) {
			assertEquals(calendar.get(i).getDescription(), testCalendar.get(i).getDescription());
		}
		for(int i=0; i < noOfValidEntries; i++) {
			assertEquals(calendar.get(i).getStart(), testCalendar.get(i).getStart());
		}
		for(int i=0; i < noOfValidEntries; i++) {
			assertEquals(calendar.get(i).getEnd(), testCalendar.get(i).getEnd());
		}
	}
	
	@After 
	public void tearDown() {
		ws1 = null;
		ws2 = null;
		ws3 = null;
		calendar = null;
	}
}
