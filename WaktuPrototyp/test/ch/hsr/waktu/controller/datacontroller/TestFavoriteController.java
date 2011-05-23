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
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QDateTime;

public class TestFavoriteController extends TestSuite {

	static UserController uc;
	static LoginController lc;
	static PermissionController pmc;
	static PersistenceController prc;
	static ProjectController pc;
	static ProjectStaffController psc;
	static FavoriteController fc;
	static WorkPackageController wpc;
	
	static Project p1;
	static WorkPackage w1, w2, w3, w4, w5;
	static Favorite f1, f2, f3, f4, f5;
	static Usr usr1, usr2, usr3, usr4, usr5;

	@BeforeClass
	public static void before() {

		try {
			prc = PersistenceController.getInstance("waktutest");
			uc = UserController.getInstance();
			pc = ProjectController.getInstance();
			lc = LoginController.getInstance();
			pmc = PermissionController.getInstance();
			wpc = WorkPackageController.getInstance();
			fc = FavoriteController.getInstance();

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
			
			w1 = wpc.addWorkPackage(p1, "Kick-Off");
			w2 = wpc.addWorkPackage(p1, "Analyse");
			w3 = wpc.addWorkPackage(p1, "Design");
			w4 = wpc.addWorkPackage(p1, "Implementation");
			w5 = wpc.addWorkPackage(p1, "Testing");
			
			f1 = fc.addFavorite(usr1, w1, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-21 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-21 12:00","yyyy-MM-dd hh:mm")));
			f2 = fc.addFavorite(usr1, w1, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 12:00","yyyy-MM-dd hh:mm")));
			f3 = fc.addFavorite(usr1, w2, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 12:00","yyyy-MM-dd hh:mm")));
			f4 = fc.addFavorite(usr2, w2, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 12:00","yyyy-MM-dd hh:mm")));
			f5 = fc.addFavorite(usr2, w3, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 12:00","yyyy-MM-dd hh:mm")));

		} catch (WaktuException e) {
			System.out.println("TestUserController failed" + e.getMessage());
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
	public void testGetFavoritesOfUser() throws WaktuException {
		
		assertEquals(3, fc.getFavorites(usr1).size());
		assertEquals(2, fc.getFavorites(usr2).size());
	}
	
	@Test
	public void testUpdateAndRemoveFavorite() throws WaktuException {
		Favorite f6 = fc.addFavorite(usr2, w3, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 12:00","yyyy-MM-dd hh:mm")));

		f6.setUser(usr3);
		f6.setStartTime(TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-26 10:00","yyyy-MM-dd hh:mm")));
		f6.setEndTime(TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-26 10:00","yyyy-MM-dd hh:mm")));
		f6.setWorkPackageID(w4);
		
		fc.updateFavorite(f6);
		assertEquals(f6.getId(), fc.getFavorites(usr3).get(0).getId());
		assertEquals(f6.getUser(), fc.getFavorites(usr3).get(0).getUser());
		assertEquals(f6.getId(), fc.getFavorites(usr3).get(0).getId());
		
		fc.removeFavorite(f6);
		
		assertEquals(0, fc.getFavorites(usr3).size());
	}
}
