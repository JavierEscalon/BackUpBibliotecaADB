/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import modelos.Tesis;

/**
 *
 * @author diegotobar
 */
public interface TesisCRUD {
    public List listarTesis();
    public Tesis lista(String codigoBarra);
    public boolean ingresarTesis(Tesis tesis);
    public boolean editarTesis(Tesis tesis);
    public boolean eliminarTesis(String codigo);    
}
