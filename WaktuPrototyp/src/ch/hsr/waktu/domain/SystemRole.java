package ch.hsr.waktu.domain;

import com.trolltech.qt.core.QCoreApplication;

public enum SystemRole {

	ADMIN {
		@Override
		public String toString() {
			return QCoreApplication.translate("SystemRole", "Admin");
		}
	}, 
	PROJECTMANAGER {
		@Override
		public String toString() {
			return QCoreApplication.translate("SystemRole", "ProjectManager");
		}
	},  
	EMPLOYEE {
		@Override
		public String toString() {
			return QCoreApplication.translate("SystemRole", "Employee");
		}
	}
}
