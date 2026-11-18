package com.pe.cibertec.consulta_clientes.domain.repository;

import com.pe.cibertec.consulta_clientes.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRepository {

    Optional<Cliente> findById(Long id);
}
