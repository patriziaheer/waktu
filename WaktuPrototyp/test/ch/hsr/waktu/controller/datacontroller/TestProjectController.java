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
import ch.hsr.waktu.services.WaktuException;

public class TestProjectController extends TestSuiteDataController {

	static UserController uc;
	static LoginController lc;
	static PermissionController pmc;
	static PersistenceController prc;
	static ProjectController pc;
	static ProjectStaffController psc;
	
	static Usr usr2;
	static Project p4;
	static Project p5;
	
	static Logger logger = Logger.getLogger(TestProjectController.class);

	@SuppressWarnings("unused")
	@BeforeClass
	public static void before() {
	
		try {
			prc = PersistenceController.getInstance();
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

			Usr usr1 = uc.addUser("Mike", "Fisler", "1234", 100, SystemRole.ADMIN, 25);
			    usr2 = uc.addUser("Simon", "Stäheli", "5678", 10, SystemRole.EMPLOYEE, 30);
			Usr usr3 = uc.addUser("Fredi", "Egli", "Karabum!", 50,	SystemRole.PROJECTMANAGER, 80);
			Usr usr4 = uc.addUser("David", "Steiner", "fels3n3gg?", 70,SystemRole.PROJECTMANAGER, 80);
			Usr usr5 = uc.addUser("Maura", "Weber", "turicum", 25,	SystemRole.PROJECTMANAGER, 80);

			Project p1 = pc.addProject("0001-SBB", "SBB Verwaltungssoftware", usr2, 125);
			Project p2 = pc.addProject("0002-HSR", "Stundenplansoftware", usr1, 130);
			Project p3 = pc.addProject("0003-UNIZH", "Stundenplansoftware", usr1, 543);
			        p4 = pc.addProject("0004-UBS", "E-Banking", usr1, 1000);
			        p5 = pc.addProject("0005-CS", "Boni-Verwaltungs-Programm", usr1, 739);
			
			p2.setActiveState(false);
			p3.setActiveState(false);
			pc.updateProject(p2);
			pc.updateProject(p3);
			
			psc.addProjectStaff(usr1, p1);
			psc.addProjectStaff(usr2, p1);
			psc.addProjectStaff(usr3, p1);

		} catch (WaktuException e) {
			logger.error("TestProjectController failed \n" + e.getMessage() + "\n" + e.getStackTrace()[0] + "\n" + e.getStackTrace()[1] + "\n" + e.getStackTrace()[2]);
		}
	}

	@Test
	public void testAddProject() throws WaktuException {
		assertEquals(5,pc.getAllProjects().size());
	}
	
	@Test
	public void testGetActiveProjects() throws WaktuException {
		assertEquals(3,pc.getActiveProjects().size());
	}
	
	@Test
	public void testGetInactiveProjects() throws WaktuException {
		assertEquals(2,pc.getInactiveProjects().size());
	}
	
	@Test
	public void testGetActiveProjectsByUser() throws WaktuException {
		assertEquals(1,pc.getActiveProjects(usr2).size());
	}
	
	@Test
	public void testGetProjectById() throws WaktuException {
		assertEquals(p5.getId(),pc.getProject(p5.getId()).getId());
	}
	
	@Test
	public void updateProject() throws WaktuException {
		
		p4.setDescription("Neue Beschreibung");
		p4.setPlannedTime(300);
		pc.updateProject(p4);
		
		assertEquals(p4,pc.getProject(p4.getId()));
	}
	
	@Test
	public void testEqualsAndHashCode() throws WaktuException {
		assertEquals(p4, pc.getProject(p4.getId()));
		assertEquals(p4.hashCode(), pc.getProject(p4.getId()).hashCode());
		
		assert(!p4.equals(usr2));
	}

	@Test(expected=WaktuException.class)
	public void testErrorActiveProjects() throws WaktuException {
		pc.getActiveProjects(null);
	}

	@Test(expected=WaktuException.class)
	public void testErrorAddProject() throws WaktuException {
		pc.addProject(null, null, new Usr(), 0);
	}

}
