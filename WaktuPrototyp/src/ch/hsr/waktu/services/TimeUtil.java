package ch.hsr.waktu.services;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QTime;

public class TimeUtil {

	private TimeUtil() {

	}

	private static Logger logger = Logger.getLogger(TimeUtil.class);

	// TODO: Konvertier-Methoden refactoren gemäss DKellers Aussage.. MF: wtf?
	public static QDateTime convertGregorianToQDateTime(
			final GregorianCalendar dateTime) {
		QDate date = new QDate(dateTime.get(GregorianCalendar.YEAR),
				dateTime.get(GregorianCalendar.MONTH),
				dateTime.get(GregorianCalendar.DAY_OF_MONTH));
		QTime time = new QTime(dateTime.get(GregorianCalendar.HOUR_OF_DAY),
				dateTime.get(GregorianCalendar.MINUTE),
				dateTime.get(GregorianCalendar.SECOND));
		return new QDateTime(date, time);
	}

	public static GregorianCalendar convertQDateTimeToGregorian(
			final QDateTime dateTime) {
		QDate date = dateTime.date();
		QTime time = dateTime.time();
		GregorianCalendar gregCal = new GregorianCalendar(date.year(),
				date.month(), date.day(), time.hour(), time.minute(),
				time.second());
		logger.info(gregCal.get(Calendar.DAY_OF_MONTH) + "."
				+ gregCal.get(Calendar.MONTH) + "."
				+ gregCal.get(Calendar.YEAR));
		logger.info(gregCal.get(Calendar.HOUR) + ":"
				+ gregCal.get(Calendar.MINUTE));
		return gregCal;
	}

	public static int calculateTimespanInSeconds(
			final GregorianCalendar timeBefore,
			final GregorianCalendar timeAfter) {
		return calculateTimespanInSeconds(
				convertGregorianToQDateTime(timeBefore),
				convertGregorianToQDateTime(timeAfter));
	}

	public static int calculateTimespanInSeconds(final QDateTime timeBefore,
			final QDateTime timeAfter) {
		return timeBefore.secsTo(timeAfter);
	}

	public static GregorianCalendar convertQDateToGregorian(final QDate date) {
		return new GregorianCalendar(date.year(), date.month(), date.day());
	}

	public static QDate[] getMonthBoundaries(final QDate date) {
		// TODO
		QDate[] startDayEndDay = { new QDate(date.year(), date.month(), 1),
				new QDate(date.year(), date.month(), date.daysInMonth()) };
		return startDayEndDay;
	}

	public static QDate[] getWeekBoundaries(final QDate date) {
		// TODO
		QDate[] startDayEndDay = {
				new QDate(date.year(), getFirstDayOfWeek(date).month(),
						getFirstDayOfWeek(date).day()),
				new QDate(date.year(), getLastDayOfWeek(date).month(),
						getLastDayOfWeek(date).day()) };
		return startDayEndDay;
	}

	private static QDate getLastDayOfWeek(final QDate date) {
		QDate lastDay = getFirstDayOfWeek(date);
		return lastDay.addDays(6);
	}

	static QDate getFirstDayOfWeek(final QDate date) {
		QDate firstDay = date.clone();
		return firstDay.addDays(-date.dayOfWeek() + 1);
	}

	public static QDate[] getYearBoundaries(final QDate date) {
		QDate[] startDayEndDay = { new QDate(date.year(), 1, 1),
				new QDate(date.year(), 12, 31) };
		return startDayEndDay;
	}

	/**
	 * Converts an .ics dateTime string into a QDateTime object
	 * 
	 * The format of the input dateTimeString is yyyyMMddThhmmss, where yyyy
	 * denotes the year, MM the month, dd the day, 'T' a delimiter, hh the hour,
	 * mm the minutes, ss the seconds. Failing to pass the dateTimeString in
	 * this format will result in undesirable DateTime Objects or a @throws
	 * IndexOutOfBoundsException may be thrown in case the dateTimeString is too
	 * short.
	 * <p>
	 * Example dateTimeString: 20110221T124714Z
	 * <p>
	 * Date: 20110221 (2010-02-21) Time: 124714 (12:47:14)
	 * 
	 * 
	 * @param dateTimeString
	 *            string containing a date and a time.
	 * @return QDateTime Instance (a Qt DateTime Object, which Combines QDate
	 *         and QTime).
	 */
	static QDateTime stringToQDateTime(final String dateTimeString) {
		String[] dateTime = splitTimeDateString(dateTimeString);
		int year = new Integer(dateTime[0].substring(0, 4));
		int month = new Integer(dateTime[0].substring(4, 6));
		int day = new Integer(dateTime[0].substring(6, 8));
		int hours = new Integer(dateTime[1].substring(0, 2));
		int minutes = new Integer(dateTime[1].substring(2, 4));
		int seconds = new Integer(dateTime[1].substring(4, 6));
		return new QDateTime(new QDate(year, month, day), new QTime(hours,
				minutes, seconds));
	}

	/**
	 * Splits a string containing a date followed by a time in two separate
	 * strings.
	 * 
	 * @param timeDateString
	 *            string containing date and time delimited by 'T'
	 * @return array containing a date- and a time-string
	 */
	private static String[] splitTimeDateString(final String timeDateString) {
		return timeDateString.split("T");
	}
}
