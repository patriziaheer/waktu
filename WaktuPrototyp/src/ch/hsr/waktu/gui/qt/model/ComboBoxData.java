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

/**
 * Combobox Daten generieren
 * @author patriziaheer
 *
 */
public class ComboBoxData {

	/**
	 * 
	 * @param cmb
	 * @param currRole
	 */
	public static void createSystemRoleComboBox(QComboBox cmb, SystemRole currRole) {
		cmb.clear();
		int i = 0;
		int currIndex = -1;
		for (SystemRole role : SystemRole.values()) {
			if (currRole == role) {
				currIndex = i;
			}
			i++;
			cmb.addItem(role.getName(), role);
		}
		cmb.setCurrentIndex(currIndex);
	}
	
	/**
	 * 
	 * @param cmb
	 * @param currUsr
	 * @throws WaktuException
	 */
	public static void createProjectManagerComboBox(QComboBox cmb, Usr currUsr)
			throws WaktuException {
		cmb.clear();
		int i = 0;
		int currIndex = -1;
		for (Usr usr : UserController.getInstance().getProjectManagers()) {
			cmb.addItem(usr.toString(), usr);
			if (usr.equals(currUsr)) {
				currIndex = i;
			}
			i++;
		}
		cmb.setCurrentIndex(currIndex);
	}

	/**
	 * 
	 * @param cmb
	 * @param currUser
	 * @param project
	 * @throws WaktuException
	 */
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

	/**
	 * 
	 * @param cmb
	 * @param proj
	 * @throws WaktuException
	 */
	public static void createAllWorkPackageComboBox(QComboBox cmb, Project proj)
			throws WaktuException {
		cmb.clear();
		for (WorkPackage wp : WorkPackageController.getInstance().getAllWorkPackages(proj)) {
			cmb.addItem(wp.toString(), wp);
		}
	}

	/**
	 * 
	 * @param cmb
	 * @throws WaktuException
	 */
	public static void createAllWorkPackageComboBox(QComboBox cmb)
			throws WaktuException {
		cmb.clear();
		for (WorkPackage wp : WorkPackageController.getInstance().getAllWorkPackages()) {
			cmb.addItem(wp.getProject().getProjectIdentifier() + " " + wp.getDescription(), wp);
		}
	}

	/**
	 * 
	 * @param cmb
	 * @param project
	 * @param workPackage
	 * @throws WaktuException
	 */
	public static void createActiveWorkPackageComboBox(QComboBox cmb,
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

	/**
	 * 
	 * @param cmb
	 * @throws WaktuException
	 */
	public static void createActiveProjectComboBox(QComboBox cmb)
			throws WaktuException {
		cmb.clear();
		for (Project project : ProjectController.getInstance()
				.getActiveProjects()) {
			cmb.addItem(project.toString(), project);
		}
	}

	/**
	 * 
	 * @param cmb
	 * @throws WaktuException
	 */
	public static void createUserComboBox(QComboBox cmb) throws WaktuException {
		cmb.clear();
		for (Usr usr : UserController.getInstance().getActiveUsers()) {
			cmb.addItem(usr.toString(), usr);
		}
	}

	/**
	 * 
	 * @param cmb
	 * @param project
	 * @throws WaktuException
	 */
	public static void createUserProjectStaffComboBox(QComboBox cmb, Project project) throws WaktuException {
		cmb.clear();
		for (Usr usr : ProjectStaffController.getInstance().getUsersNotMemberOf(project)) {
			cmb.addItem(usr.toString(), usr);
		}
	}

	/**
	 * 
	 * @param cmb
	 * @param user
	 * @throws WaktuException
	 */
	public static void createProjectProjectStaffComboBox(QComboBox cmb, Usr user) throws WaktuException {
		cmb.clear();
		for (Project project: ProjectStaffController.getInstance().getProjectsWhereUserIsNotMember(user)) {
			cmb.addItem(project.toString(), project);
		}
	}

	/**
	 * 
	 * @param cmb
	 * @throws WaktuException
	 */
	public static void createAllProjectComboBox(QComboBox cmb) throws WaktuException  {
		cmb.clear();
		for (Project project : ProjectController.getInstance()
				.getAllProjects()) {
			cmb.addItem(project.toString(), project);
		}
	}

}
