package com.internal.ServicioWebREST.Interface;
import org.springframework.data.jpa.repository.JpaRepository;

import com.internal.ServicioWebREST.Models.EmployeeModel;

public interface IEmployeeInterface extends  JpaRepository<EmployeeModel,Integer> {

}
