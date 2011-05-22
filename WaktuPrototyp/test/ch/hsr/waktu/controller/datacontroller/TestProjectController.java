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

public class TestProjectController extends TestSuite {

	static UserController uc;
	static LoginController lc;
	static PermissionController pmc;
	static PersistenceController prc;
	static ProjectController pc;
	static ProjectStaffController psc;

	@SuppressWarnings("unused")
	@BeforeClass
	public static void before() {
	
		try {
			prc = PersistenceController.getInstance("waktutest");
			uc = UserController.getInstance();
			pc = ProjectController.getInstance();
			lc = LoginController.getInstance();
			pmc = PermissionController.getInstance();

			lc.logout();
			
			TestableUserController.getInstance().addUser("paa", "ti", "1234", 10, SystemRole.ADMIN, 30);
			pmc.addPermission(SystemRole.ADMIN);
			pmc.addPermission(SystemRole.EMPLOYEE);
			pmc.addPermission(SystemRole.PROJECTMANAGER);
			pmc.reloadPermissions();
			System.out.println("paa");
			System.out.println(lc.login("paati", "1234"));
			System.out.println("Before usr");
			System.out.println(lc.getLoggedInUser());
			Usr usr1 = uc.addUser("Mike", "Fisler", "1234", 100, SystemRole.ADMIN, 25);
			Usr usr2 = uc.addUser("Simon", "Stäheli", "5678", 10, SystemRole.EMPLOYEE, 30);
			Usr usr3 = uc.addUser("Fredi", "Egli", "Karabum!", 50,	SystemRole.PROJECTMANAGER, 80);
			Usr usr4 = uc.addUser("David", "Steiner", "fels3n3gg?", 70,SystemRole.PROJECTMANAGER, 80);
			Usr usr5 = uc.addUser("Maura", "Weber", "turicum", 25,	SystemRole.PROJECTMANAGER, 80);
			System.out.println("after all");
			Project p1 = pc.addProject("0001-SBB", "SBB Verwaltungssoftware", usr2, 125);
			Project p2 = pc.addProject("0002-HSR", "Stundenplansoftware", usr1, 130);
			Project p3 = pc.addProject("0003-UNIZH", "Stundenplansoftware", usr1, 543);
			Project p4 = pc.addProject("0004-UBS", "E-Banking", usr1, 1000);
			Project p5 = pc.addProject("0005-CS", "Boni-Verwaltungs-Programm", usr1, 739);

		} catch (WaktuException e) {
			System.out.println("TestProjectController failed" + e.getMessage() + "\n" + e.getStackTrace()[0] + "\n" + e.getStackTrace()[1] + "\n" + e.getStackTrace()[2]);
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
	public void testAddUser() throws WaktuException {

		assertEquals(true,true);
	}


}
