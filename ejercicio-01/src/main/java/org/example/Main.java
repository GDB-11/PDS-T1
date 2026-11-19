package org.example;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Producto> productos = Map.of(
                1, new Producto(1, "Laptop"),
                2, new Producto(2, "Mouse"),
                3, new Producto(3, "Teclado"));

        ProductoRepository repository = productos::get;
        ProductoService service = new ProductoService(repository);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Consulta de productos ===");
        while (true) {
            System.out.print("Ingrese el ID del producto (o 'salir'): ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String entrada = scanner.nextLine();
            if (entrada.trim().equalsIgnoreCase("salir")) {
                break;
            }
            System.out.println(service.consultarProducto(entrada));
        }
    }
}
