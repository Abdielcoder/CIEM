package pro.abdiel.ciem.models;

public class NotificationsModel {
    int Image;
    String asunto;
    String mensaje;

    public NotificationsModel() {

    }

    public NotificationsModel(int image, String asunto, String mensaje) {
        Image = image;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
