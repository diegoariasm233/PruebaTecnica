package com.internal.ServicioWebREST.Models;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.internal.ServicioWebREST.Until.MyDoubleDesirializer;


@Entity(name ="employee")
public class EmployeeModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true, nullable = false)
	private int iD;
	
	@Column(name = "nombres", length = 50, nullable = false)
	private String nombres;
	
	@Column(name = "apellidos", length = 50, nullable = false)
	private String apellidos;
	
	@Column(name = "salariobase", nullable = false)
	@JsonSerialize(using=MyDoubleDesirializer.class)
	private  double salarioBase;
	
	@Column(name = "fechaingreso", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd", lenient = OptBoolean.FALSE,shape = JsonFormat.Shape.STRING)
	private Date fechaingreso;
	
	@Column(name = "fecharetiro")
	private Date fechaRetiro;
	
	
	public EmployeeModel() {
		super();
	}
	
	public EmployeeModel(int iD, String nombres, String apellidos, double salarioBase, Date fechaIngreso,
			Date fechaRetiro) {
		super();
		this.iD= iD;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.salarioBase = salarioBase;
		this.fechaingreso = fechaIngreso;
		this.fechaRetiro = fechaRetiro;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}


	
}
