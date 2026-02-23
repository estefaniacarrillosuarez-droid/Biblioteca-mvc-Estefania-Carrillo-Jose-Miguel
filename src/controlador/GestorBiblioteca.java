package controlador;

import modelo.*;

// Controlador principal que gestiona todas las operaciones de la biblioteca
public class GestorBiblioteca {
    
    // Arrays para almacenar libros y usuarios
    private Libro[] libros;
    private int cantidadLibros;
    private Usuario[] usuarios;
    private int cantidadUsuarios;
    private static final int MAX_LIBROS = 50;      // Capacidad máxima de libros
    private static final int MAX_USUARIOS = 20;    // Capacidad máxima de usuarios

    // Constructor que inicializa los arrays
    public GestorBiblioteca() {
        this.libros = new Libro[MAX_LIBROS];
        this.cantidadLibros = 0;
        this.usuarios = new Usuario[MAX_USUARIOS];
        this.cantidadUsuarios = 0;
    }

    // Agrega un libro al catálogo de la biblioteca
    public void agregarLibro(Libro libro) {
        libros[cantidadLibros] = libro;
        cantidadLibros++;
    }

    // Agrega un usuario al sistema
    public void agregarUsuario(Usuario usuario) {
        usuarios[cantidadUsuarios] = usuario;
        cantidadUsuarios++;
    }

    // Realiza un préstamo de libro a un usuario
    public void prestarLibro(String isbn, String idUsuario) {
        // Busca el libro y el usuario
        Libro libro = buscarLibroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        // Validaciones
        if (libro == null || usuario == null) {
            throw new IllegalArgumentException("Libro o usuario no encontrado");
        }
        if (!libro.hayCopiasDisponibles()) {
            throw new IllegalArgumentException("No hay copias disponibles");
        }
        if (!usuario.puedePrestar()) {
            throw new IllegalArgumentException("El usuario ya tiene 3 libros");
        }
        if (usuario.tieneLibroPrestado(isbn)) {
            throw new IllegalArgumentException("Ya tiene este libro");
        }
        if (!usuario.puedeVolverAPedir(isbn)) {
            throw new IllegalArgumentException("Debe esperar 7 dias desde la devolucion");
        }

        // Realiza el préstamo
        libro.prestar();
        Prestamo prestamo = new Prestamo(libro, usuario);
        usuario.agregarPrestamo(prestamo);
    }

    // Procesa la devolución de un libro
    public void devolverLibro(String isbn, String idUsuario) {
        // Busca el libro y el usuario
        Libro libro = buscarLibroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        // Validaciones
        if (libro == null || usuario == null) {
            throw new IllegalArgumentException("Libro o usuario no encontrado");
        }

        // Procesa la devolución
        libro.devolver();
        usuario.devolverPrestamo(isbn);
    }

    // Reserva un libro
    public void reservarLibro(String isbn) {
        Libro libro = buscarLibroPorIsbn(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("Libro no encontrado");
        }
        libro.reservar();
    }

    // Busca un libro por su ISBN
    public Libro buscarLibroPorIsbn(String isbn) {
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros[i].getIsbn().equals(isbn)) {
                return libros[i];
            }
        }
        return null;  // Si no lo encuentra, devuelve null
    }

    // Busca libros por título (búsqueda parcial)
    public Libro[] buscarLibrosPorTitulo(String titulo) {
        Libro[] resultado = new Libro[MAX_LIBROS];
        int cantidad = 0;
        
        // Busca libros que contengan el título buscado
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros[i].getTitulo().contains(titulo)) {
                resultado[cantidad] = libros[i];
                cantidad++;
            }
        }
        return resultado;
    }

    // Busca libros por género
    public Libro[] buscarLibrosPorGenero(Genero genero) {
        Libro[] resultado = new Libro[MAX_LIBROS];
        int cantidad = 0;
        
        // Busca libros del género especificado
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros[i].getGenero() == genero) {
                resultado[cantidad] = libros[i];
                cantidad++;
            }
        }
        return resultado;
    }

    // Busca un usuario por su ID
    public Usuario buscarUsuarioPorId(String id) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getId().equals(id)) {
                return usuarios[i];
            }
        }
        return null;  // Si no lo encuentra, devuelve null
    }

    // Obtiene el usuario que tiene prestado un libro específico
    public Usuario obtenerUsuarioConLibro(String isbn) {
        // Busca entre todos los usuarios
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].tieneLibroPrestado(isbn)) {
                return usuarios[i];
            }
        }
        return null;  // Si nadie lo tiene, devuelve null
    }

    // Getters
    public Libro[] getLibros() {
        return libros;
    }

    public int getCantidadLibros() {
        return cantidadLibros;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public int getCantidadUsuarios() {
        return cantidadUsuarios;
        
    }
}