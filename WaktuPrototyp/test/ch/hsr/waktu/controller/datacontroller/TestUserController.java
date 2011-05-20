package ch.hsr.waktu.controller.datacontroller;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.waktu.TestSuite;
import ch.hsr.waktu.controller.LoginController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.services.WaktuException;

public class TestUserController extends TestSuite {

	UserController uc;
	LoginController lc;
	PermissionController pmc;
	PersistenceController pc;

	@Before
	public void before() {

		try {
			pc = PersistenceController.getInstance("waktutest");
			uc = UserController.getInstance();
			lc = LoginController.getInstance();
			pmc = PermissionController.getInstance();
			
			TestableUserController.getInstance().addUser("Pa","ti", "1234", 10, SystemRole.ADMIN, 30);
			pmc.addPermission(SystemRole.ADMIN);
			pmc.addPermission(SystemRole.EMPLOYEE);
			pmc.addPermission(SystemRole.PROJECTMANAGER);
			pmc.reloadPermissions();
			lc.login("Pati", "1234");
			
		} catch (WaktuException e) {
			System.out.println(e.getMessage());
		}
	}

	@After
	public void after() {
		EntityManager em = PersistenceController.getInstance("waktutest")
				.getEMF().createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery(
				"DROP TABLE favorite, permission, projectstaff, worksession, workpackage, project, usr")
				.executeUpdate();
		em.getTransaction().commit();
	}

	@Test
	public void addUser() throws WaktuException {

			System.out.println("start");
			uc.addUser("Mike", "Fisler", "1234", 100, SystemRole.ADMIN, 25);
			uc.addUser("Simon", "Stäheli", "5678", 10, SystemRole.ADMIN, 30);
			System.out.println("end");

		assertEquals(3, uc.getAllUsers().size());

	}

}
