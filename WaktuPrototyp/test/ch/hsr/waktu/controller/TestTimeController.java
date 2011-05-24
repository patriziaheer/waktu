package ch.hsr.waktu.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.UserControllerXml;
import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.controller.datacontroller.WorkSessionControllerXml;
import ch.hsr.waktu.services.WaktuException;

public class TestTimeController {
	
	private UserController previousUserController;
	private WorkSessionController previousWorkSessionController;
	private ProjectController previousProjectController;
	
	@Before
	public void before() {
		storePreviousControllers();
		setFakeControllers();
	}
	
	private void storePreviousControllers() {
		previousUserController = UserController.getInstance();
		previousWorkSessionController = WorkSessionController.getInstance();
		previousProjectController = ProjectController.getInstance();
	}
	
	private void setFakeControllers() {
		UserController.setInstance(new UserControllerXml());
		WorkSessionController.setInstance(new WorkSessionControllerXml());
//		ProjectController.setInstance(new ProjectControllerXml());
	}
	
	@After
	public void restorePreviousControllers() {
		UserController.setInstance(previousUserController);
		WorkSessionController.setInstance(previousWorkSessionController);
		ProjectController.setInstance(previousProjectController);
	}
	
	@Test
	public void calculateWorktimeForProject() throws WaktuException {
//		Project project = ProjectController.getInstance().getProject(1);
//		Usr user = UserController.getInstance().getUser("patriziaheer");
//		double time = TimeController.calculateWorktimeForProject(
//				project, null, 
//				user, null, null);
//		assertEquals(7.0 , time, 0.01);
	}
}
