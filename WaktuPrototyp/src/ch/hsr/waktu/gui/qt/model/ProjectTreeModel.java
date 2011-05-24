package ch.hsr.waktu.gui.qt.model;

import java.util.List;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPalette.ColorRole;
import com.trolltech.qt.gui.QTreeModel;

public class ProjectTreeModel extends QTreeModel {

	private List<Project> projects;
	private boolean showInactivs;

	public ProjectTreeModel(boolean showInactivs) throws WaktuException {
		this.showInactivs = showInactivs;
		updateProjectsModel();
	}

	@Override
	public Object child(Object parent, int index) {
		if (parent == null) {
			return projects.get(index);
		} else if (parent instanceof Project) {
			return ProjectController.ProjectProperties.values()[index];
		}
		return null;
	}

	@Override
	public int childCount(Object parent) {
		if (parent == null) {
			return projects.size();
		} else if (parent instanceof Project) {
			return ProjectController.ProjectProperties.values().length;
		}
		return 0;
	}

	@Override
	public String text(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		return "";
	}

	public Usr getUser(List<QModelIndex> selected) {
		return null;
	}

	@Override
	public Object data(Object value, int role) {
		switch (role) {
		case Qt.ItemDataRole.DisplayRole: {
			return value;
		}
		case Qt.ItemDataRole.ToolTipRole: {
			return value.toString();
		}
		case Qt.ItemDataRole.FontRole: {
			QFont font = new QFont();
			//font.setBold(true);
			font.setPointSize(10);
			return font;
		}
		case Qt.ItemDataRole.ForegroundRole: {
			if (value instanceof Usr) {
				Usr usr = (Usr)value;
				if (usr.isActive() == false) {
					return QColor.red;
				}
			} else {
				return QColor.black;
			}
		}
		case Qt.ItemDataRole.DecorationRole: {
			if (value instanceof Project) {
				Project proj = (Project)value;
				if (proj.isActive() == false) {
					QPalette palette = new QPalette();
					palette.setBrush(ColorRole.WindowText, new QBrush(QColor.red));
					return palette;
				} else {
					QPalette palette = new QPalette();
					palette.setBrush(ColorRole.WindowText, new QBrush(QColor.green));
					return palette;
				}
			} else {
				QPalette palette = new QPalette();
				palette.setBrush(ColorRole.WindowText, new QBrush(QColor.yellow));
				return palette;
			}
		}
		}
		return super.data(value, role);
	}

	public void updateProjectsModel() throws WaktuException {
		if (showInactivs) {
			projects = ProjectController.getInstance().getAllProjects();
		} else {
			projects = ProjectController.getInstance().getActiveProjects();
		}
	}
	
	public void setShowInactivs(boolean showInactivs) {
		this.showInactivs = showInactivs;
	}

}
