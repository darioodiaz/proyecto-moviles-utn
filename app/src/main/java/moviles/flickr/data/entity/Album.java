package moviles.flickr.data.entity;

public class Album {
    private String id;
    private String titulo;
    private int cantidadFotos;

    public Album() {

    }

    public Album(String titulo, int cantidadFotos) {
        this.titulo = titulo;
        this.cantidadFotos = cantidadFotos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCantidadFotos() {
        return cantidadFotos;
    }

    public void setCantidadFotos(int cantidadFotos) {
        this.cantidadFotos = cantidadFotos;
    }

    public String getDescripcion() {
        return cantidadFotos + " fotos";
    }

}
