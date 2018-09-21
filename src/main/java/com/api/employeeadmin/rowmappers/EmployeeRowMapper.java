package com.api.employeeadmin.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.api.employeeadmin.beans.Employee;
import com.api.employeeadmin.beans.Job;
import com.api.employeeadmin.repositories.JobsRepository;
// TODO: borrar
public class EmployeeRowMapper<T> implements RowMapper<Employee> {

	@Autowired
	private JobsRepository jr;
	
	@Override
	public Employee mapRow(ResultSet row, int rowNum) throws SQLException {
		Employee e= new Employee();
		e.setEmployeeId(row.getInt("employee_id"));
		e.setFirstName(row.getString("first_name"));
		e.setLastName(row.getString("last_name"));
		e.setEmail(row.getString("email"));
		e.setHireDate(row.getDate("hire_date"));

		Integer job_id= row.getInt("job_id");
		Optional<Job> j= jr.findById(job_id);
		
		if (j!=null)
			e.setJob(j.get());

				
		return e;
	}

}
