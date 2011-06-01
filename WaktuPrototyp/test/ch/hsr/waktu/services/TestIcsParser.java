package ch.hsr.waktu.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.waktu.TestSuite;
import ch.hsr.waktu.domain.WorkSession;

public class TestIcsParser extends TestSuite {
	
	private WorkSession ws1 = new WorkSession();
	private WorkSession ws2 = new WorkSession();
	private WorkSession ws3 = new WorkSession();
	private ArrayList<WorkSession> calendar = new ArrayList<WorkSession>();

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
	
	@After 
	public void tearDown() {
		ws1 = null;
		ws2 = null;
		ws3 = null;
		calendar = null;
	}
	
	@Test
	public void parseIcsFile_allBeginVeventMissing_noEventsProcessed() throws WaktuException {
		ArrayList<WorkSession> testCalendar = IcsParser.parseIcsFile("./test/testdata/allBeginVeventMissing.ics");
		assertEquals(0, testCalendar.size());
	}

	@Test
	public void parseIcsFile_endVCalendarMissing_allEventsProcessedNoException() throws WaktuException {
		ArrayList<WorkSession> testCalendar = IcsParser.parseIcsFile("./test/testdata/endVCalendarMissing.ics");
		assertCalendarValidity(testCalendar, 3);
	}
	
	@Test
	public void parseIcsFile_Normal() throws WaktuException {
		ArrayList<WorkSession> testCalendar = IcsParser.parseIcsFile("./test/testdata/normal.ics");
		assertCalendarValidity(testCalendar, 3);
	}

	@Test
	public void parseIcsFile_invalidTimeDelimiter_onlyValidEventsProcessed() throws WaktuException {
		ArrayList<WorkSession> testCalendar = IcsParser.parseIcsFile("./test/testdata/invalidTimeDelimiter.ics");
		assertCalendarValidity(testCalendar, 2);
	}
	
	@Test
	public void parseIcsFile_invalidTimeFormat_onlyValidEventsProcessed() throws WaktuException {
		ArrayList<WorkSession> testCalendar = IcsParser.parseIcsFile("./test/testdata/invalidTimeFormat.ics");
		assertCalendarValidity(testCalendar, 1);
	}
	
	@Test
	public void parseIcsFile_startTimeEndTimeMissing_onlyValidEventsProcessed() throws WaktuException {
		ArrayList<WorkSession> testCalendar = IcsParser.parseIcsFile("./test/testdata/startTimeEndTimeMissing.ics");
		assertCalendarValidity(testCalendar, 1);
	}
	
	@Test
	public void parseIcsFile_summaryMissing_onlyValidEventsProcessed() throws WaktuException {
		ArrayList<WorkSession> testCalendar = IcsParser.parseIcsFile("./test/testdata/summaryMissing.ics");
		assertCalendarValidity(testCalendar, 2);
	}
	
	@Test(expected = WaktuException.class)
	public void parseIcsFile_invalidFilepathInArgument_NullReturnedWaktuGeneralException() throws WaktuException {
		ArrayList<WorkSession> testCalendar = IcsParser.parseIcsFile(null);
		assertEquals(null, testCalendar);
	}
	
	
	private void assertCalendarValidity(final ArrayList<WorkSession> testCalendar, final int noOfValidEntries) {
		for(int i = 0; i < noOfValidEntries; i++) {
			assertEquals(calendar.get(i).getDescription(), testCalendar.get(i).getDescription());
		}
		for(int i = 0; i < noOfValidEntries; i++) {
			assertEquals(calendar.get(i).getStart(), testCalendar.get(i).getStart());
		}
		for(int i = 0; i < noOfValidEntries; i++) {
			assertEquals(calendar.get(i).getEnd(), testCalendar.get(i).getEnd());
		}
		assertEquals(noOfValidEntries, testCalendar.size());
	}
}
