package ch.hsr.waktu.presentation;
import ch.hsr.waktu.domain.User;
import ch.hsr.waktu.domain.UserProperties;
import ch.hsr.waktu.presentation.juis.Ui_UserDetails;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QSplitter;
import com.trolltech.qt.gui.QWidget;


public class UserDetails extends QWidget {

	Ui_UserDetails ui = new Ui_UserDetails();
	private UserModel model = new UserModel();
	private QSplitter splitter;
	private QWidget currWidget = new QWidget();
	
	private SortFilter filterModel = new SortFilter();
	
	public UserDetails() {
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
	}
	
	@SuppressWarnings("unused")
	private void selectionChanged() {
		QModelIndex selectedIndex = filterModel.mapToSource(ui.treeView.selectionModel().selectedIndexes().get(0));
		Object selected = model.indexToValue(selectedIndex);
		Object parent = model.indexToValue(model.parent(selectedIndex));
		if (selected instanceof UserProperties) {
			UserProperties properties = (UserProperties)selected;
			if (properties.getDescription().equals("Daten")) {
				if (currWidget != null) {
					currWidget.setParent(null);
				}
				currWidget = new UserData((User)parent);
				splitter.addWidget(currWidget);
			} else if (properties.getDescription().equals("Stunden")) {
				if (currWidget != null) {
					currWidget.setParent(null);
				}
				currWidget = new Calendar();
				splitter.addWidget(currWidget);
			}
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
	
	@Override
	protected void contextMenuEvent(QContextMenuEvent event) {
        QMenu menu = new QMenu(this);
        QAction closeAction = new QAction(tr("Close"),menu);
        closeAction.triggered.connect(this, "closeApp()");
        menu.addAction(closeAction);
        menu.exec(event.globalPos());
	}
	
	@SuppressWarnings("unused")
	private void closeApp() {
		System.exit(0);
	}
	
}
