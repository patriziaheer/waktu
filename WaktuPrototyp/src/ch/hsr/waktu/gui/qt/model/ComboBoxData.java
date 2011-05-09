package ch.hsr.waktu.gui.qt.model;

import com.trolltech.qt.gui.QComboBox;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;

public class ComboBoxData {


	
	public static void createSystemRoleComboBox(QComboBox cmb) {
		cmb.clear();
		for (SystemRole role : SystemRole.values()) {
			cmb.addItem(role.name(), role);
		}
	}

	
	public static void createProjectManagerComboBox(QComboBox cmb) {
		cmb.clear();
		try {
			for (Usr usr : UserController.getInstance().getProjectManagers()) {
				cmb.addItem(usr.toString(), usr);
			}
		} catch (WaktuGeneralException e) {
			// TODO PH: exception handling
			e.printStackTrace();
		}
	}
	
	public static void createProjectForUserComboBox(QComboBox cmb, Usr currUser) {
		cmb.clear();
		try {
			for (Project proj : ProjectController.getInstance().getActiveProjects(currUser)) {
				cmb.addItem(proj.toString(), proj);
			}
		} catch (WaktuGeneralException e) {
			// TODO PH: unhandled exception
			e.printStackTrace();
		}
	}
	
	public static void createWorkPackageComboBox(QComboBox cmb, Project project) {
		cmb.clear();
		if (project != null) {
			try {
				for (WorkPackage wp: WorkPackageController.getInstance().getActiveWorkPackages(project)) {
					cmb.addItem(wp.toString(), wp);
				}
			} catch (WaktuGeneralException e) {
				// TODO PH: unhandled exceptions
				e.printStackTrace();
			}
		}
	}
	
	public static void createActiveProjectComboBox(QComboBox cmb) {
		cmb.clear();
		try {
			for (Project project : ProjectController.getInstance().getActiveProjects()) {
				cmb.addItem(project.toString(), project);
			}
		} catch (WaktuGeneralException e) {
			// TODO PH: unhandled exception
			e.printStackTrace();
		}
	}
	
	public static void createUserComboBox(QComboBox cmb) {
		cmb.clear();
		try {
			for (Usr usr: UserController.getInstance().getActiveUsers()) {
				cmb.addItem(usr.toString(), usr);
			}
		} catch (WaktuGeneralException e) {
			// TODO PH: exception handling
			e.printStackTrace();
		}
	}
	
}
