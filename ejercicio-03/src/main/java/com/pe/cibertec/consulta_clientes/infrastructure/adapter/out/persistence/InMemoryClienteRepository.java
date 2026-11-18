package com.pe.cibertec.consulta_clientes.infrastructure.adapter.out.persistence;

import com.pe.cibertec.consulta_clientes.domain.model.Cliente;
import com.pe.cibertec.consulta_clientes.domain.repository.ClienteRepository;

import java.util.Map;
import java.util.Optional;

public class InMemoryClienteRepository implements ClienteRepository {

    private final Map<Long, Cliente> clientes = Map.of(
            1L, new Cliente(1L, "Ana Perez Lopez", "ana.perez@mail.com", true),
            2L, new Cliente(2L, "Luis Gomez Rios", "luis.gomez@mail.com", false),
            3L, new Cliente(3L, "Maria Torres Diaz", "maria.torres@mail.com", true)
    );

    @Override
    public Optional<Cliente> findById(Long id) {
        return Optional.ofNullable(clientes.get(id));
    }
}
