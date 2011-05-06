package ch.hsr.waktu.controller;

import java.util.LinkedList;

import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;

public class TimeController {
	private static final double HOURS_PER_WORKDAY = 8.5;
	
	public static double calculateWorktimeForProject(Project project, WorkPackage workPackage, Usr usr, QDateTime start, QDateTime end) {
		//TODO: added by PH => null for parameters means no filter
		return 0.0;
	}
	
	public static double calculateOvertime(Usr user, QDate fromDate, QDate toDate) {
		return calculateWorktime(user, fromDate, toDate) - getPlannedTime(user, fromDate, toDate);
	}
	
	public static double calculateWorktime(Usr user, QDate fromDate, QDate toDate) {
		int worktime = 0;
		LinkedList<WorkSession> workSessionsWithinRange = new LinkedList<WorkSession>();
		for(QDate d = fromDate; fromDate.daysTo(toDate) >= 0; d.addDays(1)) {
			try {
				for(WorkSession ws: WorkSessionController.getInstance().getWorkSessionsStartingAt(user, d)) {
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
		return timeInSecondsToHalfAnHourPrecision(worktime);
	}
	
	public static double getPlannedTime(Usr user, QDate fromDate, QDate toDate) {
		return fromDate.daysTo(toDate) * user.getPensum() * HOURS_PER_WORKDAY;
	}
	
	public static double timeInSecondsToHalfAnHourPrecision(int timeInSeconds) {
		//TODO: round up/down
		return 0.0;
	}
}
