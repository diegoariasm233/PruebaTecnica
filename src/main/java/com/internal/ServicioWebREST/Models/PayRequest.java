package com.internal.ServicioWebREST.Models;

public class PayRequest {
	private int idEmployee; 
	private int Month;
	private int Year;
	
	
	public PayRequest() {
		super();
	}
	
	public PayRequest(int idEmployee, int month, int year) {
		super();
		this.idEmployee = idEmployee;
		Month = month;
		Year = year;
	}
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	
	
}
