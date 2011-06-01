package ch.hsr.waktu.gui.qt.view;

import java.util.ArrayList;

import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.IcsParser;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPalette.ColorRole;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;

public class IcsImportView extends QWidget {
    private QTabWidget tabWidget;
    private QLabel lblStatus;

    public IcsImportView(final String path) {
        setWindowTitle("ICS Import");
        QVBoxLayout gLayout = new QVBoxLayout();
        this.setLayout(gLayout);
        tabWidget = new QTabWidget(this);
        lblStatus = new QLabel(this);
        gLayout.addWidget(tabWidget);
        gLayout.addWidget(lblStatus);

        ArrayList<WorkSession> workSessions;
        try {
            workSessions = IcsParser.parseIcsFile(path);
            for (WorkSession workSession : workSessions) {
                IcsImportDetailView icsImportDetailView = new IcsImportDetailView(
                        workSession);
                icsImportDetailView.errorMessage.connect(this,
                        "setErrorMessage(String)");
                icsImportDetailView.closeMe.connect(this,
                        "closeTab(IcsImportDetailView)");
                icsImportDetailView.initialize();
                tabWidget.addTab(icsImportDetailView,
                        workSession.getDescription());
            }
        } catch (WaktuException e) {
            setErrorMessage(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private void closeTab(final IcsImportDetailView icsImportDetailView) {
        if (tabWidget.count() > 1) {
            tabWidget.removeTab(tabWidget.indexOf(icsImportDetailView));
        } else {
            close();
        }
    }

    private void setErrorMessage(final String errorMessage) {
        lblStatus.setText(errorMessage);
        QPalette palette = lblStatus.palette();
        palette.setBrush(ColorRole.WindowText, new QBrush(QColor.red));
        lblStatus.setPalette(palette);
    }

}
