package ch.hsr.waktu.gui.qt.view;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.model.SortFilterModel;
import ch.hsr.waktu.gui.qt.model.UserTreeModel;
import ch.hsr.waktu.gui.qt.view.usermanagment.UserDataView;
import ch.hsr.waktu.gui.qt.view.usermanagment.UserProjectsView;
import ch.hsr.waktu.gui.qt.view.usermanagment.UserWorkSessionsView;
import ch.hsr.waktu.guicontroller.LanguageController;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QSplitter;
import com.trolltech.qt.gui.QWidget;


public class UserDetails extends QWidget {

	private Ui_ManagmentDetails ui = new Ui_ManagmentDetails();
	private UserTreeModel userTreeModel;
	private QSplitter splitter;
	private QWidget currWidget = new QWidget();
	private SortFilterModel filterModel = new SortFilterModel();
	public Signal1<String> errorMessage = new Signal1<String>();
	
	public UserDetails() {
		ui.setupUi(this);
		
		try {
			userTreeModel = new UserTreeModel();
		} catch (WaktuGeneralException e) {
			showErrorMessage(e.getMessage());
		}
		filterModel.setDynamicSortFilter(true);
		filterModel.setSourceModel(userTreeModel);
		ui.treeView.setModel(filterModel);
		
		splitter = new QSplitter(Qt.Orientation.Horizontal);
		splitter.addWidget(ui.widget);
		splitter.addWidget(currWidget);
		
		ui.gridLayout.removeWidget(ui.widget);
		ui.gridLayout.addWidget(splitter);
		
		ui.treeView.selectionModel().selectionChanged.connect(this, "selectionChanged()");
		ui.lineEdit.textChanged.connect(this, "textFilterChanged()");
		UserController.getInstance().update.connect(this, "updateData()");
		UserController.getInstance().add.connect(this, "addData(Usr)");
		
		LanguageController.getInstance().languageChanged.connect(this, "translate()");
		
	}
	
	@SuppressWarnings("unused")
	private void selectionChanged() {
		QModelIndex selectedIndex = filterModel.mapToSource(ui.treeView.selectionModel().selectedIndexes().get(0));
		Object selected = userTreeModel.indexToValue(selectedIndex);
		Object parent = userTreeModel.indexToValue(userTreeModel.parent(selectedIndex));
		if (selected instanceof UserController.UserProperties) {
			if (currWidget != null) {
				currWidget.setParent(null);
			}
			switch ((UserController.UserProperties)selected) {
			case Data: {
				UserDataView userDataView = new UserDataView((Usr)parent);
				userDataView.errorMessage.connect(this, "showErrorMessage(String)");
				currWidget = userDataView;
			}
			break;
			case Projects: {
				UserProjectsView userProjectsView = new UserProjectsView((Usr)parent);
				userProjectsView.errorMessage.connect(this, "showErrorMessage(String)");
				currWidget = userProjectsView;
			}
			break;
			case WorkSessions: {
				UserWorkSessionsView userWorkSessionsView = new UserWorkSessionsView((Usr)parent);
				userWorkSessionsView.errorMessage.connect(this, "showErrorMessage(String)");
				currWidget = userWorkSessionsView;
			}
			}
			splitter.addWidget(currWidget);
		}
	}
	
	@SuppressWarnings("unused")
	private void textFilterChanged() {
		System.out.println("filter changed");
		userTreeModel.layoutAboutToBeChanged.emit();
        Qt.CaseSensitivity caseSensitivity = Qt.CaseSensitivity.CaseInsensitive;

        QRegExp regExp = new QRegExp(ui.lineEdit.text(),
                                     caseSensitivity, QRegExp.PatternSyntax.RegExp);
        filterModel.setFilterRegExp(regExp);
        userTreeModel.layoutChanged.emit();
	}
	
	@SuppressWarnings("unused")
	private void updateData() {
		updateTable();
	}
	
	@SuppressWarnings("unused")
	private void addData(Usr user) {
		updateTable();
	}

	private void updateTable() {
		userTreeModel.layoutAboutToBeChanged.emit();
		userTreeModel.dataChanged.emit(userTreeModel.index(0, 0), userTreeModel.index(userTreeModel.rowCount(), userTreeModel.columnCount()));
		filterModel.dataChanged.emit(filterModel.index(0, 0), filterModel.index(userTreeModel.rowCount(), userTreeModel.columnCount()));
        userTreeModel.layoutChanged.emit();
	}
	
	@Override
	protected void contextMenuEvent(QContextMenuEvent event) {
        QMenu menu = new QMenu(this);
        QAction closeAction = new QAction(com.trolltech.qt.core.QCoreApplication.translate("UserDetails",("Close"),null),menu);
        closeAction.triggered.connect(this, "closeWindow()");
        menu.addAction(closeAction);
        menu.exec(event.globalPos());
	}
	
	@SuppressWarnings("unused")
	private void closeWindow() {
		setVisible(false);
	}
	
	public void addUser() {
		if (currWidget != null) {
			currWidget.setParent(null);
		}
		currWidget = new UserDataView(null);
		splitter.addWidget(currWidget);
	}

	@SuppressWarnings("unused")
	private void translate() {
        ui.retranslateUi(this);
	}
	
	private void showErrorMessage(String errorMessageString) {
		errorMessage.emit(errorMessageString);
	}
}
