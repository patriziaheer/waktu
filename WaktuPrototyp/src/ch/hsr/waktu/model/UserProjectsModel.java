package ch.hsr.waktu.model;

import ch.hsr.waktu.controller.ProjectStaffController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QPushButton;

public class UserProjectsModel extends QAbstractItemModel{
	
	private Usr usr;
	
	public UserProjectsModel(Usr usr) {
		this.usr = usr;
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 3;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		return ProjectStaffController.getInstance().getProjects(usr).size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (Qt.ItemDataRole.DisplayRole == role) {
			Project project = ProjectStaffController.getInstance().getProjects(usr).get(index.row());
			switch (index.column()) {
			case 0: return project.getProjectIdentifier();
			case 1: return project.getDescription();
			case 2: return new QPushButton("Edit");
			}
		}
		return null;
	}
	
	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (Qt.ItemDataRole.DisplayRole == role && Qt.Orientation.Horizontal == orientation) {
			switch (section) {
			case 0: return "ProjectIdentifier";
			case 1: return "Description";
			case 2: return "";
			}
		}
		return super.headerData(section, orientation, role);
	}

	@Override
	public QModelIndex index(int arg0, int arg1, QModelIndex arg2) {
		return null;
	}

	@Override
	public QModelIndex parent(QModelIndex arg0) {
		return null;
	}

}
