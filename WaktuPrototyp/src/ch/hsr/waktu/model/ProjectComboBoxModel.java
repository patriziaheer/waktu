package ch.hsr.waktu.model;

import ch.hsr.waktu.controller.ProjectController;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;

public class ProjectComboBoxModel extends QAbstractItemModel{
	
	public ProjectComboBoxModel() {
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 1;
	}
	
	@Override
	public int rowCount(QModelIndex arg0) {
		return ProjectController.getInstance().getActiveProjects().size();
	}

	@Override
	public Object data(QModelIndex index, int arg1) {
		return ProjectController.getInstance().getActiveProjects().get(index.row()).toString();
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
