package modelo;

// Clase que representa un usuario de la biblioteca
public class Usuario {
    
    // Atributos privados del usuario
    private String id;                              // Identificador único del usuario
    private String nombre;                          // Nombre del usuario
    private Prestamo[] prestamosActuales;          // Array de préstamos activos
    private int cantidadPrestamosActuales;         // Contador de préstamos activos
    private Prestamo[] historialPrestamos;         // Array con historial de préstamos devueltos
    private int cantidadHistorial;                 // Contador del historial
    private static final int MAX_PRESTAMOS = 3;    // Máximo de libros que puede tener prestados
    private static final int MAX_HISTORIAL = 50;   // Máximo de registros en el historial

    // Constructor que inicializa un usuario
    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.prestamosActuales = new Prestamo[MAX_PRESTAMOS];
        this.cantidadPrestamosActuales = 0;
        this.historialPrestamos = new Prestamo[MAX_HISTORIAL];
        this.cantidadHistorial = 0;
    }

    // Verifica si el usuario puede pedir más libros prestados
    public boolean puedePrestar() {
        return cantidadPrestamosActuales < MAX_PRESTAMOS;
    }

    // Agrega un nuevo préstamo al usuario
    public void agregarPrestamo(Prestamo prestamo) {
        // Si ya tiene 3 libros, lanza una excepción
        if (!puedePrestar()) {
            throw new IllegalArgumentException("El usuario ya tiene 3 libros prestados");
        }
        // Agrega el préstamo al array y aumenta el contador
        prestamosActuales[cantidadPrestamosActuales] = prestamo;
        cantidadPrestamosActuales++;
    }

    // Devuelve un libro (lo quita de préstamos actuales y lo agrega al historial)
    public void devolverPrestamo(String isbn) {
        // Busca el préstamo en los préstamos actuales
        for (int i = 0; i < cantidadPrestamosActuales; i++) {
            if (prestamosActuales[i].getLibro().getIsbn().equals(isbn)) {
                // Registra la fecha de devolución
                prestamosActuales[i].registrarDevolucion();
                
                // Guarda el préstamo en el historial
                historialPrestamos[cantidadHistorial] = prestamosActuales[i];
                cantidadHistorial++;
                
                // Elimina el préstamo del array de préstamos actuales
                // Mueve todos los elementos una posición hacia atrás
                for (int j = i; j < cantidadPrestamosActuales - 1; j++) {
                    prestamosActuales[j] = prestamosActuales[j + 1];
                }
                prestamosActuales[cantidadPrestamosActuales - 1] = null;
                cantidadPrestamosActuales--;
                return;
            }
        }
        // Si no encuentra el libro, lanza una excepción
        throw new IllegalArgumentException("El usuario no tiene este libro prestado");
    }

    // Verifica si el usuario tiene actualmente un libro prestado
    public boolean tieneLibroPrestado(String isbn) {
        for (int i = 0; i < cantidadPrestamosActuales; i++) {
            if (prestamosActuales[i].getLibro().getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    // Verifica si el usuario puede volver a pedir un libro
    // (comprueba si han pasado 7 días desde la última devolución)
    public boolean puedeVolverAPedir(String isbn) {
        // Busca en el historial si ya tuvo este libro
        for (int i = 0; i < cantidadHistorial; i++) {
            if (historialPrestamos[i].getLibro().getIsbn().equals(isbn)) {
                // Si no han pasado 7 días, no puede pedirlo
                if (!historialPrestamos[i].puedeVolverAPedirlo()) {
                    return false;
                }
            }
        }
        return true;  // Si no está en el historial o ya pasaron 7 días, puede pedirlo
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Prestamo[] getPrestamosActuales() {
        return prestamosActuales;
    }

    public int getCantidadPrestamosActuales() {
        return cantidadPrestamosActuales;
    }

    public Prestamo[] getHistorialPrestamos() {
        return historialPrestamos;
    }

    public int getCantidadHistorial() {
        return cantidadHistorial;
    }
}
