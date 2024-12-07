/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author diegotobar
 */
public class Obra {
    
    private String tipo;
    private String genero;
    private String formato;
    private String dimensiones;
    private String titulo;
    private String descripcion;
    private int publicacion;
    private String editorial;
    private String ubicacion;
    private String codigo;
    private String idioma;
    private int cantidad;
    private String autor;
            
    public Obra() {
    }

    public Obra(String tipo, String genero, String formato, String dimensiones, String titulo, String descripcion, int publicacion, String editorial, String ubicacion, String codigo, String idioma, int cantidad, String autor) {
        this.tipo = tipo;
        this.genero = genero;
        this.formato = formato;
        this.dimensiones = dimensiones;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.publicacion = publicacion;
        this.editorial = editorial;
        this.ubicacion = ubicacion;
        this.codigo = codigo;
        this.idioma = idioma;
        this.cantidad = cantidad;
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getGenero() {
        return genero;
    }

    public String getFormato() {
        return formato;
    }

    public String getDimensiones() {
        return dimensiones;
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


    public void setTipo(String tipo_obra) {
        this.tipo = tipo_obra;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
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
