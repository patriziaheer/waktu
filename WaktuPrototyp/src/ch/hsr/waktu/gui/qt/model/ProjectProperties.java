package ch.hsr.waktu.gui.qt.model;

import com.trolltech.qt.core.QCoreApplication;

public enum ProjectProperties {
	Data {
		@Override
		public String toString() {
			return QCoreApplication.translate("ProjectProperties", "Data");
		}
	},
	WorkPackages {
		@Override
		public String toString() {
			return QCoreApplication.translate("ProjectProperties",
					"WorkPackages");
		}
	},
	ProjectStaff {
		@Override
		public String toString() {
			return QCoreApplication.translate("ProjectProperties",
					"ProjectStaff");
		}
	},
	WorkSessions {
		@Override
		public String toString() {
			return QCoreApplication.translate("ProjectProperties",
					"WorkSessions");
		}
	}
}
