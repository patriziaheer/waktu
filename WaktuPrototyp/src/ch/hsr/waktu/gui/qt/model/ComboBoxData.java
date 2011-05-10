package ch.hsr.waktu.gui.qt.model;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;

import com.trolltech.qt.gui.QComboBox;

public class ComboBoxData {

	public static void createSystemRoleComboBox(QComboBox cmb) {
		cmb.clear();
		for (SystemRole role : SystemRole.values()) {
			cmb.addItem(role.name(), role);
		}
	}

	public static void createProjectManagerComboBox(QComboBox cmb)
			throws WaktuGeneralException {
		cmb.clear();
		for (Usr usr : UserController.getInstance().getProjectManagers()) {
			cmb.addItem(usr.toString(), usr);
		}
	}

	public static void createProjectForUserComboBox(QComboBox cmb, Usr currUser) throws WaktuGeneralException {
		cmb.clear();
		for (Project proj : ProjectController.getInstance().getActiveProjects(
				currUser)) {
			cmb.addItem(proj.toString(), proj);
		}
	}

	public static void createWorkPackageComboBox(QComboBox cmb, Project project) throws WaktuGeneralException {
		cmb.clear();
		if (project != null) {
				for (WorkPackage wp : WorkPackageController.getInstance()
						.getActiveWorkPackages(project)) {
					cmb.addItem(wp.toString(), wp);
				}
		}
	}

	public static void createActiveProjectComboBox(QComboBox cmb) throws WaktuGeneralException {
		cmb.clear();
			for (Project project : ProjectController.getInstance()
					.getActiveProjects()) {
				cmb.addItem(project.toString(), project);
			}
	}

	public static void createUserComboBox(QComboBox cmb)
			throws WaktuGeneralException {
		cmb.clear();
			for (Usr usr : UserController.getInstance().getActiveUsers()) {
				cmb.addItem(usr.toString(), usr);
			}
	}

}
