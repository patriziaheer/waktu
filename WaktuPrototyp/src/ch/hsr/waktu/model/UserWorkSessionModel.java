package ch.hsr.waktu.model;

import ch.hsr.waktu.controller.WorkSessionController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkSession;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QTime;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;

public class UserWorkSessionModel extends QAbstractItemModel {
	
	private Usr usr;
	private QDate date = null;
	
	public UserWorkSessionModel(Usr usr) {
		this.usr = usr;
	}
	
	public UserWorkSessionModel(Usr usr, QDate date) {
		this.usr = usr;
		this.date = date;
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 5;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		if (null == date) {
			return WorkSessionController.getInstance().getWorkSessions(usr).size();
		} else {
			return WorkSessionController.getInstance().getWorkSessions(usr, date).size();
		}
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (Qt.ItemDataRole.DisplayRole == role) {
			WorkSession workSession;
			if (null == date) {
				workSession = WorkSessionController.getInstance().getWorkSessions(usr).get(index.row());
			} else {
				workSession = WorkSessionController.getInstance().getWorkSessions(usr, date).get(index.row());
			}
			switch (index.column()) {
			case 0: return "Project: Workpackage";
			case 1: return workSession.getDescription();
			case 2: return workSession.getStart().toString();
			case 3: return workSession.getEnd().toString();
			case 4:  {
				QTime dauer = new QTime(0, 0, workSession.getStart().secsTo(workSession.getEnd()));
				return dauer.toString();
			}
			}
		}
		return null;
	}
	
	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (Qt.ItemDataRole.DisplayRole == role && Qt.Orientation.Horizontal == orientation) {
			switch (section) {
			case 0: return tr("Project");
			case 1: return tr("Desciption");
			case 2: return tr("Start");
			case 3: return tr("End");
			case 4: return tr("Duration");
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
