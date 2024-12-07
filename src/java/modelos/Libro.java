package modelos;

public class Libro {
    
     String isbn;
     String edicion;
     String titulo;
     String descripcion; 
     int publicacion;
     int paginas;
     String editorial;
     String autor;
     String formato;
     String dimensiones;
     String ubicacion;
     String codigo;
     String idioma;
     int cantidad;

    public Libro() {
    }
    
    
    public Libro(String isbn, String edicion, String titulo, String descripcion, int publicacion, int paginas, String editorial, String autor, String formato, String dimensiones, String ubicacion, String codigo, String idioma, int cantidad) {
        this.isbn = isbn;
        this.edicion = edicion;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.publicacion = publicacion;
        this.paginas = paginas;
        this.editorial = editorial;
        this.autor = autor;
        this.formato = formato;
        this.dimensiones = dimensiones;
        this.ubicacion = ubicacion;
        this.codigo = codigo;
        this.idioma = idioma;
        this.cantidad = cantidad;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEdicion() {
        return edicion;
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

    public int getPaginas() {
        return paginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAutor() {
        return autor;
    }

    
    public String getFormato() {
        return formato;
    }

    public String getDimensiones() {
        return dimensiones;
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
    

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
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

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    
    public void setAutor(String autor) {
        this.autor = autor ;
    }

    
    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
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

    

    
    public boolean insertNuevoLibro(String isbn, String edicion, String titulo, String descripcion, int publicacion, int paginas, String editorial, String autor, String formato, String dimensiones, String ubicacion, String codigo, String idioma, int cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
            
    
}