import com.trolltech.qt.gui.QWidget;


public class UserData extends QWidget{

	Ui_UserData ui = new Ui_UserData();
	
	public UserData(User user) {
		ui.setupUi(this);
		ui.lblUser.setText(user.toString());
	}
}
