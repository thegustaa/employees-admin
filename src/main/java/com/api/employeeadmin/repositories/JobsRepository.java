package com.api.employeeadmin.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.employeeadmin.beans.Job;

public interface JobsRepository extends CrudRepository<Job, Integer> {

}
