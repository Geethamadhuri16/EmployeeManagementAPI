package com.Global.Groupware;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
@ComponentScan("com.Global.Groupware")
public interface EmployeeRepository extends CouchbaseRepository<Employee, String>  {
	
	

}
