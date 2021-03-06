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

    public static QDateTime convertGregorianToQDateTime(
            final GregorianCalendar dateTime) {
        QDate date = new QDate(dateTime.get(GregorianCalendar.YEAR),
                dateTime.get(GregorianCalendar.MONTH)+1,
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
                date.month()-1, date.day(), time.hour(), time.minute(),
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

    /**
     * 
     * @param date
     * @return array containing first and last day of month specified by date
     */
    public static QDate[] getMonthBoundaries(final QDate date) {
        QDate[] startDayEndDay = {new QDate(date.year(), date.month(), 1),
                new QDate(date.year(), date.month(), date.daysInMonth()) };
        return startDayEndDay;
    }

    /**
     * 
     * @param date
     * @return array containing first and last day of week specified by date
     */
    public static QDate[] getWeekBoundaries(final QDate date) {
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

    /**
     * 
     * @param date
     * @return first day of week specified by date
     */
    static QDate getFirstDayOfWeek(final QDate date) {
        QDate firstDay = date.clone();
        return firstDay.addDays(-date.dayOfWeek() + 1);
    }

    /**
     * 
     * @param date
     * @return array containing first and last day of year specified by date
     */
    public static QDate[] getYearBoundaries(final QDate date) {
        QDate[] startDayEndDay = {new QDate(date.year(), 1, 1),
                new QDate(date.year(), 12, 31)};
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
        return QDateTime.fromString(dateTimeString.replace("T", " "), "yyyyMMdd hhmmss");
    }

}
