package org.example.Ejercicio2;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Book> libros = Map.of(
                1, new Book(1, "Programacion en Java"),
                2, new Book(2, "Clean Code"),
                3, new Book(3, "Effective Java"));

        BookRepository repository = libros::get;
        BookService service = new BookService(repository);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Consulta de libros ===");
        while (true) {
            System.out.print("Ingrese el ID del libro (o 'salir'): ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String entrada = scanner.nextLine();
            if (entrada.trim().equalsIgnoreCase("salir")) {
                break;
            }
            System.out.println(service.getBookTitleById(entrada));
        }
    }
}
