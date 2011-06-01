package ch.hsr.waktu.gui.qt.view;

import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QCheckBox;

public class IndexCheckbox extends QCheckBox {

	private QModelIndex index;
	private WorkPackage workPackage;
	public Signal1<String> errorMessage = new Signal1<String>();

	public IndexCheckbox(final QModelIndex index, final WorkPackage wp, final boolean state) {
		setChecked(state);
		stateChanged.connect(this, "stateChanged()");
		this.index = index;
		this.workPackage = wp;
	}

	@SuppressWarnings("unused")
	private void stateChanged() {
		workPackage.setActiveState(!isChecked());
		try {
			WorkPackageController.getInstance().updateWorkPackage(workPackage);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}

	public QModelIndex getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "IndexButton with index: row:" + index.row() + " column: "
				+ index.column();
	}

}
