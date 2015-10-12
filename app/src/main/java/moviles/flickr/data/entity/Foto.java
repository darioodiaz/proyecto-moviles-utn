package moviles.flickr.data.entity;

import android.graphics.Bitmap;

import java.util.List;

import moviles.flickr.services.PhotoService;

public class Foto {
    private String id;
    private String name;
    private List<Comentario> comentarios;
    private String albumId;
    private Bitmap thumbnail;
    private Bitmap photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Bitmap getPhoto() {
        if (this.photo == null) {
            PhotoService.getInstance().getBigPhotoAsStream(this.id);
        }
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
