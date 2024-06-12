package com.mx.Banco.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@ConstructorBinding
@ToString
public class Bancos {

	@Id
	@Column
	private int idBanco;
	@Column
	private String nombre;
	@Column
	private String sucursal;
	@Column
	private String ubicacion;
	@Column
	private double telefono;
	@Column
	private String tipo;
	@Column
	private int noEmpleados;
	
	@OneToMany(mappedBy = "bancoId", cascade = CascadeType.ALL)
	private List<Clientes> lista = new ArrayList<Clientes>();
	
	public Bancos() {
		
	}

	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public double getTelefono() {
		return telefono;
	}

	public void setTelefono(double telefono) {
		this.telefono = telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNoEmpleados() {
		return noEmpleados;
	}

	public void setNoEmpleados(int noEmpleados) {
		this.noEmpleados = noEmpleados;
	}

}
