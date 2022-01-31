package pro.abdiel.ciem.models;

public class NotificationsModel {

    private int Image;
    private String asunto;
    private String mensaje;
    private String uuid;
    public NotificationsModel() {

    }

    public NotificationsModel( int image, String uuid, String asunto, String mensaje) {
        Image = image;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
