package pro.abdiel.ciem.models;

public class ReadCredentialsModel {
    String name;
    String company;
    String date;
    String Hour;
    String Id;
    int ProfileImage;

    public ReadCredentialsModel(String name, String company, String date, String hour, String id, int profileImage) {
        this.name = name;
        this.company = company;
        this.date = date;
        Hour = hour;
        Id = id;
        ProfileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(int profileImage) {
        ProfileImage = profileImage;
    }
}
