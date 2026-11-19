package org.example.Ejercicio2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Test
    void shouldReturnBookTitle_whenBookExists() {

        BookRepository mockRepository = mock(BookRepository.class);
        BookService bookService = new BookService(mockRepository);
        when(mockRepository.findById(1))
                .thenReturn(new Book(1, "Programacion en Java"));
        String result = bookService.getBookTitleById(1);

        assertEquals("Programacion en Java", result);
        verify(mockRepository).findById(1);
    }

    @Test
    void shouldReturnInvalidId_whenIdIsZero() {

        BookRepository mockRepository = mock(BookRepository.class);
        BookService bookService = new BookService(mockRepository);
        String result = bookService.getBookTitleById(0);

        assertEquals("Ingrese un ID válido", result);
        verifyNoInteractions(mockRepository);
    }

    @Test
    void shouldReturnNotFound_whenBookDoesNotExist() {

        BookRepository mockRepository = mock(BookRepository.class);
        BookService bookService = new BookService(mockRepository);
        when(mockRepository.findById(99)).thenReturn(null);
        String result = bookService.getBookTitleById(99);
        assertEquals("Libro no encontrado", result);
        verify(mockRepository).findById(99);
    }

    @Test
    void shouldReturnRequiredData_whenIdIsBlank() {

        BookRepository mockRepository = mock(BookRepository.class);
        BookService bookService = new BookService(mockRepository);
        String result = bookService.getBookTitleById("  ");
        assertEquals("Debe ingresar los datos requeridos", result);
        verifyNoInteractions(mockRepository);
    }

    @Test
    void shouldReturnInvalidId_whenIdHasLetters() {

        BookRepository mockRepository = mock(BookRepository.class);
        BookService bookService = new BookService(mockRepository);
        String result = bookService.getBookTitleById("abc");
        assertEquals("Ingrese un ID válido", result);
        verifyNoInteractions(mockRepository);
    }
}
