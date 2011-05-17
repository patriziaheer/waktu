package ch.hsr.waktu.gui.qt.view;

import ch.hsr.waktu.controller.LoginController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.guicontroller.LanguageController.Language;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.core.Qt.CursorShape;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QCursor;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
import com.trolltech.qt.gui.QMovie;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPalette.ColorRole;
import com.trolltech.qt.gui.QPixmap;
import com.trolltech.qt.gui.QSplashScreen;

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

		LanguageController.getInstance().languageChanged.connect(this,
				"translate()");

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
		try {
			this.setCursor(new QCursor(CursorShape.WaitCursor));
			if (LoginController.getInstance().login(ui.txtUsername.text(),
					ui.txtPassword.text())) {
		        QSplashScreen splashScreen = new QSplashScreen(new QPixmap("classpath:icons/logo.gif"));
		        splashScreen.show();
		        QMovie mov = new QMovie("classpath:icons/logo.gif");
		        QLabel lbl = new QLabel();
		        lbl.setMovie(mov);
		        splashScreen.setLayout(new QHBoxLayout());
		        mov.start();
		        splashScreen.layout().addWidget(lbl);
		        splashScreen.showMessage("Login...");
		        QApplication.processEvents();
				Usr usr = UserController.getInstance().getUser(
						ui.txtUsername.text());
		        QApplication.processEvents();
				TimeView timeView = new TimeView(usr);
		        QApplication.processEvents();
				timeView.show();
		        QApplication.processEvents();
				this.setCursor(new QCursor(CursorShape.ArrowCursor));
		        QApplication.processEvents();
				setVisible(false);
		        QApplication.processEvents();
				splashScreen.finish(timeView);
			} else {
				setStatus(com.trolltech.qt.core.QCoreApplication
						.translate("LoginView", "Username or Password wrong",
								null));
				this.setCursor(new QCursor(CursorShape.ArrowCursor));
			}
		} catch (WaktuException e) {
			setStatus(e.getMessage());
		}
	}

	private void setStatus(String text) {
		ui.lblStatus.setText(text);
		QPalette palette = ui.lblStatus.palette();
		palette.setBrush(ColorRole.WindowText, new QBrush(QColor.red));
		ui.lblStatus.setPalette(palette);
	}

	@SuppressWarnings("unused")
	private void closeClicked() {
		QApplication.exit();
	}

	@SuppressWarnings("unused")
	private void translateDE() {
		LanguageController.getInstance().setCurrLanguage(Language.DE);
	}

	@SuppressWarnings("unused")
	private void translateEN() {
		LanguageController.getInstance().setCurrLanguage(Language.EN);
	}

	@SuppressWarnings("unused")
	private void translate() {
		ui.retranslateUi(this);
		changeText();
	}

	private void changeText() {
		fileMenu.setTitle(QCoreApplication.translate(
				"LoginView", "File", null));
		languageMenu.setTitle(QCoreApplication.translate(
				"LoginView", "language", null));
		translateDEAction.setText(QCoreApplication
				.translate("LoginView", "DE", null));
		translateENAction.setText(QCoreApplication
				.translate("LoginView", "EN", null));
		closeAction.setText(QCoreApplication.translate(
				"LoginView", "Close", null));
	}
	
	@Override
	protected void closeEvent(QCloseEvent arg__1) {
		QApplication.exit();
	}
	
}
