package com.api.employeeadmin.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.employeeadmin.beans.Department;

public interface DepartmentsRepository extends CrudRepository<Department, Integer> {

}
