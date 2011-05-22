package ch.hsr.waktu.controller.datacontroller;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import com.trolltech.qt.core.QDate;

import ch.hsr.waktu.controller.BusinessRuleController;
import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;
import ch.hsr.waktu.services.XmlUtil;

public class WorkSessionControllerXml extends WorkSessionController {
private String workSessionFilePath = "./test/testdata/worksessions.xml";
	
	private static WorkSessionControllerXml theInstance = null;

	public static WorkSessionControllerXml getInstance() {
		if (theInstance == null) {
			theInstance = new WorkSessionControllerXml();
		}
		return theInstance;
	}
	
	public static void setInstance(
			WorkSessionControllerXml workSessionControllerInstance) {
		theInstance = workSessionControllerInstance;
	}
	
	public WorkSessionControllerXml() {
		
	}
	
	@Override
	public List<WorkSession> getWorkSessions(Usr user) throws WaktuException {

		List<WorkSession> workSessionsByUser = new LinkedList<WorkSession>();

//		if(!PermissionController.checkPermission()) {
//			throw new WaktuException("Permission denied");
//		}
		
		for(WorkSession ws: XmlUtil.getWorkSessionsFromXml(workSessionFilePath)){
			if(ws.getUser().equals(user)) {
				workSessionsByUser.add(ws);
			}
		}
		return workSessionsByUser;
}

	@Override
	public List<WorkSession> getWorkSessions(Usr user, QDate date)
		throws WaktuException {

//		if(!PermissionController.checkPermission()) {
//			throw new WaktuException("Permission denied");
//		}
		
		List<WorkSession> workSessionsByDate = new LinkedList<WorkSession>();
		
		for(WorkSession ws: getWorkSessions(user)) {
			if(TimeUtil.convertGregorianToQDateTime(ws.getStart()).date().equals(date)) {
				workSessionsByDate.add(ws);
			}
		}
	
		return workSessionsByDate;
	}

	@Override
	public List<WorkSession> getWorkSessions(Project project)
		throws WaktuException {
	
//		if(!PermissionController.checkPermission()) {
//			throw new WaktuException("Permission denied");
//		}
		
		List<WorkSession> workSessionsByProject = new LinkedList<WorkSession>();

		for(WorkSession ws: XmlUtil.getWorkSessionsFromXml(workSessionFilePath)) {
			if(ws.getWorkPackage().getProject().equals(project)) {
				workSessionsByProject.add(ws);
			}
		}
		return workSessionsByProject;
	}

	@Override
	public WorkSession addWorkSession(Usr user, WorkPackage workPackage,
		GregorianCalendar startTime, GregorianCalendar endTime,
		String description) throws WaktuException {
	
		WorkSession newWorkSession = new WorkSession(user, workPackage,
			startTime, endTime, description);
	
//		if(!PermissionController.checkPermission()) {
//			throw new WaktuException("Permission denied");
//		}
	
		BusinessRuleController.check(newWorkSession);
	
		return newWorkSession;
	}

	public void updateWorkSession(WorkSession workSession)
		throws WaktuException {
	
//		if(!PermissionController.checkPermission()) {
//			throw new WaktuException("Permission denied");
//		}
//	
//		BusinessRuleController.check(workSession);
//	
//		WorkSession updatedWorkSession;	
	}

/**
* 
* @param workSession
*/
	public void removeWorkSession(WorkSession workSession)
		throws WaktuException {
	
//		if(!PermissionController.checkPermission()) {
//			throw new WaktuException("Permission denied");
//		}
	
	
	}
}
