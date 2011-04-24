package ch.hsr.waktu.services;

import java.util.GregorianCalendar;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QTime;

public class TimeUtil {
	public static QDateTime convertGregorianCalToQDateTimeCal(GregorianCalendar dateTime) {
		QDate date = new QDate(dateTime.get(GregorianCalendar.YEAR), dateTime.get(GregorianCalendar.MONTH), dateTime.get(GregorianCalendar.DAY_OF_MONTH));
		QTime time = new QTime(dateTime.get(GregorianCalendar.HOUR), dateTime.get(GregorianCalendar.MINUTE), dateTime.get(GregorianCalendar.SECOND));
		return new QDateTime(date, time);
	}
	
	public static GregorianCalendar convertQDateTimeCalToGregorianCal(QDateTime dateTime) {
		QDate date = dateTime.date();
		QTime time = dateTime.time();
		return new GregorianCalendar(date.year(), date.month(), date.day(), time.hour(), time.minute(), time.second());
	}
}
