package com.mx.Banco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Banco.dao.BancosDao;
import com.mx.Banco.dominio.Bancos;

@Service
public class BancosServiceImpl implements BancosService {

	@Autowired
	private BancosDao bancosRepository;

	@Override
	public List<Bancos> getAllBancos() {
		return bancosRepository.findAll();
	}

	@Override
	public Bancos getBancoById(int id) {
		return bancosRepository.findById(id).orElse(null);
	}

	@Override
	public Bancos saveBanco(Bancos banco) {
		return bancosRepository.save(banco);
	}

	@Override
	public void deleteBanco(int id) {
		bancosRepository.deleteById(id);
	}
	
    @Override
    public int totalBancos() {
        return bancosRepository.totalBancos();
    }

}
