package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.services.WaktuException;
import ch.hsr.waktu.services.XmlUtil;

public class ProjectControllerXml extends ProjectController {
	private static final String projectFilePath = "./test/testdata/projects.xml";

	public Project getProject(final String projectIdentifier)
			throws WaktuException {
		for (Project p : getAllProjects()) {
			if (p.getProjectIdentifier().equals(projectIdentifier)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public List<Project> getAllProjects() throws WaktuException {
		return XmlUtil.getProjectsFromXml(projectFilePath);
	}
}
