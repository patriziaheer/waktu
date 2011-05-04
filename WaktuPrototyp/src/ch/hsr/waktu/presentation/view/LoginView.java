package ch.hsr.waktu.presentation.view;

import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.Usr;

import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.core.Qt.CursorShape;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QCursor;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPalette.ColorRole;

public class LoginView extends QDialog {
	
	private Ui_LoginDialog ui = new Ui_LoginDialog();
	private QMenu fileMenu;
	private QMenu languageMenu;
	private QAction translateDEAction;
	private QAction translateENAction;
	private QAction closeAction;
	
	public LoginView() {
		ui.setupUi(this);
		
		ui.btnCancel.clicked.connect(this, "cancelClicked()");
		ui.btnLogin.clicked.connect(this, "loginClicked()");
		
		QMenuBar menuBar = new QMenuBar();
		fileMenu = new QMenu();
		languageMenu = new QMenu();
		translateDEAction = new QAction(languageMenu);
		translateDEAction.triggered.connect(this, "translateDE()");
		translateENAction = new QAction(languageMenu);
		translateENAction.triggered.connect(this, "translateEN()");
		closeAction = new QAction(fileMenu);
		closeAction.triggered.connect(this, "closeClicked()");
		
		
		menuBar.addMenu(fileMenu);
		fileMenu.addMenu(languageMenu);
		languageMenu.addAction(translateENAction);
		languageMenu.addAction(translateDEAction);
		fileMenu.addAction(closeAction);
		
		this.layout().setMenuBar(menuBar);
		changeText();
	}
	
	@SuppressWarnings("unused")
	private void cancelClicked() {
		QApplication.exit();
	}
	
	@SuppressWarnings("unused")
	private void loginClicked() {
		this.setCursor(new QCursor(CursorShape.WaitCursor));
		if (PermissionController.getInstance().login(ui.txtUsername.text(), ui.txtPassword.text())) {
			Usr usr = UserController.getInstance().getUser(ui.txtUsername.text());
			TimeView timeView = new TimeView(usr);
			timeView.show();
			close();
		} else {
			ui.lblStatus.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginView","Username or Password wrong", null));
			QPalette palette = ui.lblStatus.palette();
			palette.setBrush(ColorRole.WindowText, new QBrush(QColor.red));
			ui.lblStatus.setPalette(palette);
		}
		this.setCursor(new QCursor(CursorShape.ArrowCursor));
	}

	@SuppressWarnings("unused")
	private void translateDE() {
        QTranslator translator = new QTranslator();
        translator.load("classpath:Login_de.qm");
        QApplication.installTranslator(translator);
        ui.retranslateUi(this);
        changeText();
	}
	
	@SuppressWarnings("unused")
	private void translateEN() {
        QTranslator translator = new QTranslator();
        translator.load("classpath:Login_en.qm");
        QApplication.installTranslator(translator);
        ui.retranslateUi(this);
        changeText();
	}
	
	@SuppressWarnings("unused")
	private void closeClicked() {
		QApplication.exit();
	}
	
	private void changeText() {
		fileMenu.setTitle(com.trolltech.qt.core.QCoreApplication.translate("LoginView","File", null));
		languageMenu.setTitle(com.trolltech.qt.core.QCoreApplication.translate("LoginView","language", null));
		translateDEAction.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginView","DE", null));
		translateENAction.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginView","EN", null));
		closeAction.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginView","Close", null));
	}
}
