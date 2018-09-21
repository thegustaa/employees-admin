package com.api.employeeadmin;
import static org.junit.Assert.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.employeeadmin.beans.Department;
import com.api.employeeadmin.beans.Employee;
import com.api.employeeadmin.beans.Job;
import com.api.employeeadmin.beans.Region;
import com.api.employeeadmin.repositories.EmployeesRepository;
import com.api.employeeadmin.repositories.JobsRepository;
import com.api.employeeadmin.repositories.RegionsRepository;
import com.api.employeeadmin.rowmappers.EmployeeRowMapper;
import com.api.employeeadmin.services.EmployeesService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeAdminApplicationTests {

	@Autowired
	private EmployeesRepository er;
	
	@Autowired
	private EmployeesService employeesService;
	
	@Autowired
	private JobsRepository jr;
	
	@PersistenceContext
    public EntityManager entityManager;
	
	@Autowired
	private RegionsRepository rr;
	
	@Test
	public void contextLoads() {
	}
	
	// TODO: carga en setUp()

	@Test
	public void saveEmployee(){
				
		Employee emp = new Employee();
		emp.setFirstName("Carolina");
		emp.setLastName("Perez");
		emp.setEmail("caro@mail.com");
		emp.setHireDate(new Date(0));		
		Job j= new Job();
		j.setJobTitle("Programadora");
		j.setMinSalary(10000);
		j.setMaxSalary(15000);
		jr.save(j);
		emp.setJob(j);
		
		Employee savedEmp=er.save(emp);
		assertTrue(savedEmp != null && savedEmp.getEmployeeId()>0);
	

	}
	
	@Test
	public void testGetEmployee(){
		Optional<Employee> e= er.findById(7);
		assertTrue(e != null);
	}
	
	@Test
	public void testFindAllEmployees(){
		Iterable<Employee> it = er.findAll();
		Iterator<Employee> iterator = it.iterator();
		if (iterator.hasNext()){
			Employee e=iterator.next();
			assertTrue(true);
		}
	}
	

    @Autowired
    JdbcTemplate jdbcTemplate;
	@Test
	public void a(){
		Employee e= new Employee();
		
		  List<Employee> el= jdbcTemplate.query(
	                "SELECT * FROM employees WHERE first_name = ?", 
	                new Object[] { "Carolina" },
	                              
	                new EmployeeRowMapper<Employee>());		  
		assertTrue(el.size()>0);
	}
	
	@Test
	public void testFindAllByLastName(){
		String lastName= "Perez";
		List<Employee> list = er.findAllByLastName(lastName);
		assertTrue(list.size()>0);
	}
	
	@Test 
	public void testLocationSalaryMean(){
		int location_id= 1;
		float mean=employeesService.locationSalaryMean(location_id);
		assertTrue(mean==20000);
	}
	
}
