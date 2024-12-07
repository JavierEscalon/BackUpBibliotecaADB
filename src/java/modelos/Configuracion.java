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
public class Configuracion {

    private int configuracionId;
    private int limitePrestamos;
    private double moraDiaria;
    private int rolId;
    private String nombreRol;

    public Configuracion() {
    }

    public Configuracion(int configuracionId, int limitePrestamos, double moraDiaria, int rolId, String nombreRol) {
        this.configuracionId = configuracionId;
        this.limitePrestamos = limitePrestamos;
        this.moraDiaria = moraDiaria;
        this.rolId = rolId;
        this.nombreRol = nombreRol;
    }

    public int getConfiguracionId() {
        return configuracionId;
    }

    public void setConfiguracionId(int configuracionId) {
        this.configuracionId = configuracionId;
    }

    public int getLimitePrestamos() {
        return limitePrestamos;
    }

    public void setLimitePrestamos(int limitePrestamos) {
        this.limitePrestamos = limitePrestamos;
    }

    public double getMoraDiaria() {
        return moraDiaria;
    }

    public void setMoraDiaria(double moraDiaria) {
        this.moraDiaria = moraDiaria;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

}
