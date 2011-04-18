package ch.hsr.waktu.domain;

public class PermissionTable {
	// Format of the permission table: 
	// permissions[x][y]: 
	// x=0: Admin
	// x=1: ProjectManager
	// x=2: Employee
	// y=0: canAddProjects
	// y=1: canReadAllProjects
	// y=2: canReadOwnProjects
	// y=3: 
	// permissions[]
	
	private boolean[][] permissionTable;
	
	
	public boolean[][] getPermissionTable() {
		return permissionTable;
	}
	
	public static boolean getPermission(SystemRole role, String action){
//		return permissionTable[][];
		return true;
	}
	
//	public void setPermission(SystemRole role, String action, boolean permission) {
//		permissionTable[][] = permission;
//	}
}
