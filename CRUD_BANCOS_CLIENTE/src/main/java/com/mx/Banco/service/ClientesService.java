package com.mx.Banco.service;

import java.util.List;

import com.mx.Banco.dominio.Clientes;

public interface ClientesService {

	public List<Clientes> getAllClientes();

	public Clientes getClienteById(int id);

	public Clientes saveCliente(Clientes cliente);

	public void deleteCliente(int id);

	public List<Clientes> filterClientesByEdad(int edadMin);

}
