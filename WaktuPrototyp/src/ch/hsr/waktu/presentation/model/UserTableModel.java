package ch.hsr.waktu.presentation.model;

import ch.hsr.waktu.domain.Domain;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;

public class UserTableModel extends QAbstractItemModel{
	
	public UserTableModel() {
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 1;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		return Domain.getInstance().getUsers().size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			return Domain.getInstance().getUsers().get(index.row()).getName();
		}
		return null;
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
