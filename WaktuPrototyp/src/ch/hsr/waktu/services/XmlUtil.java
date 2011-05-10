package ch.hsr.waktu.services;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkSession;

public class XmlUtil {
	
	private static Document parseXmlFile(String filePath) throws WaktuGeneralException {
		File file = new File(filePath);
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			throw new WaktuGeneralException("File could not be opened");
		} catch (ParserConfigurationException e) {
			throw new WaktuGeneralException("Wrong parser configuration");
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	private static void saveXmlFile(String filePath, Document document) {
		//TODO
	}
	
	public static LinkedList<WorkSession> getWorkSessionsFromXml(String filePath) throws WaktuGeneralException {
		//TODO
		if(filePath == null) {
			return null;
		}
		Document document = parseXmlFile(filePath);
		if(document == null) {
			return null;
		}
		LinkedList<WorkSession> worksessions = new LinkedList<WorkSession>();
		
		
		return worksessions;
	}
	
	public static void saveWorkSessionsToXml(String filePath, LinkedList<WorkSession> workSessions) {
		
	}
	
	public static LinkedList<Usr> getUsersFromXml(String filePath) throws WaktuGeneralException {
		if(filePath == null) {
			return null;
		}
		LinkedList<Usr> users = new LinkedList<Usr>();
		
		Document document = parseXmlFile(filePath);
		NodeList usrs = document.getElementsByTagName("Usr");
		for(int i = 0; i < usrs.getLength(); i++) {
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
	
	public static void saveUsersToXml(String filePath, LinkedList<Usr> users) {
		//TODO
	}
	
	public static LinkedList<Favorite> getFavoritesFromXml(String filePath) {
		if(filePath == null) {
			return null;
		}
		LinkedList<Favorite> favorites = new LinkedList<Favorite>();
		return favorites;
	}
	
	private static String getTextContentOf(Node element, String name) {
		NodeList attributes = element.getChildNodes();		
		for(int i = 0; i < attributes.getLength(); i++) {
			if(attributes.item(i).getNodeName().equals(name)) {
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
		for(SystemRole sr: SystemRole.values()) {
			if(sr.toString().equalsIgnoreCase(getTextContentOf(element, name))) {
				return sr;
			}
		}
		return null;
	}
}
