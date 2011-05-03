package ch.hsr.waktu.model;

import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;

public class ProjectWorkPackageModel extends QAbstractItemModel{
	
	private Project project;
	
	public ProjectWorkPackageModel(Project project) {
		this.project = project;
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 2;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		return WorkPackageController.getInstance().getActiveWorkPackages(project).size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (Qt.ItemDataRole.DisplayRole == role) {
			WorkPackage workPackage = WorkPackageController.getInstance().getActiveWorkPackages(project).get(index.row());
			switch (index.column()) {
			case 0: return workPackage.getDescription();
			case 1: return !workPackage.isActive();
			}
		}
		return null;
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (Qt.ItemDataRole.DisplayRole == role && Qt.Orientation.Horizontal == orientation) {
			switch (section) {
			case 0: return tr("Description");
			case 1: return tr("Inactiv");
			}
		} else if (Qt.ItemDataRole.SizeHintRole == role && Qt.Orientation.Vertical == orientation) {
			return new QSize(0,20);
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

}
