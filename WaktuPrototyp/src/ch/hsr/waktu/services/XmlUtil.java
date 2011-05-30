package ch.hsr.waktu.services;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;

public class XmlUtil {

	private XmlUtil() {

	}

	private static Document parseXmlFile(String filePath) throws WaktuException {
		File file = new File(filePath);
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(file);
		} catch (SAXException e) {
			throw new WaktuException("SAX problem");
		} catch (IOException e) {
			throw new WaktuException("File could not be opened");
		} catch (ParserConfigurationException e) {
			throw new WaktuException("Wrong parser configuration");
		}
	}

	// @SuppressWarnings("unused")
	// private static void saveXmlFile(String filePath, Document document) {
	// //TODO
	// }

	public static LinkedList<WorkSession> getWorkSessionsFromXml(String filePath)
			throws WaktuException {
		// TODO
		Document document = parseXmlFile(filePath);
		if (document == null) {
			return null;
		}
		LinkedList<WorkSession> worksessions = new LinkedList<WorkSession>();
		NodeList workSessionNodeList = document
				.getElementsByTagName("WorkSession");
		for (int i = 0; i < workSessionNodeList.getLength(); i++) {
			Node node = workSessionNodeList.item(i);
			WorkSession ws = new WorkSession();
			ws.setDescription(getTextContentOf(node, "description"));
			ws.setUser(getUserContentOf(node, "user"));
			ws.setStart(getTimeContentOf(node, "start"));
			ws.setEnd(getTimeContentOf(node, "end"));
			ws.setWorkPackage(getWorkPackageContentOf(node, "workPackage"));
			worksessions.add(ws);
		}
		return worksessions;
	}

	private static WorkPackage getWorkPackageContentOf(Node node, String string)
			throws WaktuException {
		WorkPackage wp = new WorkPackage();
		wp.setActiveState(XmlUtil.getBooleanContentOf(node, "active"));
		wp.setDescription(getTextContentOf(node, "description"));
		wp.setProject(getProjectContentOf(node, "project"));
		return null;
	}

	private static Project getProjectContentOf(Node node, String string)
			throws WaktuException {
		// TODO Auto-generated method stub
		return ProjectController.getInstance().getProject(
				getIntegerContentOf(node, string));
	}

	public static void saveWorkSessionsToXml(String filePath,
			LinkedList<WorkSession> workSessions) {

	}

	public static LinkedList<Usr> getUsersFromXml(String filePath)
			throws WaktuException {

		LinkedList<Usr> users = new LinkedList<Usr>();

		Document document = parseXmlFile(filePath);
		if (document == null) {
			return null;
		}
		NodeList usrs = document.getElementsByTagName("Usr");
		for (int i = 0; i < usrs.getLength(); i++) {
			Node usr = usrs.item(i);
			Usr u = new Usr();
			u.setUsername(getTextContentOf(usr, "username"));
			u.setFirstname(getTextContentOf(usr, "firstname"));
			u.setName(getTextContentOf(usr, "lastname"));
			u.setPasswordHash(getTextContentOf(usr, "passwordHash"));
			u.setPensum(getIntegerContentOf(usr, "pensum"));
			u.setSystemRole(getSystemRoleContentOf(usr, "systemRole"));
			u.setHoliday(getDoubleContentOf(usr, "holiday"));
			u.setActiveState(getBooleanContentOf(usr, "active"));
			users.add(u);
		}
		return users;
	}

	// public static void saveUsersToXml(String filePath, LinkedList<Usr> users)
	// {
	// //TODO
	// }

	public static LinkedList<Favorite> getFavoritesFromXml(String filePath) {
		if (filePath == null) {
			return null;
		}
		LinkedList<Favorite> favorites = new LinkedList<Favorite>();
		return favorites;
	}

	private static String getTextContentOf(Node element, String name) {
		NodeList attributes = element.getChildNodes();
		for (int i = 0; i < attributes.getLength(); i++) {
			if (attributes.item(i).getNodeName().equals(name)) {
				return attributes.item(i).getTextContent();
			}
		}
		return "";
	}

	private static boolean getBooleanContentOf(Node element, String name) {
		return (getTextContentOf(element, name).equalsIgnoreCase("true"));
	}

	private static int getIntegerContentOf(Node element, String name) {
		return new Integer(getTextContentOf(element, name));
	}

	private static double getDoubleContentOf(Node element, String name) {
		return new Double(getTextContentOf(element, name));
	}

	private static SystemRole getSystemRoleContentOf(Node element, String name) {
		for (SystemRole sr : SystemRole.values()) {
			if (sr.toString().equalsIgnoreCase(getTextContentOf(element, name))) {
				return sr;
			}
		}
		return null;
	}

	private static Usr getUserContentOf(Node element, String name)
			throws WaktuException {
		return UserController.getInstance().getUser(name);
	}

	private static GregorianCalendar getTimeContentOf(Node element, String name) {
		return new GregorianCalendar();
	}
}
