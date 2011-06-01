package ch.hsr.waktu.controller;

import java.util.GregorianCalendar;

import org.junit.Test;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.WaktuException;

public class TestBusinessRuleController {
    @Test
    public void checkUser_validUser() throws WaktuException {
        BusinessRuleController.check(new Usr("hansmuster", "Hans", "Muster",
                "1234", 3, SystemRole.EMPLOYEE, 24));
    }

    @Test(expected = WaktuException.class)
    public void checkUser_negativeHoliday_WaktuException()
            throws WaktuException {
        BusinessRuleController.check(new Usr("hansmuster", "Hans", "Muster",
                "1234", 3, SystemRole.EMPLOYEE, -24));
    }

    @Test(expected = WaktuException.class)
    public void checkUser_negativePensum_WaktuException() throws WaktuException {
        BusinessRuleController.check(new Usr("hansmuster", "Hans", "Muster",
                "1234", -3, SystemRole.EMPLOYEE, 24));
    }

    @Test(expected = WaktuException.class)
    public void checkUser_pensum0Percent_WaktuException() throws WaktuException {
        BusinessRuleController.check(new Usr("hansmuster", "Hans", "Muster",
                "1234", 0, SystemRole.EMPLOYEE, 24));
    }

    @Test(expected = WaktuException.class)
    public void checkUser_pensumGreaterThan100Percent_WaktuException()
            throws WaktuException {
        BusinessRuleController.check(new Usr("hansmuster", "Hans", "Muster",
                "1234", 101, SystemRole.EMPLOYEE, 24));
    }

    @Test
    public void checkProject_validProject() throws WaktuException {
        // BusinessRuleController.check(new Project("projectID", "Test project",
        // new Usr(), 42));
    }

    @Test(expected = WaktuException.class)
    public void checkProject_negativePlannedTime() throws WaktuException {
        BusinessRuleController.check(new Project("projectID", "Test project",
                new Usr(), -42));
    }

    @Test(expected = WaktuException.class)
    public void checkWorkSession_startAfterEnd() throws WaktuException {
        BusinessRuleController.check(new WorkSession(new Usr(),
                new WorkPackage(),
                new GregorianCalendar(2011, 05, 19, 10, 0, 0),
                new GregorianCalendar(2011, 04, 18, 9, 0, 0),
                "Test Worksession"));
    }
}
