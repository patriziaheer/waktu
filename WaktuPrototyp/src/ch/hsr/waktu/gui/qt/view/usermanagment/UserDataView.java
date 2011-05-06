package ch.hsr.waktu.gui.qt.view.usermanagment;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.view.usermanagment.jui.Ui_UserData;

import com.trolltech.qt.gui.QWidget;


public class UserDataView extends QWidget{

	private Ui_UserData ui = new Ui_UserData();
	private Usr usr;
	
	public UserDataView(Usr user) {
		this.usr = user;
		ui.setupUi(this);
		ui.btnAdd.clicked.connect(this, "addClicked()");
		ComboBoxData.createSystemRoleComboBox(ui.cmbRole);
		ui.checkBox.stateChanged.connect(this, "inactivChanged()");
		
		UserController.getInstance().update.connect(this, "updateData()");
		UserController.getInstance().add.connect(this, "addData(Usr)");
		setFields();
	}

	private void setFields() {
		if (usr != null) {
			ui.grpOverview.setVisible(true);
			ui.btnAdd.setVisible(false);
			
			ui.txtName.setText(usr.getName());
			ui.txtName.setEnabled(false);
			
			ui.txtFirstname.setText(usr.getFirstname());
			ui.txtFirstname.setEnabled(false);
			
			ui.txtPensum.setValue(usr.getPensum());
			ui.txtPensum.setEnabled(false);
			
			ui.txtHolidays.setValue(usr.getHoliday());
			ui.txtHolidays.setEnabled(false);
		} else {
			ui.grpOverview.setVisible(false);
			ui.btnAdd.setVisible(true);

			ui.txtName.setEnabled(true);
			ui.txtFirstname.setEnabled(true);
			ui.txtPensum.setEnabled(true);
			ui.txtHolidays.setEnabled(true);
		}
	}

	@SuppressWarnings("unused")
	private void inactivChanged() {
		if (usr != null) {
			usr.setActiveState(!ui.checkBox.isChecked());
			UserController.getInstance().updateUser(usr);
		}
	}
	
	@SuppressWarnings("unused")
	private void addClicked() {
		usr = UserController.getInstance().addUser(ui.txtFirstname.text(), ui.txtName.text(), ui.txtPassword.text(), ui.txtPensum.value(), (SystemRole)ui.cmbRole.itemData(ui.cmbRole.currentIndex()), ui.txtHolidays.value());
		setFields();
	}
	
	@SuppressWarnings("unused")
	private void updateData() {
		setFields();
	}
	
	@SuppressWarnings("unused")
	private void addData(Usr usr) {
		setFields();
	}
	
	
}