package ch.hsr.waktu.gui.qt.model;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QSortFilterProxyModel;

public class TableSortFilterModel extends QSortFilterProxyModel {
	
	private Project project = null;
	private WorkPackage workPackage = null;
	private Usr usr = null;
	private QDate start = null;
	private QDate end = null;

	@Override
	protected boolean filterAcceptsRow(int sourceRow, QModelIndex sourceParent) {
		QModelIndex idxProj = null;
        QModelIndex idxWP = null;
        QModelIndex idxUsr = null;
        QModelIndex idxStart = null;
        QModelIndex idxEnd = null;

        if (sourceModel() instanceof ProjectWorkSessionModel) {
	        idxWP = sourceModel().index(sourceRow, 0, sourceParent);
	        idxUsr = sourceModel().index(sourceRow, 2, sourceParent);
	        idxStart = sourceModel().index(sourceRow, 3, sourceParent);
	        idxEnd = sourceModel().index(sourceRow, 4, sourceParent);
        } else if (sourceModel() instanceof UserWorkSessionModel) {
        	idxProj = sourceModel().index(sourceRow, 0, sourceParent);
	        idxWP = sourceModel().index(sourceRow, 1, sourceParent);
	        idxStart = sourceModel().index(sourceRow, 3, sourceParent);
	        idxEnd = sourceModel().index(sourceRow, 4, sourceParent); 
        }

        QAbstractItemModel model = sourceModel();
        boolean matchFound = true;
        QDate currStart = ((QDateTime)model.data(idxStart)).date();
        QDate currEnd = ((QDateTime)model.data(idxEnd)).date();

        if (project != null) {
        	matchFound = model.data(idxProj).toString().equals(project.toString());
        }
        if (workPackage != null) {
        	matchFound = matchFound && model.data(idxWP).toString().equals(workPackage.toString());
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
	
	public void setProject(Project project) {
		this.project = project;
		invalidateFilter();
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
