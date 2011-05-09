package ch.hsr.waktu.gui.qt.model;


import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.QTime;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;

public class WorkSessionModel extends QAbstractItemModel {
	
	private Logger logger = Logger.getLogger(WorkSessionModel.class);
	
	private List<WorkSession> workSessions;
	private QModelIndex editable = null;
	private QDate date;
	
	public WorkSessionModel(Usr usr, QDate date) {
		this.date = date;
		try {
			workSessions = WorkSessionController.getInstance().getWorkSessions(usr, date);
		} catch (WaktuGeneralException e) {
			// TODO PH: unhandled exceptions
			e.printStackTrace();
		}
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
		if (Qt.ItemDataRole.DisplayRole == role || role == Qt.ItemDataRole.EditRole) {
			WorkSession workSession = workSessions.get(index.row());
			switch (index.column()) {
			case 0: return workSession.getWorkPackage().getProject();
			case 1: return workSession.getWorkPackage(); 
			case 2: return workSession.getDescription();
			case 3: return TimeUtil.convertGregorianToQDateTime(workSession.getStart()).time();
			case 4: return TimeUtil.convertGregorianToQDateTime(workSession.getEnd()).time();
			case 5: return "";
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
			case 2: return tr("Description");
			case 3: return tr("Start");
			case 4: return tr("End");
			case 5: return "";
			}
		} else if (Qt.ItemDataRole.SizeHintRole == role && Qt.Orientation.Vertical == orientation) {
			return new QSize(0,20);
		} 
		return null;
	}
	

	@Override
	public ItemFlags flags(QModelIndex index) {
		if (editable == null) {
			return super.flags(index);
		}
		if (index.row() == editable.row() && index.column() != columnCount()-1 && index.column() != 0 && index.column() != 1) {
			Qt.ItemFlag[] flags = {Qt.ItemFlag.ItemIsEditable,Qt.ItemFlag.ItemIsSelectable,Qt.ItemFlag.ItemIsEnabled};
			ItemFlags f = super.flags(index);
			f.set(flags);
			return f;
		}
		return super.flags(index);
	}
	
	@Override
	public boolean setData(QModelIndex index, Object value, int role) {
		WorkSession workSession = workSessions.get(index.row());
		switch(index.column()) {
		case 2: {
			workSession.setDescription((String)value);
		}
		break;
		case 3: {
			QDateTime qDateTime = new QDateTime(date, (QTime)value);
			if (qDateTime.compareTo(TimeUtil.convertGregorianToQDateTime(workSession.getEnd())) < 0) {
				GregorianCalendar dateTime = TimeUtil.convertQDateTimeToGregorian(qDateTime);
				workSession.setStart(dateTime);
			}
		}
		break;
		case 4: {
			QDateTime qDateTime = new QDateTime(date, (QTime)value);
			if (qDateTime.compareTo(TimeUtil.convertGregorianToQDateTime(workSession.getStart())) > 0) {
				GregorianCalendar dateTime = TimeUtil.convertQDateTimeToGregorian(qDateTime);
				workSession.setEnd(dateTime);
			}
			
		}
		break;
		}
		return super.setData(index, value, role);
	}

	@Override
	public QModelIndex index(int row, int column, QModelIndex arg2) {
		return createIndex(row, column);
	}

	@Override
	public QModelIndex parent(QModelIndex arg0) {
		return null;
	}

	public QModelIndex getEditable() {
		return editable;
	}

	public void setEditable(QModelIndex editable) {
		this.editable = editable;
		if (editable == null) {
			logger.info("no editable row");
		} else {
			logger.info("Row " + editable.row() + " is editable");
		}
	}
	
	public WorkSession getWorkSession(int row) {
		return workSessions.get(row);
	}
	
	public void updateModel(Usr usr, QDate date) {
		try {
			workSessions = WorkSessionController.getInstance().getWorkSessions(usr, date);
		} catch (WaktuGeneralException e) {
			// TODO PH: unhandled exceptions
			e.printStackTrace();
		}
	}

}
