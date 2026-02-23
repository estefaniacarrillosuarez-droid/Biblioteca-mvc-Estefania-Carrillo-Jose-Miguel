package modelo;

import java.time.LocalDate;

public class Prestamo {

    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion;

    public Prestamo(Libro libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaVencimiento = fechaPrestamo.plusDays(30);
    }

    public boolean estaVencido() {
        return fechaDevolucion == null && LocalDate.now().isAfter(fechaVencimiento);
    }

    public void registrarDevolucion() {
        fechaDevolucion = LocalDate.now();
    }

    public boolean puedeVolverAPedirlo() {
        if (fechaDevolucion == null)
            return false;
        return LocalDate.now().isAfter(fechaDevolucion.plusDays(7));
    }

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
