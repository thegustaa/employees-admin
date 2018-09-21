use employees_db;

create table countries
(
country_id char (2) not null ,
country_name varchar (40) ,
region_id int (4)
) default charset=latin1 ;



create table departments
(
department_id int not null ,
department_name varchar (30) not null ,
manager_id int (4) ,
location_id int (4)
) default charset=latin1 ;


create table employees
(
employee_id int (6) not null ,
first_name varchar (20) ,
last_name varchar (25) not null ,
email varchar (25) not null ,
phone_number varchar (20) ,
hire_date date not null ,
job_id int (10) not null ,
salary float (8,2) ,
commission_pct float (4,2) ,
manager_id int (6) ,
department_id int (4)
) default charset=latin1 ;


create table jobs
(
job_id int (10) not null ,
job_title varchar (35) not null ,
min_salary int (6),
max_salary int (6)
) default charset=latin1 ;


create table job_history
(
employee_id int (6) not null ,
start_date date not null ,
end_date date not null ,
job_id int (10) not null ,
department_id int (4)
) default charset=latin1 ;


create table locations
(
location_id int (4) not null ,
street_address varchar (40) ,
postal_code varchar (12) ,
city varchar (30) not null ,
state_province varchar (25) ,
country_id char (2)
) default charset=latin1 ;


create table regions
(
region_id int (4) not null ,
region_name varchar (25)
) default charset=latin1 ;

ALTER TABLE regions
  ADD PRIMARY KEY (region_id);
ALTER TABLE locations
  ADD PRIMARY KEY (location_id);
ALTER TABLE job_history
  ADD PRIMARY KEY (employee_id, start_date);
ALTER TABLE jobs
  ADD PRIMARY KEY (job_id);
ALTER TABLE employees
  ADD PRIMARY KEY (employee_id);
ALTER TABLE departments
  ADD PRIMARY KEY (department_id);
ALTER TABLE countries
  ADD PRIMARY KEY (country_id);

  
ALTER TABLE employees DROP FOREIGN KEY IF EXISTS FK_EMPLOYEE_MANAGER;
ALTER TABLE employees DROP FOREIGN KEY IF EXISTS FK_EMPLOYEE_JOB;
ALTER TABLE employees DROP FOREIGN KEY IF EXISTS FK_EMPLOYEE_DEP;
ALTER TABLE departments DROP FOREIGN KEY IF EXISTS FK_DEPARMENT_MANAGER;
ALTER TABLE job_history DROP FOREIGN KEY IF EXISTS FK_JOBHIST_DEP;
ALTER TABLE job_history DROP FOREIGN KEY IF EXISTS FK_JOBHIST_EMPL;
ALTER TABLE job_history DROP FOREIGN KEY IF EXISTS FK_JOBHIST_JOB;
ALTER TABLE countries DROP FOREIGN KEY IF EXISTS FK_COUNTRY_REGION;
ALTER TABLE locations DROP FOREIGN KEY IF EXISTS FK_LOCATION_COUNTRY;


ALTER TABLE employees 
ADD CONSTRAINT FK_EMPLOYEE_MANAGER 
FOREIGN KEY (manager_id)
REFERENCES employees(employee_id)
ON DELETE SET NULL;



ALTER TABLE employees 
ADD CONSTRAINT FK_EMPLOYEE_JOB 
FOREIGN KEY (job_id)
REFERENCES jobs(job_id)
ON DELETE RESTRICT;
 


ALTER TABLE employees 
ADD CONSTRAINT FK_EMPLOYEE_DEP
FOREIGN KEY (department_id)
REFERENCES departments(department_id)
ON DELETE RESTRICT;



ALTER TABLE departments 
ADD CONSTRAINT FK_DEPARMENT_MANAGER
FOREIGN KEY (manager_id)
REFERENCES employees (employee_id)
ON DELETE SET NULL;




ALTER TABLE job_history 
ADD CONSTRAINT FK_JOBHIST_DEP
FOREIGN KEY (department_id)
REFERENCES departments (department_id)
ON DELETE SET NULL;

ALTER TABLE job_history 
ADD CONSTRAINT FK_JOBHIST_EMPL
FOREIGN KEY (employee_id)
REFERENCES employees (employee_id)
ON DELETE CASCADE;

ALTER TABLE job_history 
ADD CONSTRAINT FK_JOBHIST_JOB
FOREIGN KEY (job_id)
REFERENCES jobs (job_id)
ON DELETE CASCADE; -- 



ALTER TABLE countries 
ADD CONSTRAINT FK_COUNTRY_REGION
FOREIGN KEY (region_id)
REFERENCES regions (region_id)
ON DELETE CASCADE; -- 



ALTER TABLE locations 
ADD CONSTRAINT FK_LOCATION_COUNTRY
FOREIGN KEY (country_id)
REFERENCES countries (country_id)
ON DELETE CASCADE; -- 


