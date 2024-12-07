/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author HP
 */
public class Consulta {
    private String titulo;
    private int materialId;
    private int añoPublicacion;
    private String editorial;
    private String estado;
    private String descripcion;
    private String codigoBarras;
    private String idioma;
    private int idTipoMaterial;
    private String nombreTipoMaterial;
    private String autor;

    public Consulta() {
    }

    public Consulta(String titulo, int materialId, int añoPublicacion, String editorial, String estado, String descripcion, String codigoBarras, String idioma, int idTipoMaterial, String nombreTipoMaterial, String autor) {
        this.titulo = titulo;
        this.materialId = materialId;
        this.añoPublicacion = añoPublicacion;
        this.editorial = editorial;
        this.estado = estado;
        this.descripcion = descripcion;
        this.codigoBarras = codigoBarras;
        this.idioma = idioma;
        this.idTipoMaterial = idTipoMaterial;
        this.nombreTipoMaterial = nombreTipoMaterial;
        this.autor = autor;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(int idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
    }

    public String getNombreTipoMaterial() {
        return nombreTipoMaterial;
    }

    public void setNombreTipoMaterial(String nombreTipoMaterial) {
        this.nombreTipoMaterial = nombreTipoMaterial;
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }    
}
