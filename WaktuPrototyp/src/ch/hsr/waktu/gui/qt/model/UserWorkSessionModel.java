package ch.hsr.waktu.gui.qt.model;

import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;

public class UserWorkSessionModel extends QAbstractItemModel {
	
	private Usr usr;
	
	public UserWorkSessionModel(Usr usr) {
		this.usr = usr;
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 6;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
			return WorkSessionController.getInstance().getWorkSessions(usr).size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (Qt.ItemDataRole.DisplayRole == role) {
			WorkSession workSession = WorkSessionController.getInstance().getWorkSessions(usr).get(index.row());
			switch (index.column()) {
			case 0: return workSession.getWorkPackage().getProject();
			case 1: return workSession.getWorkPackage();
			case 2: return workSession.getDescription();
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
			case 0: return tr("Project");
			case 1: return tr("WorkPackage");
			case 2: return tr("Desciption");
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
