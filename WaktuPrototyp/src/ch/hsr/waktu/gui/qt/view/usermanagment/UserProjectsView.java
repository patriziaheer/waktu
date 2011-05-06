package ch.hsr.waktu.gui.qt.view.usermanagment;

import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.UserProjectsModel;
import ch.hsr.waktu.gui.qt.view.usermanagment.jui.Ui_UserProjects;

import com.trolltech.qt.gui.QWidget;

public class UserProjectsView extends QWidget{
	
	private Ui_UserProjects ui = new Ui_UserProjects();
	private UserProjectsModel model;
	
	public UserProjectsView(Usr usr) {
		ui.setupUi(this);
		model = new UserProjectsModel(usr);
		ui.tblProjects.setModel(model);
		ui.tblProjects.horizontalHeader().setStretchLastSection(true);
		ComboBoxData.createActiveProjectComboBox(ui.cmbProjects);
	}

}
