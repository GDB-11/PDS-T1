package org.example;

public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public String consultarProducto(String idTexto) {

        if (idTexto == null || idTexto.trim().isEmpty()) {
            return "Debe ingresar los datos requeridos";
        }

        int id;
        try {
            id = Integer.parseInt(idTexto.trim());
        } catch (NumberFormatException e) {
            return "Ingrese un ID válido";
        }
        if (id <= 0) {
            return "Ingrese un ID válido";
        }

        Producto producto = repository.buscarPorId(id);
        if (producto == null) {
            return "Producto no encontrado";
        }

        return producto.getNombre();
    }
}
