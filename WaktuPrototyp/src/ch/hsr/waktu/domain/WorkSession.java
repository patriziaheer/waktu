package ch.hsr.waktu.domain;

import java.util.GregorianCalendar;

public class WorkSession {

    private int id;
    private GregorianCalendar endTime;
    private GregorianCalendar startTime;
    private Usr user;
    private String description;
    private WorkPackage workPackage;

    public WorkSession() {

    }

    public WorkSession(Usr user, WorkPackage workPackage,
            GregorianCalendar startTime, GregorianCalendar endTime,
            String description) {
        this.user = user;
        this.workPackage = workPackage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public GregorianCalendar getEnd() {
        return endTime;
    }

    public void setEnd(GregorianCalendar end) {
        this.endTime = end;
    }

    public GregorianCalendar getStart() {
        return startTime;
    }

    public void setStart(GregorianCalendar start) {
        this.startTime = start;
    }

    public Usr getUser() {
        return user;
    }

    public void setUser(Usr user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WorkPackage getWorkPackage() {
        return workPackage;
    }

    public void setWorkPackage(WorkPackage workPackage) {
        this.workPackage = workPackage;
    }

    @Override
    public String toString() {
        return id + "";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof WorkSession) {
            WorkSession ws = (WorkSession) obj;
            if (ws.id == id && ws.user.equals(user)
                    && ws.workPackage.equals(workPackage)
                    && ws.description.equals(description)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hashCode = 23;
        hashCode += 31 * id;
        hashCode += 31 * user.hashCode();
        hashCode += 31 * workPackage.hashCode();
        hashCode += 31 * description.hashCode();
        hashCode += 31 * startTime.hashCode();
        hashCode += 31 * endTime.hashCode();
        return hashCode;
    }
}
