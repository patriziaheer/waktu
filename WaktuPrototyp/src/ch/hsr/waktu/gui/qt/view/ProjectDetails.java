package ch.hsr.waktu.gui.qt.view;
import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.gui.qt.model.ProjectTreeModel;
import ch.hsr.waktu.gui.qt.model.SortFilterModel;
import ch.hsr.waktu.gui.qt.view.projectmanagment.ProjectDataView;
import ch.hsr.waktu.gui.qt.view.projectmanagment.ProjectStaffView;
import ch.hsr.waktu.gui.qt.view.projectmanagment.ProjectWorkPackageView;
import ch.hsr.waktu.gui.qt.view.projectmanagment.ProjectWorkSessionsView;
import ch.hsr.waktu.guicontroller.LanguageController;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QSplitter;
import com.trolltech.qt.gui.QWidget;


public class ProjectDetails extends QWidget {

	private Ui_ManagmentDetails ui = new Ui_ManagmentDetails();
	private ProjectTreeModel projectTreeModel;
	private QSplitter splitter;
	private QWidget currWidget = new QWidget();
	private SortFilterModel filterModel = new SortFilterModel();
	public Signal1<String> errorMessage = new Signal1<String>();
	
	public ProjectDetails() {
		ui.setupUi(this);
		try {
			projectTreeModel = new ProjectTreeModel();
		} catch (WaktuGeneralException e) {
			showErrorMessage(e.getMessage());
		}
		
		filterModel.setDynamicSortFilter(true);
		filterModel.setSourceModel(projectTreeModel);
		ui.treeView.setModel(filterModel);
		
		splitter = new QSplitter(Qt.Orientation.Horizontal);
		splitter.addWidget(ui.widget);
		splitter.addWidget(currWidget);
		
		ui.gridLayout.removeWidget(ui.widget);
		ui.gridLayout.addWidget(splitter);
		
		ui.treeView.selectionModel().selectionChanged.connect(this, "selectionChanged()");
		ui.lineEdit.textChanged.connect(this, "textFilterChanged()");
		ProjectController.getInstance().update.connect(this, "updateData()");
		ProjectController.getInstance().add.connect(this, "addData(Project)");

		LanguageController.getInstance().languageChanged.connect(this, "translate()");
	}
	
	@SuppressWarnings("unused")
	private void selectionChanged() {
		QModelIndex selectedIndex = filterModel.mapToSource(ui.treeView.selectionModel().selectedIndexes().get(0));
		Object selected = projectTreeModel.indexToValue(selectedIndex);
		Object parent = projectTreeModel.indexToValue(projectTreeModel.parent(selectedIndex));
		if (selected instanceof ProjectController.ProjectProperties) {
			if (currWidget != null) {
				currWidget.setParent(null);
			}
			switch ((ProjectController.ProjectProperties)selected) {
			case Data: {
				ProjectDataView projectDataView = new ProjectDataView((Project)parent);
				projectDataView.errorMessage.connect(this, "showErrorMessage(String)");
				currWidget = projectDataView;
			}
			break;
			case WorkPackages: {
				ProjectWorkPackageView projectWorkPackageView = new ProjectWorkPackageView((Project)parent);
				projectWorkPackageView.errorMessage.connect(this, "showErrorMessage(String)");
				currWidget = projectWorkPackageView;
			}
			break;
			case WorkSessions: {
				currWidget = new ProjectWorkSessionsView((Project)parent);
			}
			break;
			case ProjectStaff: {
				ProjectStaffView projectStaffView = new ProjectStaffView((Project)parent);
				projectStaffView.errorMessage.connect(this, "showErrorMessage(String)");
				currWidget = projectStaffView;
			}
			}
			splitter.addWidget(currWidget);
		}
	}
	
	@SuppressWarnings("unused")
	private void textFilterChanged() {
		System.out.println("filter changed");
		projectTreeModel.layoutAboutToBeChanged.emit();
        Qt.CaseSensitivity caseSensitivity = Qt.CaseSensitivity.CaseInsensitive;

        QRegExp regExp = new QRegExp(ui.lineEdit.text(),
                                     caseSensitivity, QRegExp.PatternSyntax.RegExp);
        filterModel.setFilterRegExp(regExp);
        projectTreeModel.layoutChanged.emit();
	}
	
	@SuppressWarnings("unused")
	private void updateData() {
		updateTable();
	}
	
	@SuppressWarnings("unused")
	private void addData(Project project) {
		updateTable();
	}

	private void updateTable() {
		try {
			projectTreeModel.updateProjectsModel();
		} catch (WaktuGeneralException e) {
			showErrorMessage(e.getMessage());
		}
		projectTreeModel.layoutAboutToBeChanged.emit();
		projectTreeModel.dataChanged.emit(projectTreeModel.index(0, 0), projectTreeModel.index(projectTreeModel.rowCount(), projectTreeModel.columnCount()));
		filterModel.dataChanged.emit(filterModel.index(0, 0), filterModel.index(projectTreeModel.rowCount(), projectTreeModel.columnCount()));
        projectTreeModel.layoutChanged.emit();
	}
	
	@Override
	protected void contextMenuEvent(QContextMenuEvent event) {
        QMenu menu = new QMenu(this);
        QAction closeAction = new QAction(tr("Close"),menu);
        closeAction.triggered.connect(this, "closeWindow()");
        menu.addAction(closeAction);
        menu.exec(event.globalPos());
	}
	
	@SuppressWarnings("unused")
	private void closeWindow() {
		setVisible(false);
	}

	public void addProject() {
		if (currWidget != null) {
			currWidget.setParent(null);
		}
		currWidget = new ProjectDataView(null);
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
