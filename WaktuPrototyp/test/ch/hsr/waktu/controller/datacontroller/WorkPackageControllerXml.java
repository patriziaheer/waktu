package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.services.WaktuException;
import ch.hsr.waktu.services.XmlUtil;

public class WorkPackageControllerXml extends WorkPackageController {
private String workPackageFilePath = "./test/testdata/workpackages.xml";
	
	private static WorkPackageControllerXml theInstance = null;

	public static WorkPackageControllerXml getInstance() {
		if (theInstance == null) {
			theInstance = new WorkPackageControllerXml();
		}
		return theInstance;
	}
	
	public static void setInstance(
			WorkPackageControllerXml workSessionControllerInstance) {
		theInstance = workSessionControllerInstance;
	}
	
	public WorkPackageControllerXml() {
		
	}
	
	@Override
	public List<WorkPackage> getAllWorkPackages() throws WaktuException {
		return XmlUtil.getWorkPackagesFromXml(workPackageFilePath);
	}
	
	@Override
	public List<WorkPackage> getAllWorkPackages(Project project) throws WaktuException {
		return null;
	}
	
	@Override
	public WorkPackage getWorkPackage(String description) throws WaktuException {
//	    System.out.println("getting WorkPacakge: " + description);
		for(WorkPackage wp: getAllWorkPackages()) {
			if(wp.getDescription().equals(description)) {
//			    System.out.println("WorkPackage found: " + wp.toString());
				return wp;
			}
		}
		return null;
	}
}
