package ch.hsr.waktu.gui.qt.view.usermanagment;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.guicontroller.GuiController;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.gui.QWidget;

public class UserDataView extends QWidget {

	private Ui_UserData ui = new Ui_UserData();
	private Usr usr;
	public Signal1<String> errorMessage = new Signal1<String>();

	public UserDataView(Usr user) {
		this.usr = user;
		ui.setupUi(this);
		ui.btnAdd.clicked.connect(this, "addClicked()");
		ComboBoxData.createSystemRoleComboBox(ui.cmbRole);

		UserController.getInstance().update.connect(this, "updateData()");
		UserController.getInstance().add.connect(this, "addData(Usr)");

		LanguageController.getInstance().languageChanged.connect(this, "translate()");
		setFields();
	}

	private void setFields() {
		if (usr != null) {
			if (GuiController.getInstance().canModifyUser()) {
				ui.grpOverview.setVisible(true);

				ui.btnAdd.setVisible(true);
				ui.btnAdd.setText(QCoreApplication.translate("UserDataView", "Save", null));
				ui.lblUsername.setVisible(true);
				ui.txtUserName.setVisible(true);
				ui.txtUserName.setEnabled(false);

				ui.txtName.setText(usr.getName());
				ui.txtName.setEnabled(true);

				ui.txtFirstname.setText(usr.getFirstname());
				ui.txtFirstname.setEnabled(true);

				ui.txtPassword.setText("");
				ui.txtPassword.setEnabled(true);

				ui.txtPensum.setValue(usr.getPensum());
				ui.txtPensum.setEnabled(true);

				ui.txtHolidays.setValue(usr.getHoliday());
				ui.txtHolidays.setEnabled(true);

				ui.checkBox.setChecked(!usr.isActive());
				ui.checkBox.setVisible(true);
			} else {
				ui.grpOverview.setVisible(true);
				ui.btnAdd.setVisible(false);
				ui.lblUsername.setVisible(true);
				ui.txtUserName.setVisible(true);
				ui.txtUserName.setEnabled(false);

				ui.txtName.setText(usr.getName());
				ui.txtName.setEnabled(false);

				ui.txtFirstname.setText(usr.getFirstname());
				ui.txtFirstname.setEnabled(false);

				ui.txtPassword.setText("");
				if (GuiController.getInstance().canModifyUser(usr)) {
					ui.txtPassword.setEnabled(true);
				} else {
					ui.txtPassword.setEnabled(false);
				}

				ui.txtPensum.setValue(usr.getPensum());
				ui.txtPensum.setEnabled(false);

				ui.txtHolidays.setValue(usr.getHoliday());
				ui.txtHolidays.setEnabled(false);

				ui.checkBox.setChecked(!usr.isActive());
				ui.checkBox.setVisible(false);
			}
		} else {
			ui.grpOverview.setVisible(false);
			ui.btnAdd.setVisible(true);

			ui.lblUsername.setVisible(false);
			ui.txtUserName.setVisible(false);
			ui.txtName.setEnabled(true);
			ui.txtFirstname.setEnabled(true);
			ui.txtPassword.setEnabled(true);
			ui.txtPensum.setEnabled(true);
			ui.txtHolidays.setEnabled(true);
			ui.checkBox.setVisible(true);
		}
	}

	@SuppressWarnings("unused")
	private void addClicked() {
		if (usr == null) {
			addNewUser();
		} else {
			saveUser();
		}
		setFields();
	}

	private void addNewUser() {
		try {
			usr = UserController.getInstance().addUser(ui.txtFirstname.text(), ui.txtName.text(), ui.txtPassword.text(), ui.txtPensum.value(),
					(SystemRole) ui.cmbRole.itemData(ui.cmbRole.currentIndex()), ui.txtHolidays.value());
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}

	private void saveUser() {
		usr.setFirstname(ui.txtFirstname.text());
		usr.setName(ui.txtName.text());
		usr.setPassword(ui.txtPassword.text());
		usr.setPensum(ui.txtPensum.value());
		usr.setHoliday(ui.txtHolidays.value());
		usr.setActiveState(!ui.checkBox.isChecked());
		try {
			UserController.getInstance().updateUser(usr);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private void updateData() {
		setFields();
	}

	@SuppressWarnings("unused")
	private void addData(Usr usr) {
		setFields();
	}

	@SuppressWarnings("unused")
	private void translate() {
		ui.retranslateUi(this);
		changeText();
	}

	private void changeText() {
		if (usr != null) {
			ui.btnAdd.setText(QCoreApplication.translate("ProjectDataView", "Save", null));
		} else {
			ui.btnAdd.setText(QCoreApplication.translate("ProjectDataView", "Add", null));
		}
	}

}
