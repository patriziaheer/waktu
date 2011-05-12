package ch.hsr.waktu.controller;

import java.util.LinkedList;

import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;

import com.trolltech.qt.core.QDate;

public class TimeController {
	public static final double HOURS_PER_WORKDAY = 8.5;
	public static final int NUMBER_OF_WORKDAYS_PER_WEEK = 5;
	
	public static double calculateWorktimeForProject(Project project, WorkPackage workPackage, Usr usr, QDate start, QDate end) {
		//TODO: added by PH => null for parameters means no filter
		return 0.0;
	}
	
	public static double calculateOvertime(Usr user, QDate fromDate, QDate toDate) {
		return calculateWorktime(user, fromDate, toDate) - getPlannedTime(user, fromDate, toDate);
	}
	
	public static double calculateWorktimeForWeek(Usr user, QDate date) {
		QDate[] weekStartDateEndDate = TimeUtil.getMonthBoundaries(date);
		return calculateWorktime(user, weekStartDateEndDate[0], weekStartDateEndDate[1]);
	}
	
	public static double calculateWorktimeForMonth(Usr user, QDate date) {
		QDate[] monthStartDateEndDate = TimeUtil.getMonthBoundaries(date);
		return calculateWorktime(user, monthStartDateEndDate[0], monthStartDateEndDate[1]);
	}
	
	public static double calculateWorktime(Usr user, QDate fromDate, QDate toDate) {
		int worktime = 0;
		LinkedList<WorkSession> workSessionsWithinRange = new LinkedList<WorkSession>();
		for(QDate d = fromDate; fromDate.daysTo(toDate) >= 0; d.addDays(1)) {
			try {
				for(WorkSession ws: WorkSessionController.getInstance().getWorkSessions(user, d)) {
					if(ws.getEnd().before(toDate)) {
						workSessionsWithinRange.add(ws);
					} else {
						WorkSession trimmedWs = new WorkSession();
						trimmedWs.setStart(ws.getStart());
						trimmedWs.setEnd(TimeUtil.convertQDateToGregorian(toDate));
						workSessionsWithinRange.add(trimmedWs);
					}
					
				}
			} catch(Exception e) {
				return worktime;
			}			
		}
		for(WorkSession ws: workSessionsWithinRange) {
			worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
		}
		return worktime/3600;
	}
	
	public static double getPlannedTime(Usr user, QDate currMonth) {
		return currMonth.daysInMonth() * getNumberOfWorkdays(user) * HOURS_PER_WORKDAY;
	}
	
	public static double getPlannedTime(Usr user, QDate fromDate, QDate toDate) {
		return fromDate.daysTo(toDate) * getNumberOfWorkdays(user) * HOURS_PER_WORKDAY;
	}
	
	private static float getNumberOfWorkdays(Usr user) {
		//TODO: subtract holidays
		return user.getPensum() / 100 * NUMBER_OF_WORKDAYS_PER_WEEK;
	}

}
