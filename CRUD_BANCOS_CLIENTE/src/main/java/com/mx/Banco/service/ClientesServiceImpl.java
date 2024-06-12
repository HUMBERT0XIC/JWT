package com.mx.Banco.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Banco.dao.ClientesDao;
import com.mx.Banco.dominio.Clientes;

@Service
public class ClientesServiceImpl implements ClientesService {

    @Autowired
    private ClientesDao clientesRepository;

    @Override
    public List<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes getClienteById(int id) {
        return clientesRepository.findById(id).orElse(null);
    }

    @Override
    public Clientes saveCliente(Clientes cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public void deleteCliente(int id) {
        clientesRepository.deleteById(id);
    }

    @Override
    public List<Clientes> filterClientesByEdad(int edadMin) {
        return getAllClientes().stream()
                .filter(cliente -> cliente.getEdad() >= edadMin)
                .collect(Collectors.toList());
    }
  
}
