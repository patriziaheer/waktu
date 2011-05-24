package ch.hsr.waktu.gui.qt.model;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.ProjectStaffController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.gui.QComboBox;

public class ComboBoxData {

	public static void createSystemRoleComboBox(QComboBox cmb) {
		cmb.clear();
		for (SystemRole role : SystemRole.values()) {
			cmb.addItem(role.name(), role);
		}
	}

	public static void createProjectManagerComboBox(QComboBox cmb)
			throws WaktuException {
		cmb.clear();
		for (Usr usr : UserController.getInstance().getProjectManagers()) {
			cmb.addItem(usr.toString(), usr);
		}
	}

	public static void createProjectForUserComboBox(QComboBox cmb, Usr currUser)
			throws WaktuException {
		cmb.clear();
		for (Project proj : ProjectController.getInstance().getActiveProjects(
				currUser)) {
			cmb.addItem(proj.toString(), proj);
		}
	}

	public static void createProjectForUserComboBox(QComboBox cmb,
			Usr currUser, Project project) throws WaktuException {
		cmb.clear();
		int i = 0;
		int currIndex = 0;
		for (Project proj : ProjectController.getInstance().getActiveProjects(
				currUser)) {
			cmb.addItem(proj.toString(), proj);
			if (proj.equals(project)) {
				currIndex = i;
			}
			i++;
		}
		cmb.setCurrentIndex(currIndex);
	}

	public static void createAllWorkPackageComboBox(QComboBox cmb, Project proj)
			throws WaktuException {
		cmb.clear();
		for (WorkPackage wp : WorkPackageController.getInstance().getAllWorkPackages(proj)) {
			cmb.addItem(wp.toString(), wp);
		}
	}


	public static void createWorkPackageComboBox(QComboBox cmb, Project project)
			throws WaktuException {
		cmb.clear();
		if (project != null) {
			for (WorkPackage wp : WorkPackageController.getInstance()
					.getActiveWorkPackages(project)) {
				cmb.addItem(wp.toString(), wp);
			}
		}
	}

	public static void createWorkPackageComboBox(QComboBox cmb,
			Project project, WorkPackage workPackage) throws WaktuException {
		cmb.clear();
		int i = 0;
		int currIndex = 0;
		if (project != null) {
			for (WorkPackage wp : WorkPackageController.getInstance()
					.getActiveWorkPackages(project)) {
				cmb.addItem(wp.toString(), wp);
				if (wp.equals(workPackage)) {
					currIndex = i;
				}
				i++;
			}
		}
		cmb.setCurrentIndex(currIndex);
	}

	public static void createActiveProjectComboBox(QComboBox cmb)
			throws WaktuException {
		cmb.clear();
		for (Project project : ProjectController.getInstance()
				.getActiveProjects()) {
			cmb.addItem(project.toString(), project);
		}
	}

	public static void createUserComboBox(QComboBox cmb) throws WaktuException {
		cmb.clear();
		for (Usr usr : UserController.getInstance().getActiveUsers()) {
			cmb.addItem(usr.toString(), usr);
		}
	}

	public static void createUserProjectStaffComboBox(QComboBox cmb, Project project) throws WaktuException {
		cmb.clear();
		for (Usr usr : ProjectStaffController.getInstance().getUsersNotMemberOf(project)) {
			cmb.addItem(usr.toString(), usr);
		}
	}

	public static void createProjectProjectStaffComboBox(QComboBox cmb, Usr user) throws WaktuException {
		cmb.clear();
		for (Project project: ProjectStaffController.getInstance().getProjectsWhereUserIsNotMember(user)) {
			cmb.addItem(project.toString(), project);
		}
	}

	public static void createAllProjectComboBox(QComboBox cmb) throws WaktuException  {
		cmb.clear();
		for (Project project : ProjectController.getInstance()
				.getAllProjects()) {
			cmb.addItem(project.toString(), project);
		}
	}

}
