package com.internal.ServicioWebREST.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.internal.ServicioWebREST.Interface.IEmployeeInterface;
import com.internal.ServicioWebREST.Models.EmployeeModel;
import com.internal.ServicioWebREST.Models.MessageReturn;
import com.internal.ServicioWebREST.Models.PayRequest;
import com.internal.ServicioWebREST.Models.PayResponse;
@Service
public class EmployeeService {
	 	@Autowired
	 	IEmployeeInterface employeinterface;
	
	    public MessageReturn saveEmployee(EmployeeModel employee){
	    	MessageReturn mess = new MessageReturn();
	    	try {
	    		if(employee.getSalarioBase() == 0) {
	    			mess.setMessage("El Salario no puede ser 0");
		    		mess.setStatus(HttpStatus.BAD_REQUEST);
	    		}else{
	    			if(employeinterface.existsById(employee.getiD())){
			    		mess.setMessage("Este Usuario ya existe si quieres actualizarlo debes enviar PUT method");
			    		mess.setStatus(HttpStatus.BAD_REQUEST);
			    	}else{
			    		if(employee.getFechaRetiro() != null) {
		    				if(employee.getFechaingreso().compareTo(employee.getFechaRetiro()) > 0) {
			    				mess.setMessage("Rangeo de Fechas Incorrecto");
					    		mess.setStatus(HttpStatus.BAD_REQUEST);
			    			}else {
			    				mess.setEmployee(employeinterface.save(employee));
					    		mess.setMessage("Exitoso");
					    		mess.setStatus(HttpStatus.OK);
			    			}
		    			}else {
		    				mess.setEmployee(employeinterface.save(employee));
				    		mess.setMessage("Exitoso");
				    		mess.setStatus(HttpStatus.OK);
		    			}
			    	}
	    		}
	    		
	    	}catch(Exception e) {
	    		mess.setMessage(e.getLocalizedMessage());
	    		mess.setStatus(HttpStatus.BAD_REQUEST);
	    	}
	    	return mess;
	    }
	    public MessageReturn updateEmployee(EmployeeModel employee){
	    	MessageReturn mess = new MessageReturn();
	    	try {
	    		if(employeinterface.existsById(employee.getiD())){
	    			if(employee.getFechaRetiro() != null) {
	    				if(employee.getFechaingreso().compareTo(employee.getFechaRetiro()) > 0) {
		    				mess.setMessage("Rangeo de Fechas Incorrecto");
				    		mess.setStatus(HttpStatus.BAD_REQUEST);
		    			}else {
		    				mess.setEmployee(employeinterface.save(employee));
				    		mess.setMessage("Exitoso");
				    		mess.setStatus(HttpStatus.OK);
		    			}
	    			}else {
	    				mess.setEmployee(employeinterface.save(employee));
			    		mess.setMessage("Exitoso");
			    		mess.setStatus(HttpStatus.OK);
	    			}
		    	}else {
		    		mess.setMessage("Este Usuario no existe");
		    		mess.setStatus(HttpStatus.BAD_REQUEST);
		    	}
	    	}catch(Exception ex) {
	    		mess.setMessage(ex.getLocalizedMessage());
	    		mess.setStatus(HttpStatus.BAD_REQUEST);
	    	}
	    	
	    	return mess;
	    }
	    public PayResponse calculatetoPay(PayRequest req) {
	    	PayResponse payr = new PayResponse();
	    	String date = req.getYear() +"/"+ req.getMonth() + "/01";
	    	boolean Isvalid = this.validarFecha(date);
	    	int Diasmes = 30;
	    	try {
	    		if(Isvalid) {
	    			if(employeinterface.existsById(req.getIdEmployee())){
		    			EmployeeModel employee =  employeinterface.findById(req.getIdEmployee()).get();
		    			Date Inicio = employee.getFechaingreso();
		    			LocalDate localDate = Inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    			int monthini = localDate.getMonthValue();
		    			int Yearini = localDate.getYear();
		    			int DayIni = localDate.getDayOfMonth();
		    			Date fin = employee.getFechaRetiro();
		    			
		    			double ValorDiario =employee.getSalarioBase()/ Diasmes;
		    			if(req.getMonth() == 0 || req.getYear() == 0) {
		    				payr.setSalarytopay(0);
	    		    		payr.setStatus(HttpStatus.BAD_REQUEST);
		    			}else {
		    				if(fin == null) {
		    					if((monthini > req.getMonth() && Yearini > req.getYear()) || (monthini > req.getMonth() && Yearini == req.getYear()) ) {
			    					payr.setSalarytopay(0);
			    		    		payr.setStatus(HttpStatus.OK);
			    				}else if(monthini == req.getMonth() && Yearini == req.getYear()) {
			    					int APagar = Diasmes - DayIni;
		    						payr.setSalarytopay(APagar * ValorDiario);
		    						payr.setStatus(HttpStatus.OK);
			    				}else if(req.getYear() < Yearini) {
		    						payr.setSalarytopay(0);
			    		    		payr.setStatus(HttpStatus.OK);
		    					}else{
			    					payr.setSalarytopay(employee.getSalarioBase());
			    		    		payr.setStatus(HttpStatus.OK);
			    				}
			    				
			    			}else {
			    				LocalDate localDate1 = fin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				    			int monthfin = localDate1.getMonthValue();
				    			int Yearfin = localDate1.getYear();
				    			int Dayfin = localDate1.getDayOfMonth();
				    			
				    			if(monthini == req.getMonth() && Yearini == req.getYear()) {
			    					if(monthini == monthfin && Yearini == Yearfin) {
			    						int APagar =  Dayfin - DayIni;
			    						payr.setSalarytopay(APagar * ValorDiario);
			    						payr.setStatus(HttpStatus.OK);
			    					}else {
			    						int APagar = Diasmes - DayIni;
			    						payr.setSalarytopay(APagar * ValorDiario);
			    						payr.setStatus(HttpStatus.OK);
			    					}
			    				}else {
			    					if(monthfin == req.getMonth() && Yearfin == req.getYear()) {
			    						payr.setSalarytopay(Dayfin * ValorDiario);
			    						payr.setStatus(HttpStatus.OK);
			    					}else if(Yearfin < req.getYear()) {
			    						payr.setSalarytopay(0);
				    		    		payr.setStatus(HttpStatus.OK);
			    					}else if(Yearfin == req.getYear()){
			    						if(Yearfin > Yearini) {
			    							if(req.getMonth() < monthfin) {
						    		    		payr.setSalarytopay(employee.getSalarioBase());
						    		    		payr.setStatus(HttpStatus.OK);
				    						}else {
				    							payr.setSalarytopay(0);
						    		    		payr.setStatus(HttpStatus.OK);
				    						}
			    						}else {
			    							if(req.getMonth() > monthini && req.getMonth() < monthfin ) {
						    		    		payr.setSalarytopay(employee.getSalarioBase());
						    		    		payr.setStatus(HttpStatus.OK);
				    						}else {
				    							payr.setSalarytopay(0);
						    		    		payr.setStatus(HttpStatus.OK);
				    						}
			    						}
			    						
			    						
			    					}else if(Yearfin > req.getYear()){
			    						if(req.getMonth() < monthini && Yearini == req.getYear()) {
			    							payr.setSalarytopay(0);
					    		    		payr.setStatus(HttpStatus.OK);
			    						}else if(Yearini > req.getYear()){
			    							payr.setSalarytopay(0);
					    		    		payr.setStatus(HttpStatus.OK);
			    						}else {
			    							payr.setSalarytopay(employee.getSalarioBase());
					    		    		payr.setStatus(HttpStatus.OK);
			    						}
			    						
			    				
			    					}
			    				}
			    			}
		    				payr.setMessage("Exitoso");
		    			}
		    			if(payr.getStatus() == null) {
		    				payr.setStatus(HttpStatus.OK);
		    			}
		    			
		    			
		    		}else {
			    		payr.setMessage("Este Empleado no existe");
			    		payr.setStatus(HttpStatus.BAD_REQUEST);
			    	}
	    		}else {
	    			payr.setMessage(" Fecha Incorrecta");
		    		payr.setStatus(HttpStatus.BAD_REQUEST);
	    		}
	    		
	    	}catch(Exception ex) {
	    		payr.setMessage("Error Servidor");
	    		payr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    	return payr;
	    }
	    public MessageReturn getEmployebyID(int id){
	    	MessageReturn mess = new MessageReturn();
	    	if(employeinterface.existsById(id)){
	    		mess.setEmployee(employeinterface.findById(id).get());
	    		mess.setStatus(HttpStatus.OK);
	    		mess.setMessage("Exitoso");
	        }else {
	        	mess.setStatus(HttpStatus.NOT_FOUND);
	    		mess.setMessage("No encontrado");
	        }
	    	return mess;
	    }
	    public MessageReturn deleteEmployee(int id){
	    	MessageReturn mess = new MessageReturn();
	    	try{
	    		if(employeinterface.existsById(id)){
		    		employeinterface.deleteById(id);
		    		mess.setMessage("Exitoso");
		    		mess.setStatus(HttpStatus.OK);
		    	}else {
		    		mess.setMessage("No encontrado");
		    		mess.setStatus(HttpStatus.BAD_REQUEST);
		    	}
	    	}catch(Exception ex) {
	    		mess.setMessage("Error Servidor");
	    		mess.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	        return mess;
	    }
	    public ArrayList<EmployeeModel> getallEmploye(){
	        return (ArrayList<EmployeeModel>) employeinterface.findAll();
	    }
	    public boolean validarFecha(String fecha) {
	        boolean valid = false;
	        try {
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
	            formatoFecha.setLenient(false);
	            formatoFecha.parse(fecha);
	            valid = true;
	        } catch (ParseException e) {
	        	valid = false;
	        }
	        return valid;
	    }
}
