package ch.hsr.waktu;

import java.net.URL;

import javax.persistence.EntityManager;

import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.gui.QApplication;

public class TestSuite {

    private static boolean runFirstTime = true;

    @BeforeClass
    public static void testSuiteSetUp() {

        if (runFirstTime) {
            @SuppressWarnings("unused")
            UserController uc = UserController.getInstance();

            String[] args = {};
            QApplication.initialize(args);
            ClassLoader loader = TestSuite.class.getClassLoader();
            URL url = loader.getResource("testsettings");
            PropertyConfigurator.configure(url);

            PersistenceController.setInstance("waktutest");

            runFirstTime = false;
        }
        EntityManager em;
        try {
            em = PersistenceController.getInstance().getEMF()
                    .createEntityManager();

            em.getTransaction().begin();
            em.createNativeQuery(
                    "TRUNCATE TABLE favorite, permission, projectstaff, worksession, workpackage, project, usr")
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (WaktuException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void afterAll() {
        EntityManager em;
        try {
            em = PersistenceController.getInstance().getEMF()
                    .createEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery(
                    "TRUNCATE TABLE favorite, permission, projectstaff, worksession, workpackage, project, usr")
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (WaktuException e) {
            e.printStackTrace();
        }
    }
}
