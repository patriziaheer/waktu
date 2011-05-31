package ch.hsr.waktu.controller.datacontroller;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hsr.waktu.controller.LoginController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;

public class TestWorkSessionController extends TestSuiteDataController {

	static UserController uc;
	static LoginController lc;
	static PermissionController pmc;
	static PersistenceController prc;
	static ProjectController pc;
	static ProjectStaffController psc;
	static WorkPackageController wpc;
	static WorkSessionController wsc;
	
	static Usr usr1, usr2, usr3, usr4, usr5, usr6;
	static Project p1, p2, p3, p4, p5, p6;
	static WorkPackage w10, w20, w30, w40, w41, w50, w51, w6;
	static WorkSession ws1, ws6;
	
	static Logger logger = Logger.getLogger(TestWorkSessionController.class);

	
	@BeforeClass
	public static void before() {
	
		try {
			prc = PersistenceController.getInstance();
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
			usr6 = uc.addUser("Patrizia", "Heer", "1234", 25, SystemRole.PROJECTMANAGER, 80);

			p1 = pc.addProject("0001-SBB", "SBB Verwaltungssoftware", usr2, 125);
			p2 = pc.addProject("0002-HSR", "Stundenplansoftware", usr1, 130);
			p3 = pc.addProject("0003-UNIZH", "Stundenplansoftware", usr1, 543);
			p4 = pc.addProject("0004-UBS", "E-Banking", usr1, 1000);
			p5 = pc.addProject("0005-CS", "Boni-Verwaltungs-Programm", usr1, 739);
			p6 = pc.addProject("0006-Test", "Test", usr6, 750);
			
			w10 = wpc.addWorkPackage(p1, "Kick-Off");
			w20 = wpc.addWorkPackage(p2, "Analyse");
			w30 = wpc.addWorkPackage(p3, "Design");
			w40 = wpc.addWorkPackage(p4, "Implementation");
			w41 = wpc.addWorkPackage(p4, "Testing");
			w50 = wpc.addWorkPackage(p5, "Roll-Out");
			w51 = wpc.addWorkPackage(p5, "Documentation");
			w6 = wpc.addWorkPackage(p6, "Test");

			ws1 =   wsc.addWorkSession(usr1, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 07:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 10:00","yyyy-MM-dd hh:mm")), "doijfrjwa");
					wsc.addWorkSession(usr1, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 09:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 12:00","yyyy-MM-dd hh:mm")), "ökapjafcN");
					wsc.addWorkSession(usr1, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 14:00","yyyy-MM-dd hh:mm")), "aewfdNAa");
					wsc.addWorkSession(usr1, w20, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 11:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 16:00","yyyy-MM-dd hh:mm")), "FSGKAVCvf");
					wsc.addWorkSession(usr1, w20, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 13:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 18:00","yyyy-MM-dd hh:mm")), "dma-aDcxsc");
					
					wsc.addWorkSession(usr2, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 07:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 10:00","yyyy-MM-dd hh:mm")), "dfma-aDX");
					wsc.addWorkSession(usr2, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 09:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 12:00","yyyy-MM-dd hh:mm")), "maäodeoifjv");
					wsc.addWorkSession(usr2, w20, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 14:00","yyyy-MM-dd hh:mm")), " daaefadc ads");
					wsc.addWorkSession(usr2, w20, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 11:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 16:00","yyyy-MM-dd hh:mm")), "AEvavAVv fs");
					wsc.addWorkSession(usr2, w20, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 13:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 18:00","yyyy-MM-dd hh:mm")), "QEAFpdevfa");
					
					wsc.addWorkSession(usr3, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 07:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 10:00","yyyy-MM-dd hh:mm")), "cavcmDSAVc");
					wsc.addWorkSession(usr3, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 09:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 12:00","yyyy-MM-dd hh:mm")), "mfdaa<vdd");
					wsc.addWorkSession(usr3, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-23 14:00","yyyy-MM-dd hh:mm")), "dfgsa4r5advfw");
					wsc.addWorkSession(usr3, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 11:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-24 16:00","yyyy-MM-dd hh:mm")), "dmdADSdkw9");
					
					wsc.addWorkSession(usr4, w30, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 13:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 18:00","yyyy-MM-dd hh:mm")), "dfsar*rgwer*");
					wsc.addWorkSession(usr4, w30, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 08:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-25 09:00","yyyy-MM-dd hh:mm")), "adscdkä");
					wsc.addWorkSession(usr4, w20, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-26 09:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-26 09:30","yyyy-MM-dd hh:mm")), "<syödndsad");
					wsc.addWorkSession(usr4, w20, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-26 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-26 11:15","yyyy-MM-dd hh:mm")), "dDdasd");
					wsc.addWorkSession(usr4, w30, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-26 11:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-26 12:00","yyyy-MM-dd hh:mm")), "mkdDSAd");
					
					wsc.addWorkSession(usr5, w40, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-27 12:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-27 13:10","yyyy-MM-dd hh:mm")), "äänmdCsd");
					wsc.addWorkSession(usr5, w40, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-27 13:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-27 14:00","yyyy-MM-dd hh:mm")), "5tWvfad");
					wsc.addWorkSession(usr5, w41, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-28 14:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-28 16:10","yyyy-MM-dd hh:mm")), "dldeleEd");
					wsc.addWorkSession(usr5, w41, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-28 15:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-28 17:15","yyyy-MM-dd hh:mm")), "7ujrznS");
					wsc.addWorkSession(usr5, w41, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-29 16:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-29 18:05","yyyy-MM-dd hh:mm")), "dsfe-deaewf");
			ws6 = wsc.addWorkSession(usr6, w6, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-27 12:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-27 13:10","yyyy-MM-dd hh:mm")), "Test");
			
		} catch (WaktuException e) {
			logger.error("TestWorkPackageController failed \n" + e.getMessage() + "\n" + e.getStackTrace()[0] + "\n" + e.getStackTrace()[1] + "\n" + e.getStackTrace()[2]);
		}
	}

	@Test
	public void testGetWorkSessionByUser() throws WaktuException {
		assertEquals(5, wsc.getWorkSessions(usr1).size());
		assertEquals(5, wsc.getWorkSessions(usr2).size());
		assertEquals(4, wsc.getWorkSessions(usr3).size());
		assertEquals(5, wsc.getWorkSessions(usr4).size());
		assertEquals(5, wsc.getWorkSessions(usr5).size());
	}

	@Test
	public void testGetWorkSessionByUserAndDate() throws WaktuException {
		assertEquals(1,wsc.getWorkSessions(usr1, QDate.fromString("2011-05-25","yyyy-MM-dd")).size());
		assertEquals(3,wsc.getWorkSessions(usr3, QDate.fromString("2011-05-23","yyyy-MM-dd")).size());
	}
	
	@Test
	public void testGetWorkSessionByUserStartAndEndDate() throws WaktuException {
		assertEquals(3,wsc.getWorkSessions(usr1, QDate.fromString("2011-05-23","yyyy-MM-dd"), QDate.fromString("2011-05-25","yyyy-MM-dd")).size());
		assertEquals(4,wsc.getWorkSessions(usr3, QDate.fromString("2011-05-20","yyyy-MM-dd"), QDate.fromString("2011-05-25","yyyy-MM-dd")).size());
		assertEquals(0,wsc.getWorkSessions(usr2, QDate.fromString("2011-05-25","yyyy-MM-dd"), QDate.fromString("2011-05-29","yyyy-MM-dd")).size());
	}
	
	@Test
	public void testGetWorkSessionByWorkPackage() throws WaktuException {
		assertEquals(9,wsc.getWorkSessions(w10).size());
		assertEquals(7,wsc.getWorkSessions(w20).size());
	}
	
	@Test
	public void testGetWorkSessionByWorkPackageAndUser() throws WaktuException {
		assertEquals(3,wsc.getWorkSessions(w10, usr1).size());
		assertEquals(2,wsc.getWorkSessions(w10, usr2).size());
		assertEquals(4,wsc.getWorkSessions(w10, usr3).size());
		assertEquals(2,wsc.getWorkSessions(w20, usr1).size());
		assertEquals(3,wsc.getWorkSessions(w20, usr2).size());
		assertEquals(0,wsc.getWorkSessions(w20, usr3).size());
	}
	
	@Test
	public void testGetWorkSessionByWorkPackageAndDate() throws WaktuException {
		assertEquals(6,wsc.getWorkSessions(w10, QDate.fromString("2011-05-22","yyyy-MM-dd"), QDate.fromString("2011-05-23","yyyy-MM-dd")).size());
		assertEquals(7,wsc.getWorkSessions(w10, QDate.fromString("2011-05-23","yyyy-MM-dd"), QDate.fromString("2011-05-24","yyyy-MM-dd")).size());
		assertEquals(5,wsc.getWorkSessions(w20, QDate.fromString("2011-05-24","yyyy-MM-dd"), QDate.fromString("2011-05-25","yyyy-MM-dd")).size());
		assertEquals(1,wsc.getWorkSessions(w20, QDate.fromString("2011-05-25","yyyy-MM-dd"), QDate.fromString("2011-05-25","yyyy-MM-dd")).size());
	}
	
	@Test
	public void testGetWorkSessionByProject() throws WaktuException {
		assertEquals(9,wsc.getWorkSessions(p1).size());
		assertEquals(7,wsc.getWorkSessions(p2).size());
		assertEquals(3,wsc.getWorkSessions(p3).size());
		assertEquals(5,wsc.getWorkSessions(p4).size());
		assertEquals(0,wsc.getWorkSessions(p5).size());
	}
	
	
	@Test
	public void testGetWorkSessionByProjectAndUser() throws WaktuException {
		assertEquals(3,wsc.getWorkSessions(p1, usr1).size());
		assertEquals(0,wsc.getWorkSessions(p3, usr3).size());
		assertEquals(0,wsc.getWorkSessions(p4, usr1).size());
		assertEquals(5,wsc.getWorkSessions(p4, usr5).size());
		assertEquals(3,wsc.getWorkSessions(p3, usr4).size());
	}

	@Test
	public void testGetWorkSessionByProjectAndDate() throws WaktuException {
		assertEquals(7,wsc.getWorkSessions(p1, QDate.fromString("2011-05-23","yyyy-MM-dd"), QDate.fromString("2011-05-24","yyyy-MM-dd")).size());
		assertEquals(5,wsc.getWorkSessions(p2, QDate.fromString("2011-05-24","yyyy-MM-dd"), QDate.fromString("2011-05-25","yyyy-MM-dd")).size());
		assertEquals(1,wsc.getWorkSessions(p3, QDate.fromString("2011-05-26","yyyy-MM-dd"), QDate.fromString("2011-05-29","yyyy-MM-dd")).size());
		assertEquals(4,wsc.getWorkSessions(p4, QDate.fromString("2011-05-27","yyyy-MM-dd"), QDate.fromString("2011-05-28","yyyy-MM-dd")).size());
		assertEquals(0,wsc.getWorkSessions(p5, QDate.fromString("2011-05-12","yyyy-MM-dd"), QDate.fromString("2011-05-18","yyyy-MM-dd")).size());
	}
	
	@Test
	public void testGetWorkSessionByProjectUserAndDate() throws WaktuException {
		assertEquals(1,wsc.getWorkSessions(p1, usr1, QDate.fromString("2011-05-23","yyyy-MM-dd"), QDate.fromString("2011-05-24","yyyy-MM-dd")).size());
		assertEquals(3,wsc.getWorkSessions(p2, usr2, QDate.fromString("2011-05-24","yyyy-MM-dd"), QDate.fromString("2011-05-25","yyyy-MM-dd")).size());
		assertEquals(0,wsc.getWorkSessions(p3, usr3, QDate.fromString("2011-05-26","yyyy-MM-dd"), QDate.fromString("2011-05-29","yyyy-MM-dd")).size());
		assertEquals(4,wsc.getWorkSessions(p4, usr5, QDate.fromString("2011-05-27","yyyy-MM-dd"), QDate.fromString("2011-05-28","yyyy-MM-dd")).size());
		assertEquals(0,wsc.getWorkSessions(p5, usr1, QDate.fromString("2011-05-12","yyyy-MM-dd"), QDate.fromString("2011-05-18","yyyy-MM-dd")).size());
	}

	
	@Test
	public void testUpdateWorkSession() throws WaktuException {
		
		Usr usr6 = uc.addUser("Heinz", "Kaufmann", "berlin", 25, SystemRole.EMPLOYEE, 90);
		
		ws1.setDescription("Neue Beschreibung");
		ws1.setUser(usr6);
		ws1.setWorkPackage(w51);
		ws1.setStart(TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-02 15:00","yyyy-MM-dd hh:mm")));
		ws1.setEnd(TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-02 16:00","yyyy-MM-dd hh:mm")));
		
		wsc.updateWorkSession(ws1);
		
		WorkSession temp = wsc.getWorkSessions(ws1.getWorkPackage().getProject(), ws1.getUser(), QDate.fromString("2011-05-02","yyyy-MM-dd"), QDate.fromString("2011-05-02","yyyy-MM-dd")).get(0);
		
		assertEquals(ws1, temp);
		
		//Set previous test data to assure the data integrity
		ws1.setDescription("doijfrjwa");
		ws1.setUser(usr1);
		ws1.setWorkPackage(w10);
		ws1.setStart(TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 07:00","yyyy-MM-dd hh:mm")));
		ws1.setEnd(TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-05-22 10:00","yyyy-MM-dd hh:mm")));
		
		
	}
	
	@Test
	public void testRemoveWorkSession() throws WaktuException {
		wsc.addWorkSession(usr1, w10, TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-04-22 10:00","yyyy-MM-dd hh:mm")), TimeUtil.convertQDateTimeToGregorian(QDateTime.fromString("2011-04-22 10:00","yyyy-MM-dd hh:mm")), "fvöasç*vf4FW");
		WorkSession tempWs = wsc.getWorkSessions(ws1.getWorkPackage().getProject(), ws1.getUser(), QDate.fromString("2011-04-22","yyyy-MM-dd"), QDate.fromString("2011-04-22","yyyy-MM-dd")).get(0);
		wsc.removeWorkSession(tempWs);
		
		assertEquals(0,wsc.getWorkSessions(ws1.getWorkPackage().getProject(), ws1.getUser(), QDate.fromString("2011-04-22","yyyy-MM-dd"), QDate.fromString("2011-04-22","yyyy-MM-dd")).size());
	}
	
	@Test
	public void testEqualsAndHashCode() throws WaktuException {
		WorkSession wsToTest = wsc.getWorkSessions(w6, QDate.fromString("2011-05-27","yyyy-MM-dd"), QDate.fromString("2011-05-27","yyyy-MM-dd")).get(0);
		assertEquals(ws6, wsToTest);
		assertEquals(ws6.hashCode(), wsToTest.hashCode());
		assert(wsToTest.equals(usr1) == false);
	}

	@Test(expected=WaktuException.class)
	public void testErrorAddWorkSession() throws WaktuException {
		wsc.addWorkSession(null, null, null, null, null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorUpdateWorkSession() throws WaktuException {
		wsc.updateWorkSession(null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllUsrWorkSession() throws WaktuException {
		wsc.getWorkSessions((Usr)null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllProjectWorkSession() throws WaktuException {
		wsc.getWorkSessions((Project)null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllWorkPackageWorkSession() throws WaktuException {
		wsc.getWorkSessions((WorkPackage)null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllProjectUsrWorkSession() throws WaktuException {
		wsc.getWorkSessions((Project)null, (Usr)null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllWorkPackageUsrWorkSession() throws WaktuException {
		wsc.getWorkSessions((WorkPackage)null, (Usr)null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllUsrQDateWorkSession() throws WaktuException {
		wsc.getWorkSessions((Usr)null, (QDate)null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllUsrStartEndWorkSession() throws WaktuException {
		wsc.getWorkSessions((Usr)null, (QDate)null, (QDate)null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllProjectStartEndWorkSession() throws WaktuException {
		wsc.getWorkSessions((Project)null, (QDate)null, (QDate)null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllWorkPackageStartEndWorkSession() throws WaktuException {
		wsc.getWorkSessions((WorkPackage)null, (QDate)null, (QDate)null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorGetAllProjectUsrStartEndWorkSession() throws WaktuException {
		wsc.getWorkSessions((Project)null, (Usr)null, (QDate)null, (QDate)null);
	}
}
