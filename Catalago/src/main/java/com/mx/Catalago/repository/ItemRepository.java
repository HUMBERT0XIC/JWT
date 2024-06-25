package com.mx.Catalago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mx.Catalago.entity.Item;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	//Metodo para la busqueda de items
	List<Item> findByNombreContaining(String nombre);

}
