package ch.hsr.waktu.services;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QTime;

public class TimeUtil {
	private static Logger logger = Logger.getLogger(TimeUtil.class);
	
	public static QDateTime convertGregorianToQDateTime(GregorianCalendar dateTime) {
		QDate date = new QDate(dateTime.get(GregorianCalendar.YEAR), dateTime.get(GregorianCalendar.MONTH), dateTime.get(GregorianCalendar.DAY_OF_MONTH));
		QTime time = new QTime(dateTime.get(GregorianCalendar.HOUR), dateTime.get(GregorianCalendar.MINUTE), dateTime.get(GregorianCalendar.SECOND));
		return new QDateTime(date, time);
	}
	
	public static GregorianCalendar convertQDateTimeToGregorian(QDateTime dateTime) {
		QDate date = dateTime.date();
		QTime time = dateTime.time();
		GregorianCalendar gregCal = new GregorianCalendar(date.year(), date.month(), date.day(), time.hour(), time.minute(), time.second());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = formatter.format(gregCal.getTime());
		logger.info(formattedDate);
		return gregCal;
	}
	
	public static QTime calculateTimespan(QDateTime timeBefore, QDateTime timeAfter) {
		QTime timeSpan = new QTime(0, 0, 0);
		timeSpan.addSecs(timeBefore.secsTo(timeAfter));
		return timeSpan;
	}
}
