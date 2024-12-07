package interfaces;

import java.util.List;
import modelos.Libro;

public interface LibroCRUD {
    
    public List listarLibros();
    public Libro lista(String isbn);
    public boolean ingresarLibro(Libro libro);
    public boolean editarLibro(Libro libro);
    public boolean eliminarLibro(String codigo);
    
}
