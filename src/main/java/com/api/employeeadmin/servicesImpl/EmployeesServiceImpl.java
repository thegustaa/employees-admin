package com.api.employeeadmin.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.api.employeeadmin.beans.Employee;
import com.api.employeeadmin.repositories.EmployeesRepository;
import com.api.employeeadmin.services.EmployeesService;
@Service
public class EmployeesServiceImpl implements  EmployeesService {
	@Autowired
	EmployeesRepository employeesRepo;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Employee save(Employee emp) {
		Employee emp_=employeesRepo.save(emp);
		return emp_;
	}

	@Override
	public Employee findById(Integer id) {
//		Employee e= employeesRepo.findById(id);
		return null;
	}

	@Override
	public boolean delete(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteById(int id) {
		employeesRepo.deleteById(id);
		
	}
	
	/**
	 * Calcula el promedio de sueldo de los empleados de los 
	 * departamentos que estan localizados en "location_id"
	 * @param location_id id de la localidad de los departamentos a tener en cuenta.
	 * @return promedio según descripción.
	 */
	public float locationSalaryMean(int locationId){
		// HIPOTESIS: el empleado está cobrando "max_salary" actualmente y es el 
		// valor a tener en cuenta.
		String sql="SELECT (sum(j.max_salary))/2 as location_salary_mean "
				+ "FROM jobs as j "
				+ "JOIN employees as emp on emp.job_id = j.job_id "
				+ "JOIN departments as dep on emp.department_id = dep.department_id "
				+ "JOIN locations as loc on dep.location_id = loc.location_id "
				+ "group by loc.location_id "
				+ "having location_id = ?";
		float result= -1;
		List<Float> el= jdbcTemplate.query(
					sql, 
	                new Object[] { locationId },
	                (rs, rowNum) -> 
	                	new Float(rs.getFloat("location_salary_mean")));
		if (el.size()>0){
			result= el.get(0);
		}
		return result;
	}
	
	
	
}
