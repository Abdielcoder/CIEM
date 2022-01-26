package pro.abdiel.ciem.models;

public class CardClientModel {

    private String username;
    private String delegacionId;
    private String profile;
    private String usersId;
    private int timeStamp;

    public CardClientModel() {
    }

    public CardClientModel(String username, String delegacionId, String profile, String usersId, int timeStamp) {
        this.username = username;
        this.delegacionId = delegacionId;
        this.profile = profile;
        this.usersId = usersId;
        this.timeStamp = timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDelegacionId() {
        return delegacionId;
    }

    public void setDelegacionId(String delegacionId) {
        this.delegacionId = delegacionId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
}
