package ch.hsr.waktu.services;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;

import ch.hsr.waktu.TestSuite;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QTime;

public class TestTimeUtil extends TestSuite {
	
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
		QDate refFirstDayOfWeek = new QDate(2011, 5, 9);
		assertEquals(refFirstDayOfWeek, TimeUtil.getFirstDayOfWeek(new QDate(2011, 5, 9)));
	}
	
	@Test
	public void getFirstDayOfWeek_Saturday() {
		QDate refFirstDayOfWeek = new QDate(2011, 5, 9);
		assertEquals(refFirstDayOfWeek, TimeUtil.getFirstDayOfWeek(new QDate(2011, 5, 14)));
	}
	
	@Test
	public void getFirstDayOfWeek_Sunday() {
		QDate refFirstDayOfWeek = new QDate(2011, 5, 9);
		assertEquals(refFirstDayOfWeek, TimeUtil.getFirstDayOfWeek(new QDate(2011, 5, 15)));
	}
	
	@Test
	public void getFirstDayOfWeek_WeekSpanningOverFebruaryMarchLeapyear() {
		QDate refFirstDayOfWeek = new QDate(2008, 2, 25);
		assertEquals(refFirstDayOfWeek, TimeUtil.getFirstDayOfWeek(new QDate(2008, 3, 2)));
	}
	
	public void getWeekBoundaries() {
		QDate[] refStartDateEndDate = {new QDate(2011, 5, 9), new QDate(2011, 5, 15)};		
		assertBoundaryEquality(refStartDateEndDate, TimeUtil.getWeekBoundaries(
				new QDate(2011, 5, 13)));
	}
	
	@Test
	public void getWeekBoundaries_WeekSpanningOverTwoMonths() {
		QDate[] refStartDateEndDate = {new QDate(2011, 5, 30), new QDate(2011, 6, 5)};		
		assertBoundaryEquality(refStartDateEndDate, TimeUtil.getWeekBoundaries(
				new QDate(2011, 5, 31)));
	}
	
	@Test
	public void getWeekBoundaries_WeekSpanningOverFebruaryMarchLeapyear() {
		QDate[] refStartDateEndDate = {new QDate(2008, 2, 25), new QDate(2008, 3, 2)};		
		assertBoundaryEquality(refStartDateEndDate, TimeUtil.getWeekBoundaries(
				new QDate(2008, 3, 1)));
	}

	private void assertBoundaryEquality(QDate[] refStartDateEndDate,
			QDate[] weekBoundaries) {
			for(int i=0; i < 2; i++) {
				assertEquals(refStartDateEndDate[i], weekBoundaries[i]);
			}
	}
}
