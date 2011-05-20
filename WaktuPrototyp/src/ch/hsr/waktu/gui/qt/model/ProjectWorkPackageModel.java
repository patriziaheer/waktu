package ch.hsr.waktu.gui.qt.model;

import java.util.List;

import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;

public class ProjectWorkPackageModel extends QAbstractItemModel {

	private Project project;
	private List<WorkPackage> workPackages;
	public Signal1<String> errorMessage = new Signal1<String>();

	public ProjectWorkPackageModel(Project project) {
		this.project = project;
		updateWorkPackageModel();
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 2;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		return workPackages.size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		WorkPackage workPackage = workPackages.get(index.row());
		if (Qt.ItemDataRole.DisplayRole == role || Qt.ItemDataRole.EditRole == role) {
			switch (index.column()) {
			case 0:
				return workPackage.getDescription();
			case 1:
				return !workPackage.isActive();
			}
		}
		return null;
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (Qt.ItemDataRole.DisplayRole == role && Qt.Orientation.Horizontal == orientation) {
			switch (section) {
			case 0:
				return QCoreApplication.translate("ProjectWorkPackageModel", "Description");
			case 1:
				return QCoreApplication.translate("ProjectWorkPackageModel", "Inactiv");
			}
		} else if (Qt.ItemDataRole.SizeHintRole == role && Qt.Orientation.Vertical == orientation) {
			return new QSize(0, 20);
		}
		return super.headerData(section, orientation, role);
	}

	@Override
	public ItemFlags flags(QModelIndex index) {
		ItemFlags f = super.flags(index);
		if (index.column() == 1) {
			Qt.ItemFlag[] flags = { Qt.ItemFlag.ItemIsEditable, Qt.ItemFlag.ItemIsSelectable, Qt.ItemFlag.ItemIsEnabled, Qt.ItemFlag.ItemIsUserCheckable };
			f.set(flags);
		} else {
			Qt.ItemFlag[] flags = { Qt.ItemFlag.ItemIsEditable, Qt.ItemFlag.ItemIsSelectable, Qt.ItemFlag.ItemIsEnabled };
			f.set(flags);
		}
		return f;
	}

	@Override
	public boolean setData(QModelIndex index, Object value, int role) {
		WorkPackage workPackage = workPackages.get(index.row());
		switch (index.column()) {
		case 0: {
			workPackage.setDescription((String)value);
			return true;
		}
		case 1: {
			workPackage.setActiveState((Boolean)value);
			return true;
		}
		}
		try {
			WorkPackageController.getInstance().updateWorkPackage(workPackage);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		return false;
	}
	
	@Override
	public QModelIndex index(int row, int column, QModelIndex arg2) {
		return createIndex(row, column);
	}

	@Override
	public QModelIndex parent(QModelIndex arg0) {
		return null;
	}
	
	public void updateWorkPackageModel() {
		try {
			workPackages = WorkPackageController.getInstance().getAllWorkPackages(project);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}

	public WorkPackage getWorkPackage(int row) {
		return workPackages.get(row);
	}
}
