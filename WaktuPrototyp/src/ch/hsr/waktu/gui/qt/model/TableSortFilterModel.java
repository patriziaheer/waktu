package ch.hsr.waktu.gui.qt.model;

import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QSortFilterProxyModel;

public class TableSortFilterModel extends QSortFilterProxyModel {
	
	private WorkPackage workPackage = null;
	private Usr usr = null;
	private QDate start = null;
	private QDate end = null;

	@Override
	protected boolean filterAcceptsRow(int sourceRow, QModelIndex sourceParent) {
        QModelIndex idxWP;
        QModelIndex idxUsr;
        QModelIndex idxStart;
        QModelIndex idxEnd;

        idxWP = sourceModel().index(sourceRow, 0, sourceParent); //WP
        idxUsr = sourceModel().index(sourceRow, 2, sourceParent); //Usr
        idxStart = sourceModel().index(sourceRow, 3, sourceParent); //start
        idxEnd = sourceModel().index(sourceRow, 4, sourceParent); //end

        QAbstractItemModel model = sourceModel();
        boolean matchFound = true;
        QDate currStart = ((QDateTime)model.data(idxStart)).date();
        QDate currEnd = ((QDateTime)model.data(idxEnd)).date();

        if (workPackage != null) {
        	matchFound = model.data(idxWP).toString().equals(workPackage.toString());
        }
        if (usr != null) {
        	matchFound = matchFound && model.data(idxUsr).toString().equals(usr.toString());
        }
        if (start != null) {
        	matchFound = matchFound && start.compareTo(currStart) <= 0 && start.compareTo(currEnd) >= 0;
        }
        if (end != null) {
        	matchFound = matchFound && end.compareTo(currStart) >= 0 && end.compareTo(currEnd) <= 0;
        }

        return matchFound;

	}

	public void setWorkPackage(WorkPackage workPackage) {
		this.workPackage = workPackage;
		invalidateFilter();
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
		invalidateFilter();
	}

	public void setStart(QDate start) {
		this.start = start;
		invalidateFilter();
	}

	public void setEnd(QDate end) {
		this.end = end;
		invalidateFilter();
	}
	
	
}
