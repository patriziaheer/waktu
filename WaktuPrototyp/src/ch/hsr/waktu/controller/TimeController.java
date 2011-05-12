package ch.hsr.waktu.controller;

import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QDate;

public class TimeController {
	public static final double HOURS_PER_WORKDAY = 8.5;
	public static final int NUMBER_OF_WORKDAYS_PER_WEEK = 5;
	
	public static double calculateWorktimeForProject(Project project, WorkPackage workPackage, Usr usr, QDate start, QDate end) {
		//TODO: added by PH => null for parameters means no filter
		return 0.0;
	}
	
	public static double calculateOvertime(Usr user, QDate fromDate, QDate toDate) throws WaktuException {
		return calculateWorktime(user, fromDate, toDate) - getPlannedTime(user, fromDate, toDate);
	}
	
	public static double calculateWorktimeForWeek(Usr user, QDate date) throws WaktuException {
		QDate[] weekStartDateEndDate = TimeUtil.getMonthBoundaries(date);
		return calculateWorktime(user, weekStartDateEndDate[0], weekStartDateEndDate[1]);
	}
	
	public static double calculateWorktimeForMonth(Usr user, QDate date) throws WaktuException {
		QDate[] monthStartDateEndDate = TimeUtil.getMonthBoundaries(date);
		return calculateWorktime(user, monthStartDateEndDate[0], monthStartDateEndDate[1]);
	}
	
	public static double calculateWorktime(Usr user, QDate fromDate, QDate toDate) throws WaktuException {
		int worktime = 0;
		//LinkedList<WorkSession> workSessionsWithinRange = new LinkedList<WorkSession>();
		for(QDate d = fromDate; d.daysTo(toDate) >= 0; d = d.addDays(1)) {
				for(WorkSession ws: WorkSessionController.getInstance().getWorkSessions(user, d)) {
					if(ws.getEnd().before(toDate)) {
						worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
						//workSessionsWithinRange.add(ws);
					}
				}		
		}
		/*for(WorkSession ws: workSessionsWithinRange) {
			worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
		}*/
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
