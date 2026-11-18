package com.pe.cibertec.consulta_clientes;

import com.pe.cibertec.consulta_clientes.infrastructure.config.AppConfig;

public class ConsultaClientesApplication {

    public static void main(String[] args) {
        AppConfig.clienteConsoleController().run();
    }
}
