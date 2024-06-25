package com.mx.Catalago.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mx.Catalago.entity.Item;
import com.mx.Catalago.repository.ItemRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public void run(String... args) throws Exception {
		// Limpia la tabla antes de agregar datos de ejemplo
		itemRepository.deleteAll();

		// Agrega datos de ejemplo
		Item item1 = new Item();
		item1.setId(1);
		item1.setNombre("detergente");
		item1.setDescripcion("limpiador de ropa");
		item1.setPrecio(100.0);

		Item item2 = new Item();
		item2.setId(2);
		item2.setNombre("jabon");
		item2.setDescripcion("limpiador corporal");
		item2.setPrecio(150.0);
		
		Item item3 = new Item();
		item3.setId(3);
		item3.setNombre("shampoo");
		item3.setDescripcion("acondicionador");
		item3.setPrecio(250.0);

		itemRepository.save(item1);
		itemRepository.save(item2);
		itemRepository.save(item3);
	}
}
