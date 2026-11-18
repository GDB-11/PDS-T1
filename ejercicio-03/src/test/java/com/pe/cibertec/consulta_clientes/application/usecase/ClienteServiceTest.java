package com.pe.cibertec.consulta_clientes.application.usecase;

import com.pe.cibertec.consulta_clientes.application.dto.ClienteResponse;
import com.pe.cibertec.consulta_clientes.domain.model.Cliente;
import com.pe.cibertec.consulta_clientes.domain.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void consultarPorId_clienteActivo_devuelveNombreYCorreo() {
        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(new Cliente(1L, "Ana Perez", "ana@mail.com", true)));

        ClienteResponse response = clienteService.consultarPorId("1");

        assertEquals("Ana Perez", response.nombreCompleto());
        assertEquals("ana@mail.com", response.email());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-5", "abc", "1.5"})
    void consultarPorId_idInvalido_lanzaIdIncorrecto(String id) {
        ClienteException ex = assertThrows(ClienteException.class, () -> clienteService.consultarPorId(id));

        assertEquals("Ingrese un ID correcto", ex.getMessage());
        verifyNoInteractions(clienteRepository);
    }

    @Test
    void consultarPorId_clienteNoExiste_lanzaNoRegistrado() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        ClienteException ex = assertThrows(ClienteException.class, () -> clienteService.consultarPorId("99"));

        assertEquals("Cliente no registrado", ex.getMessage());
        verify(clienteRepository).findById(99L);
    }

    @Test
    void consultarPorId_clienteInactivo_lanzaInactivo() {
        when(clienteRepository.findById(2L))
                .thenReturn(Optional.of(new Cliente(2L, "Luis Gomez", "luis@mail.com", false)));

        ClienteException ex = assertThrows(ClienteException.class, () -> clienteService.consultarPorId("2"));

        assertEquals("Cliente inactivo", ex.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "", "\t", "\n"})
    void consultarPorId_valorEnBlanco_lanzaDatosRequeridos(String id) {
        ClienteException ex = assertThrows(ClienteException.class, () -> clienteService.consultarPorId(id));

        assertEquals("Debe ingresar los datos requeridos", ex.getMessage());
        verify(clienteRepository, never()).findById(org.mockito.ArgumentMatchers.any());
    }
}
