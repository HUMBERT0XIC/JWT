package com.mx.Banco.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.Banco.dominio.Bancos;
import com.mx.Banco.service.BancosService;

@RestController
@RequestMapping("/bancos")
public class BancosController {

    private static final Logger logger = LoggerFactory.getLogger(BancosController.class);

    @Autowired
    private BancosService bancosService;

    // Operación GET para obtener todos los bancos
    @GetMapping("/listar")
    public ResponseEntity<List<Bancos>> getAllBancos() {
        logger.info("Obteniendo todos los bancos");
        List<Bancos> bancosList = bancosService.getAllBancos();
        return new ResponseEntity<>(bancosList, HttpStatus.OK);
    }

    // Operación GET para obtener un banco por su ID
    @GetMapping("/consultar/{id}")
    public ResponseEntity<Bancos> getBancoById(@PathVariable("id") int id) {
        logger.info("Consultando banco con ID: {}", id);
        Bancos banco = bancosService.getBancoById(id);
        if (banco != null) {
            return new ResponseEntity<>(banco, HttpStatus.OK);
        } else {
            logger.warn("Banco con ID {} no encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Operación POST para guardar un nuevo banco
    @PostMapping("/nuevo")
    public ResponseEntity<Bancos> saveBanco(@RequestBody Bancos banco) {
        logger.info("Guardando nuevo banco: {}", banco);
        Bancos savedBanco = bancosService.saveBanco(banco);
        return new ResponseEntity<>(savedBanco, HttpStatus.CREATED);
    }

    // Operación DELETE para eliminar un banco por su ID
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Bancos> deleteBanco(@PathVariable("id") int id) {
        logger.info("Eliminando banco con ID: {}", id);
        bancosService.deleteBanco(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Operación GET para obtener el total de bancos
    @GetMapping("/totalBancos")
    public ResponseEntity<Integer> obtenerTotalBancos() {
        logger.info("Obteniendo el total de bancos");
        int totalBancos = bancosService.totalBancos();
        return new ResponseEntity<>(totalBancos, HttpStatus.OK);
    }
}
