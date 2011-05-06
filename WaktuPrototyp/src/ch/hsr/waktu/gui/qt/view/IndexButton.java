package ch.hsr.waktu.gui.qt.view;

import org.apache.log4j.Logger;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QPushButton;

public class IndexButton extends QPushButton {
	
	private Logger logger = Logger.getLogger(IndexButton.class);
	
	public enum EditStatus {
		Edit, Save
	}
	
	public Signal1<IndexButton> actionClicked = new Signal1<IndexButton>();
	private QModelIndex index;
	private EditStatus status;
	
	public IndexButton(QModelIndex index) {
		this("", index);
	}
	
	public IndexButton(String text, QModelIndex index) {
		super(text);
		clicked.connect(this, "clicked()");
		this.index = index;
		status = EditStatus.Edit;
	}
	
	@SuppressWarnings("unused")
	private void clicked() {
		logger.info("clicked");
		actionClicked.emit(this);
	}

	public QModelIndex getIndex() {
		return index;
	}
	
	public EditStatus getStatus() {
		return status;
	}

	public void setStatus(EditStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IndexButton with index: row:" + index.row() + " column: " + index.column() + " and status: " + status;
	}

}
