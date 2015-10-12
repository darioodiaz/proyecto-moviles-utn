package moviles.flickr.data.entity;

import java.util.Date;

public class Comentario {
    private String id;
    private String body;
    private String fotoId;
    private Date fecha;
    private String autor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFotoId() {
        return fotoId;
    }

    public void setFotoId(String fotoId) {
        this.fotoId = fotoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAutor(){return autor;}

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
