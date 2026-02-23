package modelo;

// Representa un libro dentro del sistema de biblioteca
// Contiene la información necesaria y lógica para gestionar la disponibilidad, reservas y préstamos

public class Libro {
    // Identificador único
    private String isbn;
    // Datos del libro
    private String titulo;
    private String autor;
    // Información editorial sobre el libro
    private int anioPublicacion;
    private String editorial;
    // Género del libro (enum)
    private Genero genero;
    // El número total de copias en la biblioteca
    private int copiasTotales;
    // Copias disponibles actualmente para préstamo
    private int copiasDisponibles;
    // Estado del libro
    private EstadoLibro estado;
    
    // Contructor que asigna los valores iniciales del libro
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

    // Permiten acceder a la información del libro sin cambiarla
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
    // Comprueba si hay copias disponibles para préstamo
    public boolean hayCopiasDisponibles() {
        return copiasDisponibles > 0;
    }
    // Realiza el préstamo de una copia del libro 
    // Reduce las copias disponibles
    // Si se agotan, el estado del libro pasa a prestado
    public void prestar() {
        if (copiasDisponibles <=0) {
            throw new IllegalArgumentException("No hay copias disponibles");
        }

        copiasDisponibles--;

        if (copiasDisponibles == 0) {
            estado = EstadoLibro.PRESTADO;
        }
    }
    // Registra la devolución de una copia
    // Aumenta las copias disponibles y cambia el estado
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
