package object;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {
    public static final int ONGOING = 0;
    public static final int ONHOLD = 1;
    public static final int COMPLETE = 2;

    @PrimaryKey
    private int id;
    private String name;
    private Date createdate;
    private Date deadline;
    private String description;
    private User assigned;
    private RealmList<User> members;
    private int status;

    public Task() {
    }

    public Task(int id, String name, String deadline, String description, User assigned) {
        this.id = id;
        this.name = name;
        this.createdate = new Date();
        this.description = description;
        this.assigned = assigned;
        this.status = ONGOING;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.deadline = date;
    }

    ///Getter
    public String getName() {
        return name;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public User getAssigned() {
        return assigned;
    }

    public RealmList<User> getMembers() {
        return members;
    }

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public long getDaysLeft() {
        long daysLeft = deadline.getTime() - (new Date()).getTime();
        return (daysLeft / (60 * 60 * 1000));
    }

    ///Setter
    public void addMembers(RealmList<User> members) {
        this.members.addAll(members);
    }

    public void setStatus(int status) {
        this.status = (status == ONGOING || status == COMPLETE) ? status : ONHOLD;
    }

}