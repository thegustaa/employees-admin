
INSERT INTO `regions`(`region_id`, `region_name`) VALUES (1,'Latinoamérica');
INSERT INTO `regions`(`region_id`, `region_name`) VALUES (2,'Europa');

INSERT INTO `countries`(`country_id`, `country_name`, `region_id`) VALUES (1,'Argentina',1);
INSERT INTO `countries`(`country_id`, `country_name`, `region_id`) VALUES (2,'España',2);
INSERT INTO `countries`(`country_id`, `country_name`, `region_id`) VALUES (3,'Uruguay',1);

INSERT INTO `locations`(`location_id`, `street_address`, `postal_code`, `city`, 
`state_province`, `country_id`) VALUES (1,"Fake street 123","123","BA","BA",1) ;

INSERT INTO `departments`(`department_id`, `department_name`, `manager_id`, `location_id`)
VALUES (1,"Sales",null,1);

INSERT INTO `jobs`(`job_id`, `job_title`, `min_salary`, `max_salary`) 
VALUES (100,"Programador",10000,20000);

INSERT INTO `employees`(`employee_id`, `first_name`, `last_name`, `email`, 
`phone_number`, `hire_date`, `job_id`, `salary`, `commission_pct`, 
`manager_id`, `department_id`) 
VALUES (100,"Valeria","Gomez","valeria@mail.com","1111-2222","2018-11-01", 
100,10000,20,null,1);

INSERT INTO `job_history`(`employee_id`, `start_date`, `end_date`, `job_id`, `department_id`) 
VALUES (100,"2010-01-01","2015-01-01",100,1) ;

INSERT INTO `jobs`(`job_id`, `job_title`, `min_salary`, `max_salary`) 
VALUES (102,"Programador",10000,20000);

INSERT INTO `employees`(`employee_id`, `first_name`, `last_name`, `email`, `phone_number`, 
`hire_date`, `job_id`, `salary`, `commission_pct`, `manager_id`, `department_id`) 
VALUES (101,"Juan","Perez","juan@mail.com","1111-2222","2018-10-01",102,10000,20,100,1);

INSERT INTO `job_history`(`employee_id`, `start_date`, `end_date`, `job_id`, `department_id`) 
VALUES (101,"2010-01-01","2016-01-01",102,1) ;

