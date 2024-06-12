package com.mx.Banco.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table
@ConstructorBinding
@AllArgsConstructor
@ToString
public class Clientes {

	@Id
	@Column
	private int idCliente;
	@Column
	private String nombre;
	@Column
	private String app;
	@Column
	private String sexo;
	@Column
	private int edad;
	@Column
	private String direccion;
	@Column
	private double noContacto;
	@Column
	private String rfc;
	
	@ManyToOne(fetch = FetchType.EAGER)// MUESTRA LOS ELEMENTOS DE LA LISTA
	@JoinColumn(name = "BANCO_ID")
	private Bancos bancoId;
	
	public Clientes() {
		
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getNoContacto() {
		return noContacto;
	}

	public void setNoContacto(double noContacto) {
		this.noContacto = noContacto;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Bancos getBancoId() {
		return bancoId;
	}

	public void setBancoId(Bancos bancoId) {
		this.bancoId = bancoId;
	}

}
