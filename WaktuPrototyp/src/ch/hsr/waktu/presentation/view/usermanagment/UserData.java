package ch.hsr.waktu.presentation.view.usermanagment;
import ch.hsr.waktu.domain.User;
import ch.hsr.waktu.presentation.model.UserTableModel;
import ch.hsr.waktu.presentation.view.usermanagment.jui.Ui_UserData;

import com.trolltech.qt.gui.QWidget;


public class UserData extends QWidget{

	Ui_UserData ui = new Ui_UserData();
	
	public UserData(User user) {
		ui.setupUi(this);
		ui.lblUser.setText(user.toString());
		ui.tableView.setModel(new UserTableModel());
	}
}
