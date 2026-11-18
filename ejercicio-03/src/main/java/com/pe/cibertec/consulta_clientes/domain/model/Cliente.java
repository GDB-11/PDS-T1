package com.pe.cibertec.consulta_clientes.domain.model;

public record Cliente(Long id, String nombreCompleto, String email, boolean activo) {
}
