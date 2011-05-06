package ch.hsr.waktu.gui.qt.view.projectmanagment;
import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.gui.qt.model.ProjectTreeModel;
import ch.hsr.waktu.gui.qt.model.SortFilterModel;
import ch.hsr.waktu.gui.qt.view.Ui_ManagmentDetails;

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
	private ProjectTreeModel model = new ProjectTreeModel();
	private QSplitter splitter;
	private QWidget currWidget = new QWidget();
	
	private SortFilterModel filterModel = new SortFilterModel();
	
	public ProjectDetails() {
		ui.setupUi(this);
		filterModel.setDynamicSortFilter(true);
		filterModel.setSourceModel(model);
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
		
	}
	
	@SuppressWarnings("unused")
	private void selectionChanged() {
		QModelIndex selectedIndex = filterModel.mapToSource(ui.treeView.selectionModel().selectedIndexes().get(0));
		Object selected = model.indexToValue(selectedIndex);
		Object parent = model.indexToValue(model.parent(selectedIndex));
		if (selected instanceof ProjectController.ProjectProperties) {
			if (currWidget != null) {
				currWidget.setParent(null);
			}
			switch ((ProjectController.ProjectProperties)selected) {
			case Data: {
				currWidget = new ProjectDataView((Project)parent);
			}
			break;
			case WorkPackages: {
				currWidget = new ProjectWorkPackageView((Project)parent);
			}
			break;
			case WorkSessions: {
				currWidget = new ProjectWorkSessionsView((Project)parent);
			}
			break;
			case ProjectStaff: {
				currWidget = new ProjectStaffView((Project)parent);
			}
			}
			splitter.addWidget(currWidget);
		}
	}
	
	@SuppressWarnings("unused")
	private void textFilterChanged() {
		System.out.println("filter changed");
		model.layoutAboutToBeChanged.emit();
        Qt.CaseSensitivity caseSensitivity = Qt.CaseSensitivity.CaseInsensitive;

        QRegExp regExp = new QRegExp(ui.lineEdit.text(),
                                     caseSensitivity, QRegExp.PatternSyntax.RegExp);
        filterModel.setFilterRegExp(regExp);
        model.layoutChanged.emit();
	}
	
	@SuppressWarnings("unused")
	private void updateData() {
		model.layoutAboutToBeChanged.emit();
		model.dataChanged.emit(model.index(0, 0), model.index(model.rowCount(), model.columnCount()));
		filterModel.dataChanged.emit(filterModel.index(0, 0), filterModel.index(model.rowCount(), model.columnCount()));
        model.layoutChanged.emit();
	}
	
	@SuppressWarnings("unused")
	private void addData(Project project) {
		model.layoutAboutToBeChanged.emit();
		model.dataChanged.emit(model.index(0, 0), model.index(model.rowCount(), model.columnCount()));
		filterModel.dataChanged.emit(model.index(0, 0), model.index(model.rowCount(), model.columnCount()));
        model.layoutChanged.emit();
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
	
}
