package interfaces;

import java.util.List;
import modelos.Obra;

public interface ObraCRUD {
    public List listarObras();
    public Obra lista(String codigoBarra);
    public boolean ingresarObra(Obra obra);
    public boolean editarObra(Obra obra);
    public boolean eliminarObra(String codigo);
}
