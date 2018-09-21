package com.api.employeeadmin.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.api.employeeadmin.beans.Employee;
/**
 * Repositorio para las operaciones CRUD.
 * @author Gustavo M. Borello
 *
 */
public interface EmployeesRepository extends CrudRepository<Employee, Integer>, JpaRepository<Employee, Integer> {

	List<Employee> findAllByLastName(String lastName);
	List<Employee> findAllByLastName(String lastName, Pageable pagination);

}