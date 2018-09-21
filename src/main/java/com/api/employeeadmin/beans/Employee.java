package com.api.employeeadmin.beans;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="employee_id", unique=true, nullable=false)
	private int employeeId;

	@Column(name="commission_pct")
	private float commissionPct;

	@Column(nullable=false, length=25)
	private String email;

	@Column(name="first_name", length=20)
	private String firstName;

	@Temporal(TemporalType.DATE)
	@Column(name="hire_date", nullable=false)
	
	@JsonFormat
	  (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date hireDate;

	@Column(name="last_name", nullable=false, length=25)
	private String lastName;

	@Column(name="phone_number", length=20)
	private String phoneNumber;

	private float salary;

	//bi-directional many-to-one association to Department
	@OneToMany(mappedBy="employee")
	@JsonIgnore
	private List<Department> departments;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="department_id")
//	@JsonIgnore
	private Department department;

	//bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name="job_id", nullable=false)
//	@JsonIgnore
	private Job job;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="manager_id")
	@JsonIgnore
	private Employee employee;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="employee")
	@JsonIgnore
	private List<Employee> employees;

	//bi-directional many-to-one association to JobHistory
	@OneToMany(mappedBy="employee")
	@JsonIgnore
	private List<JobHistory> jobHistories;



//	private Integer job_id;

	public Employee() {
	}

//	public Employee(int employeeId,String firstName, String lastName,
//			String email) {
//		this.setEmployeeId(employeeId);
//		this.setFirstName(firstName);
//		this.setLastName(lastName);
//		this.setEmail(email);		
//	}
	
	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public float getCommissionPct() {
		return this.commissionPct;
	}

	public void setCommissionPct(float commissionPct) {
		this.commissionPct = commissionPct;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public float getSalary() {
		return this.salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setEmployee(this);

		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setEmployee(null);

		return department;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setEmployee(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setEmployee(null);

		return employee;
	}

	public List<JobHistory> getJobHistories() {
		return this.jobHistories;
	}

	public void setJobHistories(List<JobHistory> jobHistories) {
		this.jobHistories = jobHistories;
	}

	public JobHistory addJobHistory(JobHistory jobHistory) {
		getJobHistories().add(jobHistory);
		jobHistory.setEmployee(this);

		return jobHistory;
	}

	public JobHistory removeJobHistory(JobHistory jobHistory) {
		getJobHistories().remove(jobHistory);
		jobHistory.setEmployee(null);

		return jobHistory;
	}

//	public void setJobId(Integer job_id) {
//		this.job_id = job_id;		
//	}
//	public Integer getJobId() {
//		return this.job_id;		
//	}
}