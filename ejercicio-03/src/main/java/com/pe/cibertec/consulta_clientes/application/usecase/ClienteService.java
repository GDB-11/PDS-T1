package com.pe.cibertec.consulta_clientes.application.usecase;

import com.pe.cibertec.consulta_clientes.application.dto.ClienteResponse;
import com.pe.cibertec.consulta_clientes.domain.model.Cliente;
import com.pe.cibertec.consulta_clientes.domain.repository.ClienteRepository;

public class ClienteService {

    public static final String MSG_DATOS_REQUERIDOS = "Debe ingresar los datos requeridos";
    public static final String MSG_ID_INCORRECTO = "Ingrese un ID correcto";
    public static final String MSG_NO_REGISTRADO = "Cliente no registrado";
    public static final String MSG_INACTIVO = "Cliente inactivo";

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse consultarPorId(String idTexto) {
        long id = parsearId(idTexto);

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException(MSG_NO_REGISTRADO));

        if (!cliente.activo()) {
            throw new ClienteException(MSG_INACTIVO);
        }
        return new ClienteResponse(cliente.nombreCompleto(), cliente.email());
    }

    private long parsearId(String idTexto) {
        if (idTexto == null || idTexto.isBlank()) {
            throw new ClienteException(MSG_DATOS_REQUERIDOS);
        }
        try {
            long id = Long.parseLong(idTexto.trim());
            if (id <= 0) {
                throw new ClienteException(MSG_ID_INCORRECTO);
            }
            return id;
        } catch (NumberFormatException e) {
            throw new ClienteException(MSG_ID_INCORRECTO);
        }
    }
}
