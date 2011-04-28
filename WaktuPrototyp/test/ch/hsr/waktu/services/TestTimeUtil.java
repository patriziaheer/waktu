package ch.hsr.waktu.services;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QTime;
import java.util.GregorianCalendar;

public class TestTimeUtil {
	@Test
	public void convertGregorianCalToQDateTimeCal_YearMonthDayHrsMinsSecs() {
		assertEquals(new QDateTime(new QDate(2011, 4, 27), new QTime(15, 32, 23)), TimeUtil.convertGregorianCalToQDateTimeCal(new GregorianCalendar(2011, 4, 27, 15, 32, 23)));
	}
	
	@Test
	public void convertQDateTimeCalToGregorianCal_YearMonthDayHrsMinsSecs() {
		assertEquals(new GregorianCalendar(2011, 4, 27, 15, 32, 23), TimeUtil.convertQDateTimeCalToGregorianCal(new QDateTime(new QDate(2011, 4, 27), new QTime(15, 32, 23))));
	}
}
