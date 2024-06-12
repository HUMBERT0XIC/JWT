package com.mx.Banco.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.Banco.dominio.Clientes;
import com.mx.Banco.service.ClientesService;
import com.mx.Banco.util.RestClient;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

	private static final Logger logger = LoggerFactory.getLogger(ClientesController.class);

	@Autowired
	private ClientesService clientesService;
	
	@Autowired
    private RestClient restClient;

	// Operación GET para obtener todos los clientes
	@GetMapping("/listar")
	public ResponseEntity<List<Clientes>> getAllClientes() {
		logger.info("Obteniendo todos los clientes");
		List<Clientes> clientesList = clientesService.getAllClientes();
		return new ResponseEntity<>(clientesList, HttpStatus.OK);
	}

	// Operación GET para obtener un cliente por su ID
	@GetMapping("/consultar/{id}")
	public ResponseEntity<Clientes> getClienteById(@PathVariable("id") int id) {
		logger.info("Consultando cliente con ID: {}", id);
		Clientes cliente = clientesService.getClienteById(id);
		if (cliente != null) {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} else {
			logger.warn("Cliente con ID {} no encontrado", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Operación POST para guardar un nuevo cliente
	@PostMapping("/nuevo")
	public ResponseEntity<Clientes> saveCliente(@RequestBody Clientes cliente) {
		logger.info("Guardando nuevo cliente: {}", cliente);
		Clientes savedCliente = clientesService.saveCliente(cliente);
		return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
	}

	// Operación DELETE para eliminar un cliente por su ID
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<Clientes> deleteCliente(@PathVariable("id") int id) {
		logger.info("Eliminando cliente con ID: {}", id);
		clientesService.deleteCliente(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Operación GET para filtrar clientes por edad
	@GetMapping("/filtrarEdad/{edadMin}")
	public ResponseEntity<List<Clientes>> filterClientesByAge(@PathVariable("edadMin") int edadMin) {
		logger.info("Filtrando clientes por edad mayor a {}", edadMin);
		List<Clientes> filteredClientes = clientesService.filterClientesByEdad(edadMin);
		return new ResponseEntity<>(filteredClientes, HttpStatus.OK);
	}

    // Endpoint para obtener un chiste de Chuck Norris
    @GetMapping("/chiste")
    public ResponseEntity<String> obtenerChiste() {
        String chiste = restClient.getChuckNorrisJoke();
        return new ResponseEntity<>(chiste, HttpStatus.OK);
    }
}
