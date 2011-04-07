package ch.hsr.waktu.model;

import ch.hsr.waktu.controller.FavoriteController;
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Usr;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;

public class FavoriteModel extends QAbstractItemModel {
	
	private Usr usr;
	
	public FavoriteModel(Usr usr) {
		this.usr = usr;
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return 3;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		return FavoriteController.getInstance().getFavorites(usr).size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (Qt.ItemDataRole.DisplayRole == role) {
			Favorite fav = FavoriteController.getInstance().getFavorites(usr).get(index.row());
			switch (index.column()) {
			case 0: return fav.getWorkPackageID();
			case 1: return fav.getStartTime().toString();
			case 2: return fav.getEndTime().toString();
			}
		}
		return null;
	}
	
	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (Qt.ItemDataRole.DisplayRole == role && Qt.Orientation.Horizontal == orientation) {
			switch (section) {
			case 0: return "Workpackage";
			case 1: return "Start";
			case 2: return "End";
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
