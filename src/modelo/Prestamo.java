package modelo;

import java.time.LocalDate;

// Esta clase representa el préstamo de un libro a un usuario
// Guarda las fechas, y controla el vencimiento y cuándo se puede volver a pedir el mismo libro

public class Prestamo {
    // Libro prestado
    private Libro libro;
    // Usuario que realiza el préstamo
    private Usuario usuario;
    // Fecha en la que se realiza
    private LocalDate fechaPrestamo;
    // Fecha límite para devolverlo (30 días después)
    private LocalDate fechaVencimiento;
    // Fecha en la que fue devuelto (si ya se devolvió)
    private LocalDate fechaDevolucion;
    
    // Al crear el préstamo guardamos la fecha actual y calculamos cúando vence
    public Prestamo(Libro libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaVencimiento = fechaPrestamo.plusDays(30);
    }
    // Devuelve true si el libro no se ha devuelto y pasó la fecha límite
    public boolean estaVencido() {
        return fechaDevolucion == null && LocalDate.now().isAfter(fechaVencimiento);
    }
    // Guarda la fecha en la que el usuario devuelve el libro
    public void registrarDevolucion() {
        fechaDevolucion = LocalDate.now();
    }
    // Comprobamos si el usuario puede pedir otra vez este libro, y deben pasar 7 días desde que lo devolvió
    public boolean puedeVolverAPedirlo() {
        if (fechaDevolucion == null)
            return false;
        return LocalDate.now().isAfter(fechaDevolucion.plusDays(7));
    }
    // Permiten consultar la información
    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
    
}
