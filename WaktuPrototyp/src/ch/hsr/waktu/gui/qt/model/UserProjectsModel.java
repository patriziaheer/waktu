package ch.hsr.waktu.gui.qt.model;

import java.util.List;

import ch.hsr.waktu.controller.datacontroller.ProjectStaffController;
import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;

public class UserProjectsModel extends QAbstractItemModel {

	private Usr usr;
	private List<Project> projects;

	public UserProjectsModel(Usr usr) throws WaktuGeneralException {
		this.usr = usr;
		updateProjectsModel();
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 3;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		return projects.size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (Qt.ItemDataRole.DisplayRole == role) {
			Project project = projects.get(index.row());
			switch (index.column()) {
			case 0:
				return project.getProjectIdentifier();
			case 1:
				return project.getDescription();
			case 2:
				return "";
			}
		}
		return null;
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (Qt.ItemDataRole.DisplayRole == role
				&& Qt.Orientation.Horizontal == orientation) {
			switch (section) {
			case 0:
				return "ProjectIdentifier";
			case 1:
				return "Description";
			case 2:
				return "";
			}
		} else if (Qt.ItemDataRole.SizeHintRole == role
				&& Qt.Orientation.Vertical == orientation) {
			return new QSize(0, 20);
		}
		return super.headerData(section, orientation, role);
	}

	@Override
	public QModelIndex index(int row, int column, QModelIndex arg2) {
		return createIndex(row, column);
	}

	@Override
	public QModelIndex parent(QModelIndex arg0) {
		return null;
	}

	public void updateProjectsModel() throws WaktuGeneralException {
		projects = ProjectStaffController.getInstance().getProjects(usr);
	}

}
