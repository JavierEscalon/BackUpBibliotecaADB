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
public class Prestamo {
     private int prestamoId;
    private int usuarioId;
    private int materialId;
    private java.sql.Date fechaPrestamo;
    private java.sql.Date fechaDevolucionPrevista;
    private java.sql.Date fechaDevolucionReal;
    private String estado;
    private int mora;
    private String nombreUsuario;
    private String titulo;

    // Constructor vacío
    public Prestamo() {
    }

    // Constructor con todos los campos
    public Prestamo(int prestamoId, int usuarioId, int materialId, java.sql.Date fechaPrestamo, 
                    java.sql.Date fechaDevolucionPrevista, java.sql.Date fechaDevolucionReal, String estado,int mora,String nombreUsuario,String titulo) {
        this.prestamoId = prestamoId;
        this.usuarioId = usuarioId;
        this.materialId = materialId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
        this.mora = mora;
        this.nombreUsuario = nombreUsuario;
        this.titulo = titulo;
    }

    // Getters y Setters
    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public java.sql.Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(java.sql.Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public java.sql.Date getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }

    public void setFechaDevolucionPrevista(java.sql.Date fechaDevolucionPrevista) {
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
    }

    public java.sql.Date getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(java.sql.Date fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    } 

    public int getMora() {
        return mora;
    }

    public void setMora(int mora) {
        this.mora = mora;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
    
}
