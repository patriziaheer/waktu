package ch.hsr.waktu.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ch.hsr.waktu.controller.datacontroller.ProjectControllerXml;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.WorkPackageControllerXml;
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;

public class XmlUtil {

    private XmlUtil() {

    }

    private static Document parseXmlFile(final String filePath)
            throws WaktuException {

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

    public static LinkedList<Usr> getUsersFromXml(final String filePath)
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

    public static void saveWorkSessionsToXml(final String filePath,
            final LinkedList<WorkSession> workSessions) {

    }

    public static List<WorkSession> getWorkSessionsFromXml(final String filePath)
            throws WaktuException {

        Document document = parseXmlFile(filePath);
        if (document == null) {
            return null;
        }
        ArrayList<WorkSession> worksessions = new ArrayList<WorkSession>();
        NodeList workSessionNodeList = document
                .getElementsByTagName("WorkSession");
        for (int i = 0; i < workSessionNodeList.getLength(); i++) {
            Node node = workSessionNodeList.item(i);
            WorkSession ws = new WorkSession();
            ws.setDescription(getTextContentOf(node, "description"));
            ws.setUser(getUserContentOf(node, "user"));
            ws.setStart(getTimeContentOf(node, "start"));
            ws.setEnd(getTimeContentOf(node, "end"));
            ws.setWorkPackage(getWorkPackageContentOf(node, "WorkPackage"));
            worksessions.add(ws);
        }
        return worksessions;
    }

    public static List<Project> getProjectsFromXml(final String filePath)
            throws WaktuException {
        Document document = parseXmlFile(filePath);
        if (document == null) {
            return null;
        }
        ArrayList<Project> projects = new ArrayList<Project>();
        NodeList projectNodeList = document.getElementsByTagName("Project");
        for (int i = 0; i < projectNodeList.getLength(); i++) {
            Node node = projectNodeList.item(i);
            Project p = new Project();
            p.setActiveState(getBooleanContentOf(node, "active"));
            p.setDescription(getTextContentOf(node, "description"));
            p.setPlannedTime(getIntegerContentOf(node, "plannedTime"));
            p.setProjectIdentifier(getTextContentOf(node, "projectIdentifier"));
            p.setProjectManager(getUserContentOf(node, "projectManager"));
            projects.add(p);
        }
        return projects;
    }

    public static List<Favorite> getFavoritesFromXml(final String filePath) {

        if (filePath == null) {
            return null;
        }
        LinkedList<Favorite> favorites = new LinkedList<Favorite>();
        return favorites;
    }

    public static List<WorkPackage> getWorkPackagesFromXml(final String filePath)
            throws WaktuException {
        Document document = parseXmlFile(filePath);
        if (document == null) {
            return null;
        }
        ArrayList<WorkPackage> workpackages = new ArrayList<WorkPackage>();
        NodeList workPackageNodeList = document
                .getElementsByTagName("WorkPackage");
        for (int i = 0; i < workPackageNodeList.getLength(); i++) {
            Node node = workPackageNodeList.item(i);
            WorkPackage wp = new WorkPackage();
            wp.setActiveState(getBooleanContentOf(node, "active"));
            wp.setDescription(getTextContentOf(node, "description"));
            wp.setProject(getProjectContentOf(node, "project"));
            workpackages.add(wp);
        }
        return workpackages;
    }

    private static WorkPackage getWorkPackageContentOf(final Node element,
            final String name) throws WaktuException {

        return ((WorkPackageControllerXml)WorkPackageControllerXml.getInstance()).getWorkPackage(
                getTextContentOf(element, name));
    }

    private static Project getProjectContentOf(final Node element,
            final String name) throws WaktuException {
        return ((ProjectControllerXml)ProjectControllerXml.getInstance()).getProject(
                getTextContentOf(element, name));
    }

    private static String getTextContentOf(final Node element, final String name) {
        NodeList attributes = element.getChildNodes();
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.item(i).getNodeName().equals(name)) {
                return attributes.item(i).getTextContent();
            }
        }
        return "";
    }

    private static boolean getBooleanContentOf(final Node element,
            final String name) {

        return getTextContentOf(element, name).equalsIgnoreCase("true");
    }

    private static int getIntegerContentOf(final Node element, final String name) {
        return new Integer(getTextContentOf(element, name));
    }

    private static double getDoubleContentOf(final Node element,
            final String name) {
        return new Double(getTextContentOf(element, name));
    }

    private static SystemRole getSystemRoleContentOf(final Node element,
            final String name) {
        for (SystemRole sr : SystemRole.values()) {
            if (sr.toString().equalsIgnoreCase(getTextContentOf(element, name))) {
                return sr;
            }
        }
        return null;
    }

    private static Usr getUserContentOf(final Node element, final String name)
            throws WaktuException {

        return UserController.getInstance().getUser(
                getTextContentOf(element, name));
    }

    private static GregorianCalendar getTimeContentOf(final Node element,
            final String name) {

        return TimeUtil.convertQDateTimeToGregorian(TimeUtil
                .stringToQDateTime(getTextContentOf(element, name)));
    }
}
