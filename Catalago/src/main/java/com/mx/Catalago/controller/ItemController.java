package com.mx.Catalago.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.Catalago.entity.Item;
import com.mx.Catalago.repository.ItemRepository;
import com.mx.Catalago.service.EncryptionService;
import com.mx.Catalago.service.PokemonService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private PokemonService pokemonService;

	// Busqueda
	@GetMapping("/find")
	public ResponseEntity<List<Item>> getItems(@RequestParam(required = false) String nombre) {
		List<Item> items;
		if (nombre != null && !nombre.isEmpty()) {
			items = itemRepository.findByNombreContaining(nombre);
		} else {
			items = itemRepository.findAll();
		}
		return ResponseEntity.ok(items);
	}

	// Loggin
	@PostMapping("/login")
	public ResponseEntity<?> login() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	// Cifrar texto
	@PostMapping("/encrypt")
	public ResponseEntity<String> encryptText(@RequestBody String plainText) {
		try {
			String encryptedText = encryptionService.encrypt(plainText);
			return ResponseEntity.ok(encryptedText);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error al cifrar el texto: " + e.getMessage());
		}
	}

	// Obtener información de un Pokémon
	@GetMapping("/pokeInfo")
	public ResponseEntity<String> getPokemonInfo() {
		try {
			String pokemonInfo = pokemonService.getPokemonInfo();
			return ResponseEntity.ok(pokemonInfo);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error al obtener informacion de la Pokedex: " + e.getMessage());
		}
	}
}
