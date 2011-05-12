package ch.hsr.waktu.services;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QTime;

public class TestTimeUtil {
	@Test
	public void convertGregorianToQDateTime_YearMonthDayHrsMinsSecs() {
		assertEquals(new QDateTime(new QDate(2011, 4, 27), new QTime(15, 32, 23)), 
				TimeUtil.convertGregorianToQDateTime(
						new GregorianCalendar(2011, 4, 27, 15, 32, 23)));
	}
	
	@Test
	public void convertQDateTimeToGregorian_YearMonthDayHrsMinsSecs() {
		assertEquals(new GregorianCalendar(2011, 4, 27, 15, 32, 23), 
				TimeUtil.convertQDateTimeToGregorian(
						new QDateTime(new QDate(2011, 4, 27), new QTime(15, 32, 23))));
	}
	
	@Test
	public void getFirstDayOfWeek_Monday() {
		int refFirstDayOfWeek = new QDate(2011, 5, 9).day();
		assertEquals(refFirstDayOfWeek, TimeUtil.getFirstDayOfWeek(new QDate(2011, 5, 9)));
	}
	
	@Test
	public void getFirstDayOfWeek_Saturday() {
		int refFirstDayOfWeek = new QDate(2011, 5, 9).day();
		assertEquals(refFirstDayOfWeek, TimeUtil.getFirstDayOfWeek(new QDate(2011, 5, 14)));
	}
	
	@Test
	public void getFirstDayOfWeek_Sunday() {
		int refFirstDayOfWeek = new QDate(2011, 5, 9).day();
		assertEquals(refFirstDayOfWeek, TimeUtil.getFirstDayOfWeek(new QDate(2011, 5, 15)));
	}
	
	@Test
	public void getFirstDayOfWeek_DateInMarchLeapyear() {
		int refFirstDayOfWeek = new QDate(2008, 2, 25).day();
		assertEquals(refFirstDayOfWeek, TimeUtil.getFirstDayOfWeek(new QDate(2008, 3, 2)));
	}
	
//	@Test
//	public void getWeekBoundaries() {
//		QDate[] refStartDateEndDate = {new QDate(), new QDate()};
//		
//		boundaryEquality(startDateEndDate);
//	}
	
//rly necessary? only tests Qt's behaviour
//	@Test
//	public void calculateTimespanInSecondsYearMonthDayHrsMinsSecsDifference() {
//		assertEquals(9, TimeUtil.calculateTimespanInSeconds(
//				new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,32)), 
//				new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,41))));
//	}
//	
//	@Test
//	public void calculateTimespanMinutesDifferenceNoOverflow() {
//		assertEquals(42*60, TimeUtil.calculateTimespanInSeconds(
//				new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,32)), 
//				new QDateTime(new QDate(2011, 3, 5), new QTime(12,45,32))));
//	}
//	
//	@Test
//	public void calculateTimespanHourDifferenceNoOverflow() {
//		assertEquals(11*60*60, TimeUtil.calculateTimespanInSeconds(
//				new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,32)), 
//				new QDateTime(new QDate(2011, 3, 5), new QTime(23,3,32))));
//	}
//	
//	@Test
//	public void calculateTimespanDayDifferenceNoOverflow() {
//		assertEquals(2*24*60*60, TimeUtil.calculateTimespanInSeconds(
//				new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,32)), 
//				new QDateTime(new QDate(2011, 3, 7), new QTime(12,3,32))));
//	}
//	
//	@Test
//	public void calculateTimespanMonthDifferenceNoLeapyear() {
//		assertEquals(28*24*60*60, TimeUtil.calculateTimespanInSeconds(
//				new QDateTime(new QDate(2011, 2, 5), new QTime(12,3,32)), 
//				new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,32))));
//	}
//	
//	@Test
//	public void calculateTimespanMonthDifferenceLeapyear() {
//		assertEquals(29*24*60*60, TimeUtil.calculateTimespanInSeconds(
//				new QDateTime(new QDate(2008, 2, 5), new QTime(12,3,32)), 
//				new QDateTime(new QDate(2008, 3, 5), new QTime(12,3,32))));
//	}
//	
//	@Test
//	public void calculateTimespanYearDifferenceNoLeapyear() {
//		assertEquals((7*31+4*30+28)*24*60*60, TimeUtil.calculateTimespanInSeconds(
//				new QDateTime(new QDate(2010, 3, 5), new QTime(12,3,32)), 
//				new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,32))));
//	}
//	
//	@Test
//	public void calculateTimespanYearDifferenceLeapyear() {
//		assertEquals((7*31+4*30+29)*24*60*60, TimeUtil.calculateTimespanInSeconds(
//				new QDateTime(new QDate(2008, 1, 5), new QTime(12,3,32)), 
//				new QDateTime(new QDate(2009, 1, 5), new QTime(12,3,32))));
//	}

//TODO BeforeTime is after afterTime
//	@Test
//	public void calculateTimespanBeforeTimeAfterTimeSwitched_TODO()Ê{
//		
//	}
}
