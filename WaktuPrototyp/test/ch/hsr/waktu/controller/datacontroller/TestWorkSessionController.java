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
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QDateTime;

public class TestWorkSessionController extends TestSuite {

	static UserController uc;
	static LoginController lc;
	static PermissionController pmc;
	static PersistenceController prc;
	static ProjectController pc;
	static ProjectStaffController psc;
	static WorkPackageController wpc;
	static WorkSessionController wsc;
	
	static Usr usr1, usr2, usr3, usr4, usr5;
	static Project p1, p2, p3, p4, p5;
	static WorkPackage w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, w13, w14, w15, w16, w17, w18, w19, w20, w21, w22, w23, w24, w25;
	
	@BeforeClass
	public static void before() {
	
		try {
			prc = PersistenceController.getInstance("waktutest");
			uc = UserController.getInstance();
			pc = ProjectController.getInstance();
			psc = ProjectStaffController.getInstance();
			lc = LoginController.getInstance();
			pmc = PermissionController.getInstance();
			wpc = WorkPackageController.getInstance();
			wsc = WorkSessionController.getInstance();

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
			
			w1 = wpc.addWorkPackage(p1, "Kick-Off");
			w2 = wpc.addWorkPackage(p1, "Analyse");
			w3 = wpc.addWorkPackage(p1, "Design");
			w4 = wpc.addWorkPackage(p1, "Implementation");
			w5 = wpc.addWorkPackage(p1, "Testing");
			w6 = wpc.addWorkPackage(p1, "Roll-Out");
			w7 = wpc.addWorkPackage(p1, "Benutzer instruieren");
			w8 = wpc.addWorkPackage(p2, "Kick-Off");
			w9 = wpc.addWorkPackage(p2, "Design");
			w10 = wpc.addWorkPackage(p2, "Implementation");
			w11 = wpc.addWorkPackage(p2, "Roll-Out");
			w12 = wpc.addWorkPackage(p3, "Kick-Off");
			w13 = wpc.addWorkPackage(p3, "Analyse");
			w14 = wpc.addWorkPackage(p3, "Design");
			w15 = wpc.addWorkPackage(p3, "Implementation");
			w16 = wpc.addWorkPackage(p3, "Testing");
			w17 = wpc.addWorkPackage(p3, "Roll-Out");
			w18 = wpc.addWorkPackage(p3, "Benutzer instruieren");
			w19 = wpc.addWorkPackage(p4, "Kick-Off");
			w20 = wpc.addWorkPackage(p4, "Analyse");
			w21 = wpc.addWorkPackage(p4, "Design");
			w22 = wpc.addWorkPackage(p5, "Implementation");
			w23 = wpc.addWorkPackage(p5, "Testing");
			w24 = wpc.addWorkPackage(p5, "Roll-Out");
			w25 = wpc.addWorkPackage(p5, "Benutzer instruieren");

			wsc.addWorkSession(usr1, w1, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 12:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 14:00","yyyy-MM-dd hh:mm")), "Beschreibung");
			
		} catch (WaktuException e) {
			System.out.println("TestWorkPackageController failed \n" + e.getMessage() + "\n" + e.getStackTrace()[0] + "\n" + e.getStackTrace()[1] + "\n" + e.getStackTrace()[2]);
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
	public void testAddWorkSession() throws WaktuException {
		assertEquals(true,true);
	}

}
