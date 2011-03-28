import com.trolltech.qt.gui.QWidget;


public class Calendar extends QWidget {
	
	Ui_Calendar ui = new Ui_Calendar();
	
	public Calendar() {
		ui.setupUi(this);
		ui.btnDown.clicked.connect(this, "downClicked()");
		ui.calendarWidget.setVisible(false);
		ui.calendarWidget.selectionChanged.connect(this, "calendarSelectionChanged()");
	}
	
	private void downClicked() {
		if (ui.calendarWidget.isVisible()) {
			ui.btnDown.setText("v");
			ui.calendarWidget.setVisible(false);
			setButtonVisibility(true);
		} else {
			ui.btnDown.setText("^");
			setButtonVisibility(false);
			ui.calendarWidget.setVisible(true);
		}
	}

	private void setButtonVisibility(boolean visible) {
		ui.btnLeft.setVisible(visible);
		ui.btnMo.setVisible(visible);
		ui.btnDi.setVisible(visible);
		ui.btnMi.setVisible(visible);
		ui.btnDo.setVisible(visible);
		ui.btnFri.setVisible(visible);
		ui.btnSa.setVisible(visible);
		ui.btnSo.setVisible(visible);
		ui.btnRight.setVisible(visible);
	}
	
	private void calendarSelectionChanged() {
		
	}
}
