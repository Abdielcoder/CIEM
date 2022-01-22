package pro.abdiel.ciem.models;

public class ModelNotifications {
    int Image;
    String name;

    public ModelNotifications(int image, String name) {
        Image = image;
        this.name = name;
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
}
