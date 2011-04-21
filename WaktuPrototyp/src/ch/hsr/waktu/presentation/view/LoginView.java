package ch.hsr.waktu.presentation.view;

import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.presentation.view.jui.Ui_LoginWindow;

import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPalette.ColorRole;

public class LoginView extends QMainWindow {
	
	private Ui_LoginWindow ui = new Ui_LoginWindow();
	
	public LoginView() {
		ui.setupUi(this);
		ui.btnCancel.clicked.connect(this, "cancelClicked()");
		ui.btnLogin.clicked.connect(this, "loginClicked()");
	}
	
	@SuppressWarnings("unused")
	private void cancelClicked() {
		System.exit(0);
	}
	
	@SuppressWarnings("unused")
	private void loginClicked() {
		if (PermissionController.getInstance().login(ui.txtUsername.text(), ui.txtPassword.text())) {
			Usr usr = UserController.getInstance().getUser(ui.txtUsername.text());
			TimeView timeView = new TimeView(usr);
			timeView.show();
			close();
		} else {
			ui.statusbar.showMessage(tr("Username or Password wrong"), 2000);
			QPalette palette = ui.statusbar.palette();
			palette.setBrush(ColorRole.WindowText, new QBrush(QColor.red));
			ui.statusbar.setPalette(palette);
		}
	}

}
