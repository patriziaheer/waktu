package ch.hsr.waktu.gui.qt.model;

import com.trolltech.qt.core.QCoreApplication;

public enum UserProperties {
	Data {
		@Override
		public String toString() {
			return QCoreApplication.translate("UserProperties", "Data");
		}
	},
	Projects {
		@Override
		public String toString() {
			return QCoreApplication.translate("UserProperties", "Projects");
		}
	},
	WorkSessions {
		@Override
		public String toString() {
			return QCoreApplication.translate("UserProperties", "WorkSessions");
		}
	}
}
