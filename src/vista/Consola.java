package vista;

import modelo.*;
import controlador.GestorBiblioteca;

public class Consola {

    public void mostrarResumenLibros(GestorBiblioteca gestor) {
        System.out.println("\nRESUMEN DE LIBROS");

        for (int i = 0; i < gestor.getCantidadLibros(); i++) {
            Libro libro = gestor.getLibros()[i];

            System.out.println("ISBN: " + libro.getIsbn());
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Genero: " + libro.getGenero());
            System.out.println("Copias disponibles: "
                    + libro.getCopiasDisponibles() + "/"
                    + libro.getCopiasTotales());

        }
    }

    public void mostrarResumenUsuarios(GestorBiblioteca gestor) {
        System.out.println("\nRESUMEN DE USUARIOS");

        for (int i = 0; i < gestor.getCantidadUsuarios(); i++) {
            Usuario usuario = gestor.getUsuarios()[i];

            System.out.println("ID: " + usuario.getId());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Libros prestados: "
                    + usuario.getCantidadPrestamosActuales());

            for (int j = 0; j < usuario.getCantidadPrestamosActuales(); j++) {
                Prestamo prestamo = usuario.getPrestamosActuales()[j];
                System.out.println("  - " + prestamo.getLibro().getTitulo());
            }

        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarLibro(Libro libro) {
        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }

        System.out.println("\nInformacion del libro:");
        System.out.println("ISBN: " + libro.getIsbn());
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Anio: " + libro.getAnioPublicacion());
        System.out.println("Editorial: " + libro.getEditorial());
        System.out.println("Genero: " + libro.getGenero());
        System.out.println("Copias disponibles: "
                + libro.getCopiasDisponibles() + "/"
                + libro.getCopiasTotales());
    }

    public void mostrarLibros(Libro[] libros) {

        if (libros == null || libros.length == 0) {
            System.out.println("No se encontraron libros");
            return;
        }

        System.out.println("\nLibros encontrados:");

        boolean encontrado = false;

        for (int i = 0; i < libros.length; i++) {

            if (libros[i] != null) {  // evita NullPointerException
                System.out.println("- " + libros[i].getTitulo()
                        + " (" + libros[i].getIsbn() + ")");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron libros");
        }
    }
}