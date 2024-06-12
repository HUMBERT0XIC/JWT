package com.mx.Banco.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.Banco.dominio.Bancos;

@Repository
public interface BancosDao extends JpaRepository<Bancos, Integer> {
	
	@Procedure
    public int totalBancos();
	
}
