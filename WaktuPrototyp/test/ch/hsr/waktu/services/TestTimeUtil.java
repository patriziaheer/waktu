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
		assertEquals(new QDateTime(new QDate(2011, 4, 27), new QTime(15, 32, 23)), TimeUtil.convertGregorianToQDateTime(new GregorianCalendar(2011, 4, 27, 15, 32, 23)));
	}
	
	@Test
	public void convertQDateTimeToGregorian_YearMonthDayHrsMinsSecs() {
		assertEquals(new GregorianCalendar(2011, 4, 27, 15, 32, 23), TimeUtil.convertQDateTimeToGregorian(new QDateTime(new QDate(2011, 4, 27), new QTime(15, 32, 23))));
	}

	@Test
	public void calculateTimespanSameDay() {
		assertEquals(new QTime(5,0,0), TimeUtil.calculateTimespan(new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,32)), new QDateTime(new QDate(2011, 3, 5), new QTime(17,3,32))));
		assertEquals(new QTime(0,12,0), TimeUtil.calculateTimespan(new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,32)), new QDateTime(new QDate(2011, 3, 5), new QTime(12,15,32))));
		assertEquals(new QTime(0,0,9), TimeUtil.calculateTimespan(new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,32)), new QDateTime(new QDate(2011, 3, 5), new QTime(12,3,41))));
	}
	
	@Test
	public void calculateTimespanSeveralDays() {
		
	}
}
