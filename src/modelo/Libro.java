package modelo;

public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String editorial;
    private Genero genero;
    private int copiasTotales;
    private int copiasDisponibles;
    private EstadoLibro estado;

    public Libro(String isbn, String titulo, String autor, int anioPublicacion, String editorial, Genero genero, int copiasTotales) {
        
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.genero = genero;
        this.copiasTotales = copiasTotales;
        this.copiasDisponibles = copiasTotales;
        this.estado = EstadoLibro.DISPONIBLE;

    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public int getAnioPublicacion() {
        return anioPublicacion;
    }
    public String getEditorial() {
        return editorial;
    }
    public Genero getGenero() {
        return genero;
    }
    public int getCopiasTotales() {
        return copiasTotales;
    }
    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public boolean hayCopiasDisponibles() {
        return copiasDisponibles > 0;
    }
    public void prestar() {
        if (copiasDisponibles <=0) {
            throw new IllegalArgumentException("No hay copias disponibles");
        }

        copiasDisponibles--;

        if (copiasDisponibles == 0) {
            estado = EstadoLibro.PRESTADO;
        }
    }

    public void devolver() {
        if (copiasDisponibles < copiasTotales) {
            copiasDisponibles++;
            estado = EstadoLibro.DISPONIBLE;
        }
    }

     public void reservar() {
            estado = EstadoLibro.RESERVADO;
        }

    
}
