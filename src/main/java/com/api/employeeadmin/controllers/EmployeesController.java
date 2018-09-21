package com.api.employeeadmin.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.employeeadmin.beans.*;
import com.api.employeeadmin.repositories.DepartmentsRepository;
import com.api.employeeadmin.repositories.EmployeesRepository;
import com.api.employeeadmin.repositories.JobsRepository;
import com.api.employeeadmin.services.EmployeesService;

//import com.jayway.jsonpath.Criteria;

@RestController
public class EmployeesController {
	
	@Autowired 
	private EmployeesService employeesService;
	@Autowired
	private EmployeesRepository employeesRepository;

	@Autowired
	private JobsRepository jobsRepository;
	@Autowired
	private DepartmentsRepository departmentsRepository;

	
	@PersistenceContext
    public EntityManager entityManager;
	@GetMapping("/")
	public String home() {
		@SuppressWarnings("unchecked")
		List<Employee> list= 
		 entityManager.createNamedQuery("Employee.findAll")	            
	            .getResultList();
		return "Employees Admin Home - Total: "+list.size();
	}
	
	@PostMapping("/add")
	public @ResponseBody Employee add(@RequestBody @Valid Employee employee) {
		Job job= employee.getJob();
		jobsRepository.save(job);
		Employee emp=employeesRepository.save(employee);
		return emp;
	}
	
	@GetMapping("/delete/{id}")
	public void delete (@PathVariable("id") Integer id){
		employeesRepository.deleteById(id);
	}
	
	@GetMapping("/get/{id}")
	public @ResponseBody Employee get(@PathVariable("id") Integer id){
		try {
			Employee e= employeesRepository.findById(id).get();
			return e;
		} catch (NoSuchElementException ex){
			return new Employee();
		}
	}

	@GetMapping("/findBy")
	public @ResponseBody List<Employee> findByJobId(
				@RequestParam(value="job_id" , defaultValue = "-1") Integer jobId,
				@RequestParam(value="manager_id" , defaultValue = "-1") Integer managerId,
				@RequestParam(value="last_name" , defaultValue = "") String lastName,
				@RequestParam(value="page" , defaultValue = "-1") Integer page,
				@RequestParam(value="page_size" , defaultValue = "-1") Integer pageSize){	
		
		List<Employee> employeesResult= null;
		
		if (jobId!=-1){
			Optional<Job> job=jobsRepository.findById(jobId);
			if (job!=null){
				Optional<Employee> employee=employeesRepository.findById(job.get().getEmployees().get(0).getEmployeeId());
				if (employee!=null){
					employeesResult= new ArrayList<Employee>(){{ add(employee.get()); }};
				}
			}		
		}

		if (lastName.compareTo("") != 0){
			if (page != -1 && pageSize!=-1) {
				employeesResult = employeesRepository.findAllByLastName(lastName, 
						PageRequest.of(page, pageSize));
			} else {
				employeesResult = employeesRepository.findAllByLastName(lastName);
			}
		}
		
		if (managerId!=-1){
			Optional<Employee> manager=employeesRepository.findById(managerId);
			if (manager != null){
				// TODO: agregar paginación.
				employeesResult =manager.get().getEmployees();			
			}
		}
		
		// Ordenamiento por hireDate
		Collections.sort(employeesResult, new Comparator<Employee>() {
		    @Override
		    public int compare(Employee emp1, Employee emp2) {
		        return emp1.getHireDate().compareTo(emp2.getHireDate());
		    }
		});
		
		
	return employeesResult;
	}
	
	/**
	 * Inserta departmento de acuerdo a la logica (TODO: doc. lógica)
	 * 
	 * @param department departamento 
	 * @return si se inserta retorna estado 200 HTTP, en caso contrario 403 HTTP
	 */
	@PostMapping("/addDepartment")
	public ResponseEntity<String> addDepartment(@RequestBody @Valid Department department){
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		float locationSalaryMean=employeesService
				.locationSalaryMean(department.getLocationId());
		ResponseEntity<String> response = null;
		if (locationSalaryMean > 1000){
			if (dayOfMonth<14){				
				response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			} else {
				departmentsRepository.save(department);
				response = new ResponseEntity<String>(HttpStatus.OK);
			}
		} else {
			if (locationSalaryMean > 1500){
				if (dayOfMonth >= 15) {
					response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
				} else {
					departmentsRepository.save(department);
					response = new ResponseEntity<String>(HttpStatus.OK);
				}
			}
		}
		
		return response;
	}
	
}

