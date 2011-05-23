package ch.hsr.waktu.controller.datacontroller;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hsr.waktu.TestSuite;
import ch.hsr.waktu.controller.LoginController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.WaktuException;

public class TestProjectStaffController extends TestSuite {

	static UserController uc;
	static LoginController lc;
	static PermissionController pmc;
	static PersistenceController prc;
	static ProjectController pc;
	static ProjectStaffController psc;
	
	static Usr usr1;
	static Usr usr2;
	static Usr usr3;
	static Usr usr4;
	static Usr usr5;
	static Project p1;
	static Project p2;
	static Project p3;
	static Project p4;
	static Project p5;

	@BeforeClass
	public static void before() {
	
		try {
			prc = PersistenceController.getInstance("waktutest");
			uc = UserController.getInstance();
			pc = ProjectController.getInstance();
			psc = ProjectStaffController.getInstance();
			lc = LoginController.getInstance();
			pmc = PermissionController.getInstance();

			TestableUserController.getInstance().addUser("pa", "ti", "1234", 10, SystemRole.ADMIN, 30);
			pmc.addPermission(SystemRole.ADMIN);
			pmc.addPermission(SystemRole.EMPLOYEE);
			pmc.addPermission(SystemRole.PROJECTMANAGER);
			pmc.reloadPermissions();
			lc.login("pati", "1234");

			usr1 = uc.addUser("Mike", "Fisler", "1234", 100, SystemRole.ADMIN, 25);
			usr2 = uc.addUser("Simon", "Stäheli", "5678", 10, SystemRole.EMPLOYEE, 30);
			usr3 = uc.addUser("Fredi", "Egli", "Karabum!", 50,	SystemRole.PROJECTMANAGER, 80);
			usr4 = uc.addUser("David", "Steiner", "fels3n3gg?", 70,SystemRole.PROJECTMANAGER, 80);
			usr5 = uc.addUser("Maura", "Weber", "turicum", 25,	SystemRole.PROJECTMANAGER, 80);

			p1 = pc.addProject("0001-SBB", "SBB Verwaltungssoftware", usr2, 125);
			p2 = pc.addProject("0002-HSR", "Stundenplansoftware", usr1, 130);
			p3 = pc.addProject("0003-UNIZH", "Stundenplansoftware", usr1, 543);
			p4 = pc.addProject("0004-UBS", "E-Banking", usr1, 1000);
			p5 = pc.addProject("0005-CS", "Boni-Verwaltungs-Programm", usr1, 739);
			
			psc.addProjectStaff(usr1, p1);
			psc.addProjectStaff(usr1, p2);
			psc.addProjectStaff(usr1, p3);
			psc.addProjectStaff(usr2, p1);
			psc.addProjectStaff(usr2, p2);
			psc.addProjectStaff(usr2, p4);
			psc.addProjectStaff(usr3, p2);
			psc.addProjectStaff(usr3, p3);
			psc.addProjectStaff(usr3, p4);
			psc.addProjectStaff(usr4, p1);
			psc.addProjectStaff(usr4, p3);
			psc.addProjectStaff(usr4, p5);
			psc.addProjectStaff(usr5, p3);
			psc.addProjectStaff(usr5, p4);
			psc.addProjectStaff(usr5, p5);

		} catch (WaktuException e) {
			System.out.println("TestProjectStaffController failed \n" + e.getMessage() + "\n" + e.getStackTrace()[0] + "\n" + e.getStackTrace()[1] + "\n" + e.getStackTrace()[2]);
		}
	}

	@AfterClass
	public static void after() {
		EntityManager em = PersistenceController.getInstance("waktutest")
				.getEMF().createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery(
				"TRUNCATE TABLE favorite, permission, projectstaff, worksession, workpackage, project, usr")
				.executeUpdate();
		em.getTransaction().commit();
	}

	@Test
	public void testAddProjectStaff() throws WaktuException {
		assertEquals(3,psc.getProjects(usr1).size());
		assertEquals(3,psc.getProjects(usr2).size());
		assertEquals(3,psc.getProjects(usr3).size());
		assertEquals(3,psc.getProjects(usr4).size());
		assertEquals(3,psc.getProjects(usr5).size());
		assertEquals(3,psc.getUsers(p1).size());
		assertEquals(3,psc.getUsers(p2).size());
		assertEquals(4,psc.getUsers(p3).size());
		assertEquals(3,psc.getUsers(p4).size());
		assertEquals(2,psc.getUsers(p5).size());
	}
	
	@Test
	public void testGetProjectsByUser() throws WaktuException {
		assertEquals(3,psc.getProjects(usr1).size());
	}
	
	@Test
	public void testGetUsersByProject() throws WaktuException {
		assertEquals(2,psc.getUsers(p5).size());
	}
	
	@Test
	public void getUsersNotMemberOf() throws WaktuException {
		assertEquals(3,psc.getUsersNotMemberOf(p1).size());
		assertEquals(3,psc.getUsersNotMemberOf(p2).size());
		assertEquals(2,psc.getUsersNotMemberOf(p3).size());
		assertEquals(3,psc.getUsersNotMemberOf(p4).size());
		assertEquals(4,psc.getUsersNotMemberOf(p5).size());
	}
	
	@Test
	public void getProjectsWhereUserIsNotMember() throws WaktuException {
		assertEquals(2,psc.getProjectsWhereUserIsNotMember(usr1).size());
		assertEquals(2,psc.getProjectsWhereUserIsNotMember(usr2).size());
		assertEquals(2,psc.getProjectsWhereUserIsNotMember(usr3).size());
		assertEquals(2,psc.getProjectsWhereUserIsNotMember(usr4).size());
		assertEquals(2,psc.getProjectsWhereUserIsNotMember(usr5).size());
	}
	
	@Test
	public void testRemoveProjectStaff() throws WaktuException {
		psc.removeUser(usr1, p3);
		psc.removeUser(usr3, p3);
		assertEquals(2,psc.getUsers(p3).size());
	}

}
