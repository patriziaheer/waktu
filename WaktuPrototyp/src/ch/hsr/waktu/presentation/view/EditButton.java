package ch.hsr.waktu.presentation.view;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QPushButton;

public class EditButton extends QPushButton {
	
	public Signal1<EditButton> editClicked = new Signal1<EditButton>();
	private QModelIndex index;
	
	public EditButton(String text, QModelIndex index) {
		super(text);
		clicked.connect(this, "clicked()");
		this.index = index;
	}
	
	@SuppressWarnings("unused")
	private void clicked() {
		editClicked.emit(this);
	}

	public QModelIndex getIndex() {
		return index;
	}

}
