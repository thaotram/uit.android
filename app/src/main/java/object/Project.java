package object;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.annotations.PrimaryKey;

public class Project extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Task> tasks;
    private RealmList<User> members;
    private String description;
    private User assigned;
    private RealmList<String> tags;
    private RealmList<Channel> channels;
    private Date createdate;
    private Date deadline;

    public Project() {
    }

    public Project(int id, String name, String description, User assigned, String deadline, RealmList<User> members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assigned = assigned;
        this.createdate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format
                = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.deadline = date;
    }

    ///Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RealmList<Task> getTasks() {
        return tasks;
    }

    public int getNumberSameStatusTasks(int Status){
        int count=0;
        for(Task each:this.tasks){
            if (each.getStatus() == Status) {
                count++;
            }
        }
        return count;
    }

    public RealmQuery<Task> getSameStatusTasks(int Status) {
        return tasks.where().equalTo("status", Status);
    }

    public RealmList<User> getMembers() {
        return members;
    }

    public String getDescription() {
        return description;
    }

    public User getAssigned() {
        return assigned;
    }

    public RealmList<String> getTags() {
        return tags;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public RealmList<Channel> getChannels() {
        return channels;
    }

    public Date getDeadline() {
        return deadline;
    }

    ///Add tung phan tu (channel, task, member<>(), tag)
    public void addChannel(Channel channel) {
        this.channels.add(channel);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addMembers(RealmList<User> members) {
        this.members.addAll(members);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public JSONObject getJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("name", name);
            obj.put("tasks", tasks);
            obj.put("members", members);
            obj.put("description", description);
            obj.put("assigned", assigned);
            obj.put("tags", tags);
            obj.put("channels", channels);
            obj.put("createdate", createdate);
            obj.put("deadline", deadline);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

