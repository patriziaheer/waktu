package ch.hsr.waktu.domain;

import com.trolltech.qt.core.QCoreApplication;

public enum SystemRole {

	ADMIN {
		
		public String getName() {
			return QCoreApplication.translate("SystemRole", "Admin");
		}
	}, 
	PROJECTMANAGER {
		@Override
		public String getName() {
			return QCoreApplication.translate("SystemRole", "ProjectManager");
		}
	},  
	EMPLOYEE {
		@Override
		public String getName() {
			return QCoreApplication.translate("SystemRole", "Employee");
		}
	};

	public String getName() {
		return "";
	}
}
