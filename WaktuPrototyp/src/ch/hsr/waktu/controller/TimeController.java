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
	
	public static double calculateWorktime(Usr usr, Project project, WorkPackage workPackage, QDate start, QDate end) throws WaktuException {
		if(workPackage == null && usr != null && start != null && end != null) {
			return calculateWorktime(project, usr, start, end);
		} else if(workPackage != null && usr == null && start != null && end != null) {
			return calculateWorktime(project, workPackage, start, end);
		} else if(workPackage != null && usr != null && start == null && end == null) {
			return calculateWorktime(project, workPackage, usr);
		} else if(workPackage != null && usr == null && start == null && end == null) {
			return calculateWorktime(project, workPackage);
		} else if(workPackage == null && usr != null && start == null && end == null) {
			return calculateWorktime(project, usr);
		} else if(workPackage != null && usr == null && start == null && end == null) {
			return calculateWorktime(project, workPackage);
		} else if(workPackage == null && usr == null && start != null && end != null) {
			return calculateWorktime(project, start, end);
		} else if(workPackage == null && usr == null && start == null && end == null) {
			return calculateWorktime(project);
		}
		return 0.0;
	}
	
	private static double calculateWorktime(Project project) throws WaktuException {
		double worktime = 0;

		try {
			for(WorkSession ws: WorkSessionController.getInstance().getWorkSessions(project)) {
				worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
			}
		} catch (NullPointerException e) {
			return 0;
		}		
		return worktime/3600;
	}

	private static double calculateWorktime(Project project, QDate start,
			QDate end) throws WaktuException {
		double worktime = 0;

		try {
			for(WorkSession ws: WorkSessionController.getInstance().getWorkSessions(project, start, end)) {
				worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
			}
		} catch (NullPointerException e) {
			return 0;
		}		
		return worktime/3600;
	}

	private static double calculateWorktime(Project project, Usr usr) throws WaktuException {
		double worktime = 0;

		try {
			for(WorkSession ws: WorkSessionController.getInstance().getWorkSessions(project, usr)) {
				worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
			}
		} catch (NullPointerException e) {
			return 0;
		}		
		return worktime/3600;
	}

	private static double calculateWorktime(Project project,
			WorkPackage workPackage) throws WaktuException {
		double worktime = 0;

		try {
			//TODO: project removed
			for(WorkSession ws: WorkSessionController.getInstance().getWorkSessions(workPackage)) {
				worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
			}
		} catch (NullPointerException e) {
			return 0;
		}		
		return worktime/3600;
	}

	private static double calculateWorktime(Project project,
			WorkPackage workPackage, Usr usr) throws WaktuException {
		double worktime = 0;

		try {
			//TODO: project removed
			for (WorkSession ws : WorkSessionController.getInstance()
					.getWorkSessions(workPackage, usr)) {
				worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(),
						ws.getEnd());
			}
		} catch (NullPointerException e) {
			return 0;
		}
		return worktime/3600;
	}
	
	private static double calculateWorktime(Project project, WorkPackage workPackage, QDate fromDate, QDate toDate) throws WaktuException {
		double worktime = 0;

		try {
			for(WorkSession ws: WorkSessionController.getInstance().getWorkSessions(workPackage, fromDate, toDate)) {
				worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
			}
		} catch(NullPointerException e) {
			return 0;
		}
					
		return worktime/3600;
	}
	
	private static double calculateWorktime(Project project, Usr user, QDate fromDate, QDate toDate) throws WaktuException {
		double worktime = 0;

		for(WorkSession ws: WorkSessionController.getInstance().getWorkSessions(project, user, fromDate, toDate)) {
			worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
		}		
		return worktime/3600;
	}

	public static double calculateOvertime(Usr user, QDate fromDate, QDate toDate) throws WaktuException {
		return calculateWorktime(user, fromDate, toDate) - getPlannedTime(user, fromDate, toDate);
	}
	
	public static double calculateWorktimeForWeek(Usr user, QDate date) throws WaktuException {
		QDate[] weekStartDateEndDate = TimeUtil.getWeekBoundaries(date);
		return calculateWorktime(user, weekStartDateEndDate[0], weekStartDateEndDate[1]);
	}
	
	public static double calculateWorktimeForMonth(Usr user, QDate date) throws WaktuException {
		QDate[] monthStartDateEndDate = TimeUtil.getMonthBoundaries(date);
		return calculateWorktime(user, monthStartDateEndDate[0], monthStartDateEndDate[1]);
	}
	
	public static double calculateWorktime(Usr user, QDate fromDate, QDate toDate) throws WaktuException {
		double worktime = 0;

		try {
			for(WorkSession ws: WorkSessionController.getInstance().getWorkSessions(user, fromDate, toDate)) {
				worktime += TimeUtil.calculateTimespanInSeconds(ws.getStart(), ws.getEnd());
			}
		} catch (NullPointerException e) {
			return 0;
		}
					
		return worktime/3600;
	}
	
	public static double getPlannedTime(Usr user, QDate currMonth) {
		return getNumberOfWorkdays(user, currMonth) * HOURS_PER_WORKDAY;
	}
	
	public static double getPlannedTime(Usr user, QDate fromDate, QDate toDate) {
		return fromDate.daysTo(toDate) * getNumberOfWorkdays(user, fromDate, toDate) * HOURS_PER_WORKDAY;
	}
	
	private static double getNumberOfWorkdays(Usr user, QDate currMonth) {
		QDate start = new QDate(currMonth.year(), currMonth.month(), 1);
		QDate end = new QDate(currMonth.year(), currMonth.month(), currMonth.daysInMonth());
		return user.getPensum() / 100.0 * getNumberOfWorkdays(start, end);
	}
	
	private static double getNumberOfWorkdays(Usr user, QDate fromDate, QDate toDate) {
		return user.getPensum() / 100.0 * getNumberOfWorkdays(fromDate, toDate);
	}

	
	private static int getNumberOfWorkdays(QDate start, QDate end) {
		int days = 0;
		for (QDate d = start; d.compareTo(end) <= 0; d = d.addDays(1)) {
			if (d.dayOfWeek() != 6 && d.dayOfWeek() != 7) {
				days++;
			}
		}
		return days;
	}

}
