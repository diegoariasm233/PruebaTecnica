package com.internal.ServicioWebREST.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internal.ServicioWebREST.Models.EmployeeModel;
import com.internal.ServicioWebREST.Models.MessageReturn;
import com.internal.ServicioWebREST.Models.PayRequest;
import com.internal.ServicioWebREST.Models.PayResponse;
import com.internal.ServicioWebREST.Service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService Employeeservice;
	
	@GetMapping()
    public @ResponseBody ArrayList<EmployeeModel> getallEmploye(){
        return Employeeservice.getallEmploye();
    }
	@GetMapping( path = "/{id}")
    public @ResponseBody ResponseEntity<MessageReturn> getEmployebyID(@PathVariable("id") int id) {
        MessageReturn ret = this.Employeeservice.getEmployebyID(id);
		return ResponseEntity.status(ret.getStatus()).body(ret);
    }	
	@GetMapping(value="/pay")
    public @ResponseBody ResponseEntity<PayResponse> payMethod(@RequestBody PayRequest req){
		PayResponse ret = this.Employeeservice.calculatetoPay(req);
		return ResponseEntity.status(ret.getStatus()).body(ret);
    }
	@PostMapping()
    public @ResponseBody ResponseEntity<MessageReturn> SaveEmployee(@RequestBody EmployeeModel empleado)  {
		MessageReturn ret = this.Employeeservice.saveEmployee(empleado);
		return ResponseEntity.status(ret.getStatus()).body(ret);
    }
	@PutMapping()
	public @ResponseBody ResponseEntity<MessageReturn> UpdateEmployee(@RequestBody EmployeeModel empleado) {
		MessageReturn ret =  this.Employeeservice.updateEmployee(empleado);
		return ResponseEntity.status(ret.getStatus()).body(ret);
	 }
	
	@DeleteMapping( path = "/{id}")
	public @ResponseBody ResponseEntity<MessageReturn> deleteEmployee(@PathVariable("id") int id) {
		MessageReturn ret =  this.Employeeservice.deleteEmployee(id);
		return ResponseEntity.status(ret.getStatus()).body(ret);
	 }


	
}
