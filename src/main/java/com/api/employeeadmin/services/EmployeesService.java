package com.api.employeeadmin.services;


import com.api.employeeadmin.beans.Employee;

public interface EmployeesService {
	public Employee save(Employee emp);
	public Employee findById(Integer id);
	public boolean delete(Employee emp);
	public boolean update(Employee emp);
	public void deleteById(int id);
	public float locationSalaryMean(int location_id);
}
