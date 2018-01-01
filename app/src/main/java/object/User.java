package object;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    public static final boolean FEMALE = false;
    public static final boolean MALE = false;

    @PrimaryKey
    private String id;
    private String name;
    private Date birthdate;
    private Boolean gender;
    private String email;
    private String description;
    private RealmList<Project> projects;
    private Date lastupdate;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Boolean getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return "http://graph.facebook.com/" + id + "/picture";
    }

    public String getDescription() {
        return description;
    }

    public RealmList<Project> getProjects() {
        return projects;
    }

    public JSONObject getJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("name", name);
            obj.put("email", email);
            obj.put("birthdate", birthdate);
            obj.put("gender", gender);
            obj.put("description", description);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void SetCurrentUser(final String id, final String name, final String email) {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).equalTo("id", id).findFirst();
        if (user == null) {
            realm.beginTransaction();
            realm.copyToRealm(new User(id, name, email));
            realm.commitTransaction();
        }
    }

    public Date getLastupdate() {
        return lastupdate;
    }
}
