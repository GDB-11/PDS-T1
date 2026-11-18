import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.example.Producto;
import org.example.ProductoRepository;
import org.example.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductoServiceTest {

    private ProductoRepository repositoryMock;
    private ProductoService service;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(ProductoRepository.class);
        service = new ProductoService(repositoryMock);
    }

    @Test
    void deberiaRetornarNombreCuandoProductoExiste() {
        when(repositoryMock.buscarPorId(10)).thenReturn(new Producto(10, "Laptop"));

        String resultado = service.consultarProducto("10");

        assertEquals("Laptop", resultado);
        verify(repositoryMock).buscarPorId(10);
    }

    @Test
    void deberiaMostrarErrorCuandoIdEsInvalido() {
        String resultado = service.consultarProducto("-5");

        assertEquals("Ingrese un ID válido", resultado);
        verify(repositoryMock, never()).buscarPorId(anyInt());

    }

    @Test
    void deberiaMostrarErrorCuandoIdNoEsNumerico() {
        String resultado = service.consultarProducto("abc");

        assertEquals("Ingrese un ID válido", resultado);
        verify(repositoryMock, never()).buscarPorId(anyInt());
    }

    @Test
    void deberiaMostrarMensajeCuandoProductoNoExiste() {
        when(repositoryMock.buscarPorId(99)).thenReturn(null);

        String resultado = service.consultarProducto("99");

        assertEquals("Producto no encontrado", resultado);
        verify(repositoryMock).buscarPorId(99);
    }

    @Test
    void deberiaMostrarMensajeCuandoCampoEstaEnBlanco() {
        String resultado = service.consultarProducto("   ");

        assertEquals("Debe ingresar los datos requeridos", resultado);
        verify(repositoryMock, never()).buscarPorId(anyInt());
    }
}
