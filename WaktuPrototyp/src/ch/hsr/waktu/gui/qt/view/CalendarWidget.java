package ch.hsr.waktu.gui.qt.view;

import ch.hsr.waktu.guicontroller.LanguageController;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.gui.QPixmap;
import com.trolltech.qt.gui.QWidget;

public class CalendarWidget extends QWidget {

	private Ui_Calendar ui = new Ui_Calendar();
	public Signal0 dayChanged = new Signal0();
	private QDate currDate = QDate.currentDate();

	public CalendarWidget() {
		ui.setupUi(this);
		ui.lblLogo.setPixmap(new QPixmap("classpath:icons/logo_klein.png"));

		// calendar slots
		ui.btnMo.clicked.connect(this, "moClicked()");
		ui.btnDi.clicked.connect(this, "diClicked()");
		ui.btnMi.clicked.connect(this, "miClicked()");
		ui.btnDo.clicked.connect(this, "doClicked()");
		ui.btnFri.clicked.connect(this, "friClicked()");
		ui.btnSa.clicked.connect(this, "saClicked()");
		ui.btnSo.clicked.connect(this, "soClicked()");
		ui.btnLeft.clicked.connect(this, "leftClicked()");
		ui.btnRight.clicked.connect(this, "rightClicked()");
		ui.btnToday.clicked.connect(this, "todayClicked()");

		LanguageController.getInstance().languageChanged.connect(this,
				"translate()");

		updateCalendar();
	}

	public QDate getCurrentDate() {
		return currDate;
	}

	private void resetButtonEnabled() {
		ui.btnMo.setEnabled(true);
		ui.btnDi.setEnabled(true);
		ui.btnMi.setEnabled(true);
		ui.btnDo.setEnabled(true);
		ui.btnFri.setEnabled(true);
		ui.btnSa.setEnabled(true);
		ui.btnSo.setEnabled(true);
	}

	@SuppressWarnings("unused")
	private void moClicked() {
		if (currDate.dayOfWeek() == 7) {
			currDate = currDate.addDays(-6);
		} else {
			currDate = currDate.addDays((currDate.dayOfWeek() - 1) * -1);
		}
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void diClicked() {
		switch (currDate.dayOfWeek()) {
		case 7: {
			currDate = currDate.addDays(-5);
		}
			break;
		case 1: {
			currDate = currDate.addDays(1);
		}
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 2) * -1);
		}
		}
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void miClicked() {
		switch (currDate.dayOfWeek()) {
		case 7: {
			currDate = currDate.addDays(-4);
		}
			break;
		case 1:
		case 2:
			currDate = currDate.addDays(3 - currDate.dayOfWeek());
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 3) * -1);
		}
		}
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void doClicked() {
		switch (currDate.dayOfWeek()) {
		case 7: {
			currDate = currDate.addDays(-3);
		}
			break;
		case 1:
		case 2:
		case 3:
			currDate = currDate.addDays(4 - currDate.dayOfWeek());
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 4) * -1);
		}
		}
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void friClicked() {
		switch (currDate.dayOfWeek()) {
		case 7: {
			currDate = currDate.addDays(-2);
		}
			break;
		case 1:
		case 2:
		case 3:
		case 4:
			currDate = currDate.addDays(5 - currDate.dayOfWeek());
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 5) * -1);
		}
		}
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void saClicked() {
		switch (currDate.dayOfWeek()) {
		case 7: {
			currDate = currDate.addDays(-1);
		}
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			currDate = currDate.addDays(6 - currDate.dayOfWeek());
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 6) * -1);
		}
		}
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void soClicked() {
		currDate = currDate.addDays(7 - currDate.dayOfWeek());
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void leftClicked() {
		currDate = currDate.addDays(-7);
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void rightClicked() {
		currDate = currDate.addDays(7);
		updateCalendar();
	}

	private void updateCalendar() {
		ui.lblCurrDate.setText(currDate.toString("dd.MM.yy"));
		QDate startDate = new QDate();
		QDate endDate = new QDate();

		resetButtonEnabled();

		switch (currDate.dayOfWeek()) {
		case 1: {
			ui.btnMo.setEnabled(false);
			startDate = currDate;
			endDate = currDate.addDays(6);
		}
			break;
		case 2: {
			ui.btnDi.setEnabled(false);
			startDate = currDate.addDays(-1);
			endDate = currDate.addDays(5);
		}
			break;
		case 3: {
			ui.btnMi.setEnabled(false);
			startDate = currDate.addDays(-2);
			endDate = currDate.addDays(4);
		}
			break;
		case 4: {
			ui.btnDo.setEnabled(false);
			startDate = currDate.addDays(-3);
			endDate = currDate.addDays(3);
		}
			break;
		case 5: {
			ui.btnFri.setEnabled(false);
			startDate = currDate.addDays(-4);
			endDate = currDate.addDays(2);
		}
			break;
		case 6: {
			ui.btnSa.setEnabled(false);
			startDate = currDate.addDays(-5);
			endDate = currDate.addDays(1);
		}
			break;
		case 7: {
			ui.btnSo.setEnabled(false);
			startDate = currDate.addDays(-6);
			endDate = currDate;
		}
			break;
		}
		ui.lblStart.setText(startDate.toString("dd.MM.yy"));
		ui.lblEnd.setText(endDate.toString("dd.MM.yy"));
		dayChanged.emit();
	}

	@SuppressWarnings("unused")
	private void todayClicked() {
		currDate = QDate.currentDate();
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void translate() {
		ui.retranslateUi(this);
		updateCalendar();
	}

}
