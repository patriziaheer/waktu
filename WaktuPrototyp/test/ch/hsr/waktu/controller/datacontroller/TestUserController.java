package ch.hsr.waktu.controller.datacontroller;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hsr.waktu.TestSuite;
import ch.hsr.waktu.controller.LoginController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.WaktuException;

public class TestUserController extends TestSuite {

	static UserController uc;
	static LoginController lc;
	static PermissionController pmc;
	static PersistenceController prc;
	static ProjectController pc;
	static ProjectStaffController psc;
	
	static Logger logger = Logger.getLogger(TestUserController.class);


	@SuppressWarnings("unused")
	@BeforeClass
	public static void before() {

		try {
			prc = PersistenceController.getInstance("waktutest");
			uc = UserController.getInstance();
			pc = ProjectController.getInstance();
			lc = LoginController.getInstance();
			pmc = PermissionController.getInstance();

			TestableUserController.getInstance().addUser("pa", "ti", "1234", 10, SystemRole.ADMIN, 30);
			pmc.addPermission(SystemRole.ADMIN);
			pmc.addPermission(SystemRole.EMPLOYEE);
			pmc.addPermission(SystemRole.PROJECTMANAGER);
			pmc.reloadPermissions();
			lc.login("pati", "1234");

			Usr usr1 = uc.addUser("Mike", "Fisler", "1234", 100, SystemRole.ADMIN, 25);
			Usr usr2 = uc.addUser("Simon", "Stäheli", "5678", 10, SystemRole.EMPLOYEE, 30);
			Usr usr3 = uc.addUser("Fredi", "Egli", "Karabum!", 50,	SystemRole.EMPLOYEE, 80);
			Usr usr4 = uc.addUser("David", "Steiner", "fels3n3gg?", 70,SystemRole.PROJECTMANAGER, 80);
			Usr usr5 = uc.addUser("Maura", "Weber", "turicum", 25,	SystemRole.PROJECTMANAGER, 80);
			
			usr4.setActiveState(false);
			usr5.setActiveState(false);
			
			uc.updateUser(usr4);
			uc.updateUser(usr5);
			
			pc.addProject("0001-SBB", "SBB Verwaltungssoftware", usr2, 125);
			pc.addProject("0002-HSR", "Stundenplansoftware", usr1, 130);

		} catch (WaktuException e) {
			logger.error("TestUserController failed" + e.getMessage());
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

		assertEquals(6, uc.getAllUsers().size());
	}

	@Test
	public void testGetProjectManagers() throws WaktuException {
		assertEquals(4, uc.getProjectManagers().size());

	}
	
	@Test
	public void testGetInactiveUsers() throws WaktuException{
		assertEquals(2, uc.getInactiveUsers().size());
	}
	
	@Test
	public void testGetActiveUsers() throws WaktuException {
		assertEquals(4, uc.getActiveUsers().size());
	}

	
	@Test
	public void testGetUserByUsername() throws WaktuException {
		assertEquals("mikefisler",uc.getUser("mikefisler").getUsername());
	}
	
	@Test
	public void testUpdateUser() throws WaktuException {
		Usr usr3 = uc.getUser("frediegli");
		usr3.setName("Kunz");
		usr3.setFirstname("Frid");
		usr3.setHoliday(10);
		usr3.setSystemRole(SystemRole.EMPLOYEE);
		
		uc.updateUser(usr3);
		
		assertEquals(usr3, uc.getUser("frediegli"));
		assertEquals(usr3.getId(), uc.getUser("frediegli").getId());
	}

}
