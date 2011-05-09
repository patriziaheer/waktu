package ch.hsr.waktu.gui.qt.model;
import java.util.List;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.Usr;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QPixmap;
import com.trolltech.qt.gui.QTreeModel;


public class UserTreeModel extends QTreeModel {
	
	private List<Usr> usrs;
	
	public UserTreeModel() {
		usrs = UserController.getInstance().getActiveUsers();
	}

	@Override
	public Object child(Object parent, int index) {
		if (parent == null) {
			return usrs.get(index);
		} else if (parent instanceof Usr) {
			return UserController.UserProperties.values()[index];
		}
		return null;
	}

	@Override
	public int childCount(Object parent) {
		if (parent == null) {
			return usrs.size();
		} else if (parent instanceof Usr) {
			return UserController.UserProperties.values().length;
		}
		return 0;
	}

	@Override
	public String text(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}
	
	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		return "";
	}

	public Usr getUser(List<QModelIndex> selected) {
		return null;
	}

	@Override
	public Object data(Object value, int role) {
		switch (role) {
			case Qt.ItemDataRole.DisplayRole: {
				return value;
			}
			case Qt.ItemDataRole.ToolTipRole: {
				return value.toString();
			}
			case Qt.ItemDataRole.DecorationRole: {
				QIcon icon = new QIcon(new QPixmap("classpath:PfeilDown.JPG"));
				return icon;
			} case Qt.ItemDataRole.FontRole:
				QFont font = new QFont();
				font.setBold(true);
				font.setPointSize(10);
				return font;
		}
		return super.data(value, role);
	}
	
	public void updateUsrModel() {
		usrs = UserController.getInstance().getActiveUsers();
	}
}
