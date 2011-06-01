package ch.hsr.waktu.gui.qt.model;

import java.util.List;

import ch.hsr.waktu.controller.datacontroller.ProjectStaffController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;

public class ProjectStaffModel extends QAbstractItemModel {

    private Project project;
    private List<Usr> usrs;

    public ProjectStaffModel(Project project) throws WaktuException {
        this.project = project;
        updateProjectStaffModel();
    }

    @Override
    public int columnCount(QModelIndex arg0) {
        return 3;
    }

    @Override
    public int rowCount(QModelIndex arg0) {
        return usrs.size();
    }

    @Override
    public Object data(QModelIndex index, int role) {
        if (Qt.ItemDataRole.DisplayRole == role) {
            Usr usr = usrs.get(index.row());
            switch (index.column()) {
            case 0:
                return usr.getName();
            case 1:
                return usr.getFirstname();
            case 2:
                return "";
            }
        } else if (Qt.ItemDataRole.SizeHintRole == role) {
            switch (index.column()) {
            case 0:
                return new QSize(400, 30);
            case 1:
                return new QSize(400, 30);
            case 2:
                return new QSize(50, 30);
            }
        }
        return null;
    }

    @Override
    public Object headerData(int section, Orientation orientation, int role) {
        if (Qt.ItemDataRole.DisplayRole == role
                && Qt.Orientation.Horizontal == orientation) {
            switch (section) {
            case 0:
                return QCoreApplication.translate("ProjectStaffModel", "Name");
            case 1:
                return QCoreApplication.translate("ProjectStaffModel",
                        "Firstname");
            case 2:
                return "";
            }
        } else if (Qt.ItemDataRole.SizeHintRole == role
                && Qt.Orientation.Vertical == orientation) {
            return new QSize(0, 20);
        } else if (Qt.ItemDataRole.SizeHintRole == role
                && Qt.Orientation.Horizontal == orientation) {
            switch (section) {
            case 0:
                return new QSize(400, 30);
            case 1:
                return new QSize(400, 30);
            case 2:
                return new QSize(50, 30);
            }
        }
        return super.headerData(section, orientation, role);
    }

    @Override
    public QModelIndex index(int row, int column, QModelIndex arg2) {
        return createIndex(row, column);
    }

    @Override
    public QModelIndex parent(QModelIndex arg0) {
        return null;
    }

    public void updateProjectStaffModel() throws WaktuException {
        usrs = ProjectStaffController.getInstance().getUsers(project);
    }

}
