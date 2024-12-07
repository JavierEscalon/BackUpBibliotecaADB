/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author diegotobar
 */
public class Tesis {
    private String grado;
    private String institucion;
    private String director;
    private String area;
    private String defensa;
    private int paginas;
    private String titulo;
    private String descripcion; 
    private int publicacion;
    private String editorial;
    private String autor;
    private String ubicacion;
    private String codigo;
    private String idioma;
    private int cantidad;

    public Tesis() {
    }

    public Tesis(String grado, String institucion, String director, String area, String defensa, int paginas, String titulo, String descripcion, int publicacion, String editorial,String autor, String ubicacion, String codigo, String idioma, int cantidad) {
        this.grado = grado;
        this.institucion = institucion;
        this.director = director;
        this.area = area;
        this.defensa = defensa;
        this.paginas = paginas;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.publicacion = publicacion;
        this.editorial = editorial;
        this.autor = autor;
        this.ubicacion = ubicacion;
        this.codigo = codigo;
        this.idioma = idioma;
        this.cantidad = cantidad;
    }

    public String getGrado() {
        return grado;
    }

    public String getInstitucion() {
        return institucion;
    }

    public String getDirector() {
        return director;
    }

    public String getArea() {
        return area;
    }

    public String getDefensa() {
        return defensa;
    }

    public int getPaginas() {
        return paginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getAutor() {
        return autor;
    }


    public void setGrado(String grado) {
        this.grado = grado;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDefensa(String defensa) {
        this.defensa = defensa;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    
}
