package ch.hsr.waktu.gui.qt.model;

import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;

public class ProjectWorkSessionModel extends QAbstractItemModel {
	
	private Project project;
	
	public ProjectWorkSessionModel(Project project) {
		this.project = project;
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 6;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		return WorkSessionController.getInstance().getWorkSessions(project).size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (Qt.ItemDataRole.DisplayRole == role) {
			WorkSession workSession = WorkSessionController.getInstance().getWorkSessions(project).get(index.row());
			switch (index.column()) {
			case 0: return workSession.getWorkPackage();
			case 1: return workSession.getDescription();
			case 2: return workSession.getUser();
			case 3: return TimeUtil.convertGregorianToQDateTime(workSession.getStart());
			case 4: return TimeUtil.convertGregorianToQDateTime(workSession.getEnd());
			case 5: return TimeUtil.calculateTimespanInSeconds(workSession.getStart(), workSession.getEnd());
			}
		}
		return null;
	}
	
	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (Qt.ItemDataRole.DisplayRole == role && Qt.Orientation.Horizontal == orientation) {
			switch (section) {
			case 0: return tr("WorkPackage");
			case 1: return tr("Desciption");
			case 2: return tr("User");
			case 3: return tr("Start");
			case 4: return tr("End");
			case 5: return tr("Duration");
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