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
import ch.hsr.waktu.services.WaktuException;

public class TestWorkPackageController extends TestSuiteDataController {

    static UserController uc;
    static LoginController lc;
    static PermissionController pmc;
    static PersistenceController prc;
    static ProjectController pc;
    static ProjectStaffController psc;
    static WorkPackageController wpc;

    static Usr usr1, usr2, usr3, usr4, usr5, usr6;
    static Project p1, p2, p3, p4, p5, p6;
    static WorkPackage w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, w13,
            w14, w15, w16, w17, w18, w19, w20, w21, w22, w23, w24, w25, w26;

    static Logger logger = Logger.getLogger(TestWorkPackageController.class);

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

            TestableUserController.getInstance().addUser("pa", "ti", "1234",
                    10, SystemRole.ADMIN, 30);
            pmc.addPermission(SystemRole.ADMIN);
            pmc.addPermission(SystemRole.EMPLOYEE);
            pmc.addPermission(SystemRole.PROJECTMANAGER);
            pmc.reloadPermissions();
            lc.login("pati", "1234");

            usr1 = uc.addUser("Mike", "Fisler", "1234", 100, SystemRole.ADMIN,
                    25);
            usr2 = uc.addUser("Simon", "Stäheli", "5678", 10,
                    SystemRole.EMPLOYEE, 30);
            usr3 = uc.addUser("Fredi", "Egli", "Karabum!", 50,
                    SystemRole.PROJECTMANAGER, 80);
            usr4 = uc.addUser("David", "Steiner", "fels3n3gg?", 70,
                    SystemRole.PROJECTMANAGER, 80);
            usr5 = uc.addUser("Maura", "Weber", "turicum", 25,
                    SystemRole.PROJECTMANAGER, 80);
            usr6 = uc.addUser("Patrizia", "Heer", "1234", 25,
                    SystemRole.PROJECTMANAGER, 80);

            p1 = pc.addProject("0001-SBB", "SBB Verwaltungssoftware", usr2, 125);
            p2 = pc.addProject("0002-HSR", "Stundenplansoftware", usr1, 130);
            p3 = pc.addProject("0003-UNIZH", "Stundenplansoftware", usr1, 543);
            p4 = pc.addProject("0004-UBS", "E-Banking", usr1, 1000);
            p5 = pc.addProject("0005-CS", "Boni-Verwaltungs-Programm", usr1,
                    739);
            p6 = pc.addProject("0006-Test", "Test", usr6, 750);

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
            w26 = wpc.addWorkPackage(p6, "Test");

            w1.setActiveState(false);
            w6.setActiveState(false);
            w9.setActiveState(false);
            w12.setActiveState(false);
            w13.setActiveState(false);
            w18.setActiveState(false);
            w19.setActiveState(false);

            wpc.updateWorkPackage(w1);
            wpc.updateWorkPackage(w6);
            wpc.updateWorkPackage(w9);
            wpc.updateWorkPackage(w12);
            wpc.updateWorkPackage(w13);
            wpc.updateWorkPackage(w18);
            wpc.updateWorkPackage(w19);

        } catch (WaktuException e) {
            logger.error("TestWorkPackageController failed \n" + e.getMessage()
                    + "\n" + e.getStackTrace()[0] + "\n" + e.getStackTrace()[1]
                    + "\n" + e.getStackTrace()[2]);
        }
    }

    @Test
    public void testGetAllWorkPackages() throws WaktuException {
        assertEquals(26, wpc.getAllWorkPackages().size());
    }

    @Test
    public void testAddWorkPackage() throws WaktuException {
        assertEquals(7, wpc.getAllWorkPackages(p1).size());
        assertEquals(4, wpc.getAllWorkPackages(p2).size());
        assertEquals(7, wpc.getAllWorkPackages(p3).size());
        assertEquals(3, wpc.getAllWorkPackages(p4).size());
        assertEquals(4, wpc.getAllWorkPackages(p5).size());
    }

    @Test
    public void testActiveWorkPackage() throws WaktuException {
        assertEquals(5, wpc.getActiveWorkPackages(p1).size());
        assertEquals(3, wpc.getActiveWorkPackages(p2).size());
        assertEquals(4, wpc.getActiveWorkPackages(p3).size());
        assertEquals(2, wpc.getActiveWorkPackages(p4).size());
        assertEquals(4, wpc.getActiveWorkPackages(p5).size());
    }

    @Test
    public void testInactiveWorkPackage() throws WaktuException {
        assertEquals(2, wpc.getInactiveWorkPackages(p1).size());
        assertEquals(1, wpc.getInactiveWorkPackages(p2).size());
        assertEquals(3, wpc.getInactiveWorkPackages(p3).size());
        assertEquals(1, wpc.getInactiveWorkPackages(p4).size());
        assertEquals(0, wpc.getInactiveWorkPackages(p5).size());
    }

    @Test
    public void testGetWorkPackageId() throws WaktuException {
        assertEquals(w1.getId(), wpc.getWorkPackage(w1.getId()).getId());
        assertEquals(w2.getId(), wpc.getWorkPackage(w2.getId()).getId());

    }

    @Test
    public void testUpdateWorkPackage() throws WaktuException {
        w14.setDescription("Neue Beschreibung");
        w14.setProject(p1);
        w15.setDescription("Neuere Beschreibung");
        w15.setProject(p2);

        wpc.updateWorkPackage(w14);
        wpc.updateWorkPackage(w15);

        assertEquals(w14.getId(), wpc.getWorkPackage(w14.getId()).getId());
        assertEquals(w15.getId(), wpc.getWorkPackage(w15.getId()).getId());
    }

    @Test
    public void testEqualsAndHashCode() throws WaktuException {
        assertEquals(w26, wpc.getAllWorkPackages(p6).get(0));
        assertEquals(w26.hashCode(), wpc.getAllWorkPackages(p6).get(0)
                .hashCode());
        assert (!w26.equals(usr1));
    }

    @Test(expected = WaktuException.class)
    public void testErrorAddWorkPackage() throws WaktuException {
        wpc.addWorkPackage(new Project(), "");
    }

    @Test(expected = WaktuException.class)
    public void testErrorUpdateWorkPackage() throws WaktuException {
        wpc.updateWorkPackage(null);
    }

    @Test(expected = WaktuException.class)
    public void testErrorGetAllWorkPackage() throws WaktuException {
        wpc.getAllWorkPackages(null);
    }

    @Test(expected = WaktuException.class)
    public void testErrorGetActiveWorkPackage() throws WaktuException {
        wpc.getActiveWorkPackages(null);
    }

    @Test(expected = WaktuException.class)
    public void testErrorGetInactiveWorkPackage() throws WaktuException {
        wpc.getInactiveWorkPackages(null);
    }

}
