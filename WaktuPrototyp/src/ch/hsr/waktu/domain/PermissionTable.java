package ch.hsr.waktu.domain;

public class PermissionTable {
	public static boolean getPermission(SystemAction action, SystemRole role, Project project) {
		if(role.equals(SystemRole.ADMIN)) {
			return Admin.getPermission(action, project);
		} else if(role.equals(SystemRole.PROJECTMANAGER)) {
			return ProjectManager.getPermission(action, project);
		} else if(role.equals(SystemRole.EMPLOYEE)) {
			return Employee.getPermission(action, project);
		}
		return false;
	}
	
	public static void setPermission(SystemAction action, SystemRole role, boolean permission) {
		if(role.equals(SystemRole.ADMIN)) {
			Admin.setPermission(action, permission);
		} else if(role.equals(SystemRole.PROJECTMANAGER)) {
			ProjectManager.setPermission(action, permission);
		} else if(role.equals(SystemRole.EMPLOYEE)) {
			Employee.setPermission(action, permission);
		}
	}
	
	private static class Admin {
		static boolean[] permissions = new boolean[SystemAction.values().length];
		
		private static boolean getPermission(SystemAction action, Project project) {
			return permissions[action.getIndex()];
		}
		
		private static void setPermission(SystemAction action, boolean permission) {
			permissions[action.getIndex()] = permission;
		}
	}
	
	private static class ProjectManager {
		static boolean[] permissions = new boolean[SystemAction.values().length];
		
		private static boolean getPermission(SystemAction action, Project project) {
			return permissions[action.getIndex()];
		}
		
		private static void setPermission(SystemAction action, boolean permission) {
			permissions[action.getIndex()] = permission;
		}
	}
	
	private static class Employee {
		static boolean[] permissions = new boolean[SystemAction.values().length];
		
		private static boolean getPermission(SystemAction action, Project project) {
			return permissions[action.getIndex()];
		}
		
		private static void setPermission(SystemAction action, boolean permission) {
			permissions[action.getIndex()] = permission;
		}
	}
}
