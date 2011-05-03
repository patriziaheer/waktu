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
	
}
