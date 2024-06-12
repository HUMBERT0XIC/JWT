package com.mx.Banco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.mx.Banco.dominio.Clientes;

@Repository
public interface ClientesDao extends JpaRepository<Clientes, Integer>{

}
