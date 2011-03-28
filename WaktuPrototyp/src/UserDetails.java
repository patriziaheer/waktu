import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QSplitter;
import com.trolltech.qt.gui.QWidget;


public class UserDetails extends QWidget {

	Ui_UserDetails ui = new Ui_UserDetails();
	private UserModel model = new UserModel();
	private QSplitter splitter;
	private QWidget currWidget;
	private int u = 0;
	
	public UserDetails() {
		ui.setupUi(this);
		ui.treeView.setModel(model);
		
		splitter = new QSplitter(Qt.Orientation.Horizontal);
		splitter.addWidget(ui.treeView);
		//splitter.addWidget(ui.widget);
		
		ui.gridLayout.removeWidget(ui.treeView);
		ui.gridLayout.removeWidget(ui.widget);
		ui.gridLayout.addWidget(splitter);
		
		ui.treeView.selectionModel().selectionChanged.connect(this, "selectionChanged()");
	}
	
	public void selectionChanged() {
		Object o = model.indexToValue(ui.treeView.selectionModel().selectedIndexes().get(0));
		if (o instanceof UserProperties) {
			UserProperties properties = (UserProperties)o;
			if (properties.getDescription().equals("Daten")) {
				if (currWidget != null) {
					currWidget.setParent(null);
				}
				currWidget = new UserData(Domain.getInstance().getUsers().get(0));
				splitter.addWidget(currWidget);
			} else if (properties.getDescription().equals("Stunden")) {
				if (currWidget != null) {
					currWidget.setParent(null);
				}
				currWidget = new Calendar();
				splitter.addWidget(currWidget);
			}
		}
		splitter.addWidget(currWidget);
	}
	
}
