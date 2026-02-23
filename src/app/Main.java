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