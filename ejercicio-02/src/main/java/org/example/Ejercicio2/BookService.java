package org.example.Ejercicio2;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getBookTitleById(String idIngresado) {

        if (idIngresado == null || idIngresado.trim().isEmpty()) {
            return "Debe ingresar los datos requeridos";
        }

        try {
            int id = Integer.parseInt(idIngresado.trim());
            return getBookTitleById(id);
        } catch (NumberFormatException e) {
            return "Ingrese un ID válido";
        }
    }

    public String getBookTitleById(int id) {

        if (id <= 0) {
            return "Ingrese un ID válido";
        }

        Book book = bookRepository.findById(id);

        if (book == null) {
            return "Libro no encontrado";
        }

        return book.getTitle();
    }
}
