package com.mx.Banco.service;

import com.mx.Banco.dao.ClientesDao;
import com.mx.Banco.dominio.Bancos;
import com.mx.Banco.dominio.Clientes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClientesServiceTest {

    @Mock
    private ClientesDao clientesDao;

    @InjectMocks
    private ClientesService clientesService = new ClientesServiceImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarCliente() {
        Clientes cliente = new Clientes();
        cliente.setIdCliente(1);
        cliente.setNombre("Juan");
        cliente.setApp("Perez");
        cliente.setSexo("M");
        cliente.setEdad(30);
        cliente.setDireccion("Calle nose");
        cliente.setNoContacto(1234567890);
        cliente.setRfc("JUANP123456");
        cliente.setBancoId(new Bancos());

        when(clientesDao.save(any(Clientes.class))).thenReturn(cliente);

        Clientes clienteGuardado = clientesService.saveCliente(cliente);
        assertEquals(cliente.getIdCliente(), clienteGuardado.getIdCliente());
        assertEquals(cliente.getNombre(), clienteGuardado.getNombre());
        assertEquals(cliente.getApp(), clienteGuardado.getApp());
        assertEquals(cliente.getSexo(), clienteGuardado.getSexo());
        assertEquals(cliente.getEdad(), clienteGuardado.getEdad());
        assertEquals(cliente.getDireccion(), clienteGuardado.getDireccion());
        assertEquals(cliente.getNoContacto(), clienteGuardado.getNoContacto());
        assertEquals(cliente.getRfc(), clienteGuardado.getRfc());
        assertEquals(cliente.getBancoId().getIdBanco(), clienteGuardado.getBancoId().getIdBanco());
    }
}
