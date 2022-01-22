package pro.abdiel.ciem.models;

public class ModelNotifications {
    int Image;
    String name;
    String message;

    public ModelNotifications(int image, String name, String message) {
        Image = image;
        this.name = name;
        this.message = message;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
