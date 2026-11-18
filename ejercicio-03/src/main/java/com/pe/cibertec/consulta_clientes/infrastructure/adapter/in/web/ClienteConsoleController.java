package com.pe.cibertec.consulta_clientes.infrastructure.adapter.in.web;

import com.pe.cibertec.consulta_clientes.application.dto.ClienteResponse;
import com.pe.cibertec.consulta_clientes.application.usecase.ClienteException;
import com.pe.cibertec.consulta_clientes.application.usecase.ClienteService;

import java.util.Scanner;

public class ClienteConsoleController {

    private final ClienteService clienteService;

    public ClienteConsoleController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Consulta de clientes ===");
        while (true) {
            System.out.print("Ingrese el ID del cliente (o 'salir'): ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String entrada = scanner.nextLine();
            if (entrada.trim().equalsIgnoreCase("salir")) {
                break;
            }
            try {
                ClienteResponse cliente = clienteService.consultarPorId(entrada);
                System.out.println("Nombre: " + cliente.nombreCompleto());
                System.out.println("Correo: " + cliente.email());
            } catch (ClienteException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
