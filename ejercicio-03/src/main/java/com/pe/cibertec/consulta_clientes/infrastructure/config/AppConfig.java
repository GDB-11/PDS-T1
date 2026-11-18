package com.pe.cibertec.consulta_clientes.infrastructure.config;

import com.pe.cibertec.consulta_clientes.application.usecase.ClienteService;
import com.pe.cibertec.consulta_clientes.domain.repository.ClienteRepository;
import com.pe.cibertec.consulta_clientes.infrastructure.adapter.in.web.ClienteConsoleController;
import com.pe.cibertec.consulta_clientes.infrastructure.adapter.out.persistence.InMemoryClienteRepository;

public final class AppConfig {

    private AppConfig() {
    }

    public static ClienteConsoleController clienteConsoleController() {
        ClienteRepository repository = new InMemoryClienteRepository();
        return new ClienteConsoleController(new ClienteService(repository));
    }
}
