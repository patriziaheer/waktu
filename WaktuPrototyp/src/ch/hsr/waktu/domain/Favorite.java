package ch.hsr.waktu.domain;

import java.util.GregorianCalendar;

public class Favorite {

    private int id;
    private GregorianCalendar endTime;
    private GregorianCalendar startTime;
    private Usr user;
    private WorkPackage workPackage;

    public Favorite() {

    }

    public Favorite(Usr userId, WorkPackage workPackage,
            GregorianCalendar startTime, GregorianCalendar endTime) {
        this.user = userId;
        this.workPackage = workPackage;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

    public void setEndTime(GregorianCalendar endTime) {
        this.endTime = endTime;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    public Usr getUser() {
        return user;
    }

    public void setUser(Usr usr) {
        this.user = usr;
    }

    public WorkPackage getWorkPackageID() {
        return workPackage;
    }

    public void setWorkPackageID(WorkPackage workPackageID) {
        workPackage = workPackageID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Favorite) {
            Favorite fav = (Favorite) obj;
            if (fav.id == id && fav.endTime.equals(endTime)
                    && fav.user.equals(user)
                    && fav.workPackage.equals(workPackage)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hashCode = 23;
        hashCode += 31 * id;
        hashCode += 31 * endTime.hashCode();
        hashCode += 31 * startTime.hashCode();
        hashCode += 31 * user.hashCode();
        hashCode += 31 * workPackage.hashCode();
        return hashCode;
    }

    @Override
    public String toString() {
        return id + startTime.toString() + endTime.toString()
                + workPackage.getDescription() + user.getUsername();
    }

}
