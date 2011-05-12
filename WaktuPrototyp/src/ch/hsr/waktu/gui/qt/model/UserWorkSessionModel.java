package ch.hsr.waktu.gui.qt.model;

import java.util.List;

import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;

public class UserWorkSessionModel extends QAbstractItemModel {
	
	private Usr usr;
	private List<WorkSession> workSessions;
	
	public UserWorkSessionModel(Usr usr) throws WaktuException {
		this.usr = usr;
		updateWorkSessionModel();
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 6;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
			return workSessions.size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (Qt.ItemDataRole.DisplayRole == role) {
			WorkSession workSession = workSessions.get(index.row());
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
			case 0: return QCoreApplication.translate("UserWorkSessionModel", "Project");
			case 1: return QCoreApplication.translate("UserWorkSessionModel", "WorkPackage");
			case 2: return QCoreApplication.translate("UserWorkSessionModel", "Desciption");
			case 3: return QCoreApplication.translate("UserWorkSessionModel", "Start");
			case 4: return QCoreApplication.translate("UserWorkSessionModel", "End");
			case 5: return QCoreApplication.translate("UserWorkSessionModel", "Duration");
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

	public void updateWorkSessionModel() throws WaktuException {
		workSessions = WorkSessionController.getInstance().getWorkSessions(usr);
	}
	
}
