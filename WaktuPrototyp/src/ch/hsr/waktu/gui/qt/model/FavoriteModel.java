package ch.hsr.waktu.gui.qt.model;

import java.util.List;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.FavoriteController;
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.QTime;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;

public class FavoriteModel extends QAbstractItemModel {

    private Logger logger = Logger.getLogger(FavoriteModel.class);

    private List<Favorite> favorites;
    private Usr usr;
    private QModelIndex editable = null;

    public FavoriteModel(Usr usr) throws WaktuException {
        this.usr = usr;
        updateFavoriteModel();
    }

    @Override
    public int columnCount(QModelIndex arg0) {
        return 4;
    }

    @Override
    public int rowCount(QModelIndex arg0) {
        return favorites.size();
    }

    @Override
    public Object data(QModelIndex index, int role) {
        if (Qt.ItemDataRole.DisplayRole == role
                || Qt.ItemDataRole.EditRole == role
                || Qt.ItemDataRole.ToolTipRole == role) {
            Favorite fav = favorites.get(index.row());
            switch (index.column()) {
            case 0:
                return fav.getWorkPackageID().getProject().getDescription()
                        + "\n" + fav.getWorkPackageID();
            case 1:
                return TimeUtil.convertGregorianToQDateTime(fav.getStartTime())
                        .time();
            case 2:
                return TimeUtil.convertGregorianToQDateTime(fav.getEndTime())
                        .time();
            case 3:
                return "";
            }
        } else if (Qt.ItemDataRole.SizeHintRole == role) {
            switch (index.column()) {
            case 0:
                return new QSize(130, 60);
            case 1:
                return new QSize(50, 60);
            case 2:
                return new QSize(50, 60);
            case 3:
                return new QSize(10, 60);
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
                return QCoreApplication.translate("FavoriteModel",
                        "Workpackage");
            case 1:
                return QCoreApplication.translate("FavoriteModel", "Start");
            case 2:
                return QCoreApplication.translate("FavoriteModel", "End");
            case 3:
                return "";
            }
        } else if (Qt.ItemDataRole.SizeHintRole == role
                && Qt.Orientation.Vertical == orientation) {
            return new QSize(0, 60);
        } else if (Qt.ItemDataRole.SizeHintRole == role
                && Qt.Orientation.Horizontal == orientation) {
            switch (section) {
            case 0:
                return new QSize(130, 30);
            case 1:
                return new QSize(50, 30);
            case 2:
                return new QSize(50, 30);
            case 3:
                return new QSize(10, 30);
            }
        }
        return super.headerData(section, orientation, role);
    }

    @Override
    public ItemFlags flags(QModelIndex index) {
        if (editable == null) {
            return super.flags(index);
        }
        if (index.row() == editable.row()
                && index.column() != columnCount() - 1 && index.column() != 0) {
            Qt.ItemFlag[] flags = { Qt.ItemFlag.ItemIsEditable,
                    Qt.ItemFlag.ItemIsSelectable, Qt.ItemFlag.ItemIsEnabled };
            ItemFlags f = super.flags(index);
            f.set(flags);
            return f;
        }
        return super.flags(index);
    }

    @Override
    public boolean setData(QModelIndex index, Object value, int role) {
        Favorite favorite = favorites.get(index.row());
        switch (index.column()) {
        case 1: {
            QDateTime dateTime = new QDateTime(new QDate(1900, 01, 01),
                    (QTime) value);
            if (dateTime.compareTo(TimeUtil
                    .convertGregorianToQDateTime(favorite.getEndTime())) < 0) {
                favorite.setStartTime(TimeUtil
                        .convertQDateTimeToGregorian(dateTime));
            }
        }
            break;
        case 2: {
            QDateTime dateTime = new QDateTime(new QDate(1900, 01, 01),
                    (QTime) value);
            if (dateTime.compareTo(TimeUtil
                    .convertGregorianToQDateTime(favorite.getStartTime())) > 0) {
                favorite.setEndTime(TimeUtil
                        .convertQDateTimeToGregorian(dateTime));
            }
        }
            break;
        }
        return super.setData(index, value, role);
    }

    @Override
    public QModelIndex index(int row, int column, QModelIndex arg2) {
        return createIndex(row, column);
    }

    @Override
    public QModelIndex parent(QModelIndex arg0) {
        return null;
    }

    public QModelIndex getEditable() {
        return editable;
    }

    public void setEditable(QModelIndex editable) {
        this.editable = editable;
        if (editable == null) {
            logger.info("no editable row");
        } else {
            logger.info("Row " + editable.row() + " is editable");
        }
    }

    public void updateFavoriteModel() throws WaktuException {
        favorites = FavoriteController.getInstance().getFavorites(usr);
    }

    public Favorite getFavorite(int row) {
        return favorites.get(row);
    }

    @Override
    public int hashCode() {
        int hashCode = 31;
        hashCode += 23 * favorites.hashCode();
        hashCode += 23 * usr.hashCode();
        return hashCode;
    }
}
