package com.mx.Banco.service;

import java.util.List;

import com.mx.Banco.dominio.Bancos;

public interface BancosService {

	public List<Bancos> getAllBancos();

	public Bancos getBancoById(int id);

	public Bancos saveBanco(Bancos banco);

	public  void deleteBanco(int id);
	
	public int totalBancos();

}
