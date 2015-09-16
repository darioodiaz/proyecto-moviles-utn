package moviles.flickr.data.entity;

public class Album {
    private long id;
    private String titulo;
    private int cantidadFotos;

    public Album(String titulo, int cantidadFotos) {
        this.titulo = titulo;
        this.cantidadFotos = cantidadFotos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
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
