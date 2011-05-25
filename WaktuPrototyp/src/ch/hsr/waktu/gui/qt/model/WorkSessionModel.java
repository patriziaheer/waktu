package ch.hsr.waktu.gui.qt.model;


import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QCoreApplication;
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
	
	public WorkSessionModel(Usr usr, QDate date) throws WaktuException {
		this.date = date;
		updateModel(usr, date);
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
		if (Qt.ItemDataRole.DisplayRole == role 
				|| Qt.ItemDataRole.EditRole == role
				|| Qt.ItemDataRole.ToolTipRole == role) {
			WorkSession workSession = workSessions.get(index.row());
			switch (index.column()) {
			case 0: return workSession.getWorkPackage().getProject().getDescription();
			case 1: return workSession.getWorkPackage().getDescription(); 
			case 2: return workSession.getDescription();
			case 3: return TimeUtil.convertGregorianToQDateTime(workSession.getStart()).time();
			case 4: return TimeUtil.convertGregorianToQDateTime(workSession.getEnd()).time();
			case 5: return "";
			}
		} else if (Qt.ItemDataRole.SizeHintRole == role) {
			switch (index.column()) {
			case 0: return new QSize(120, 20);
			case 1: return new QSize(120, 20);
			case 2: return new QSize(120, 20);
			case 3: return new QSize(44, 20);
			case 4: return new QSize(44, 20);
			case 5: return new QSize(30, 20);
			}
		}
		return null;
	}
	
	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (Qt.ItemDataRole.DisplayRole == role && Qt.Orientation.Horizontal == orientation) {
			switch (section) {
			case 0: return QCoreApplication.translate("WorkSessionModel", "Project");
			case 1: return QCoreApplication.translate("WorkSessionModel", "WorkPackage"); 
			case 2: return QCoreApplication.translate("WorkSessionModel", "Description");
			case 3: return QCoreApplication.translate("WorkSessionModel", "Start");
			case 4: return QCoreApplication.translate("WorkSessionModel", "End");
			case 5: return "";
			}
		} else if (Qt.ItemDataRole.SizeHintRole == role && Qt.Orientation.Vertical == orientation) {
			return new QSize(0,20);
		} else if (Qt.ItemDataRole.SizeHintRole == role && Qt.Orientation.Horizontal == orientation) {
			switch (section) {
			case 0: return new QSize(120, 30);
			case 1: return new QSize(120, 30);
			case 2: return new QSize(120, 30);
			case 3: return new QSize(44, 30);
			case 4: return new QSize(44, 30);
			case 5: return new QSize(30, 30);
			}
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
	
	public void updateModel(Usr usr, QDate date) throws WaktuException {
		this.date = date;
		workSessions = WorkSessionController.getInstance().getWorkSessions(usr, date);
	}

}
