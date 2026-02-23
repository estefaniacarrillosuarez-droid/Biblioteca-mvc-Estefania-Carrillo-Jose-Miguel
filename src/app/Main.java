package app;
import modelo.*;
import controlador.GestorBiblioteca;
import vista.Consola;

public class Main {
    public static void main(String[] args) {
        
        GestorBiblioteca gestor = new GestorBiblioteca();
        Consola consola = new Consola();

        Libro libro1 = new Libro("978-01", "El Quijote", "Cervantes", 1605, 
                                 "Planeta", Genero.NOVELA, 5);
        Libro libro2 = new Libro("978-02", "1984", "Orwell", 1949, 
                                 "Penguin", Genero.CIENCIA_FICCION, 3);
        Libro libro3 = new Libro("978-03", "El Hobbit", "Tolkien", 1937, 
                                 "Minotauro", Genero.FANTASIA, 4);

        gestor.agregarLibro(libro1);
        gestor.agregarLibro(libro2);
        gestor.agregarLibro(libro3);

        Usuario usuario1 = new Usuario("U001", "Ana Lopez");
        Usuario usuario2 = new Usuario("U002", "Carlos Ruiz");

        gestor.agregarUsuario(usuario1);
        gestor.agregarUsuario(usuario2);

        consola.mostrarResumenLibros(gestor);
        consola.mostrarResumenUsuarios(gestor);

        // ===== EJEMPLO 1 =====
        try {
            consola.mostrarMensaje("\nPrestando libro a Ana...");
            gestor.prestarLibro("978-01", "U001");
            consola.mostrarMensaje("Prestamo realizado");
        } catch (IllegalArgumentException e) {
            consola.mostrarMensaje("Error: " + e.getMessage());
        }

        consola.mostrarResumenUsuarios(gestor);

        try {
            consola.mostrarMensaje("\nDevolviendo libro...");
            gestor.devolverLibro("978-01", "U001");
            consola.mostrarMensaje("Devolucion realizada");
        } catch (IllegalArgumentException e) {
            consola.mostrarMensaje("Error: " + e.getMessage());
        }
    
        // ===== EJEMPLO 2 (NUEVO) =====
        try {
            consola.mostrarMensaje("\nPrestando 'El Hobbit' a Carlos...");
            gestor.prestarLibro("978-03", "U002");
            consola.mostrarMensaje("Prestamo realizado");
        } catch (IllegalArgumentException e) {
            consola.mostrarMensaje("Error: " + e.getMessage());
        }

        consola.mostrarResumenUsuarios(gestor);

        // ===== EJEMPLO 3 (NUEVO - Error por falta de stock) =====
        try {
            consola.mostrarMensaje("\nIntentando prestar muchas veces '1984'...");
            gestor.prestarLibro("978-02", "U001");
            gestor.prestarLibro("978-02", "U002");
            gestor.prestarLibro("978-02", "U001");
            gestor.prestarLibro("978-02", "U002"); // Puede provocar error si no hay stock
            consola.mostrarMensaje("Prestamos realizados");
        } catch (IllegalArgumentException e) {
            consola.mostrarMensaje("Error controlado: " + e.getMessage());
        }

        consola.mostrarMensaje("\nBuscando libros de fantasia...");
        Libro[] librosFantasia = gestor.buscarLibrosPorGenero(Genero.FANTASIA);
        consola.mostrarLibros(librosFantasia);

        Usuario usuarioConLibro = gestor.obtenerUsuarioConLibro("978-01");
        if (usuarioConLibro != null) {
            consola.mostrarMensaje("\nEl libro esta prestado a: " + usuarioConLibro.getNombre());
        } else {
            consola.mostrarMensaje("\nEl libro no esta prestado");
        }
    }
}