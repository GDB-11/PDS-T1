package com.pe.cibertec.consulta_clientes.infrastructure.adapter.out.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryClienteRepositoryTest {

    private final InMemoryClienteRepository repository = new InMemoryClienteRepository();

    @Test
    void findById_existente_devuelveCliente() {
        assertTrue(repository.findById(1L).isPresent());
    }

    @Test
    void findById_inexistente_devuelveVacio() {
        assertTrue(repository.findById(999L).isEmpty());
    }
}
