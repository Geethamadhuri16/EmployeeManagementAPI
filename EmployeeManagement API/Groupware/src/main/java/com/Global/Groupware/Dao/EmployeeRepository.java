package com.Global.Groupware.Dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.Global.Groupware.model.Employee;
@Component
@ComponentScan("com.Global.Groupware")
public interface EmployeeRepository extends CouchbaseRepository<Employee, String>  {
	
	

}
