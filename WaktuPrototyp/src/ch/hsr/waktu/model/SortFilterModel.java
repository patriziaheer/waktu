package ch.hsr.waktu.model;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.gui.QSortFilterProxyModel;

public class SortFilterModel extends QSortFilterProxyModel {

	@Override
	protected boolean filterAcceptsRow(int source_row, QModelIndex source_parent) {
		QModelIndex index0;
		if (source_parent == null) {
			index0 = sourceModel().index(source_row, 0, source_parent);
		} else {
			index0 = sourceModel().index(source_parent.row(), 0,
					source_parent.parent());
		}

		QRegExp filter = filterRegExp();
		QAbstractItemModel model = sourceModel();
		boolean matchFound;

		matchFound = filter.indexIn(model.data(index0).toString()) != -1;

		return matchFound;
	}
}
