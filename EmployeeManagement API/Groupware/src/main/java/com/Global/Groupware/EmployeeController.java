package com.Global.Groupware;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Global.Groupware.model.Employee;
import com.Global.Groupware.service.EmployeeService;

   @CrossOrigin(origins="http://localhost:3000")
   @RestController
	public class EmployeeController {
	   @Autowired
	   private EmployeeService service;
	

	    @PostMapping("/employees")
	   
	    public String addEmployee(@RequestBody Employee employee) {
	    	return service.addEmployee(employee);

	    }

//	    @GetMapping("/getemployees")
//	    public List<Employee> getAllEmployees() {
//	        return employeeRepository.findAll();
//	    }
	    
	    @GetMapping("/getemployees")
	    public List<Employee> getAllEmployees(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "20") int pageSize,
	            @RequestParam(defaultValue = "employeeName") String sortBy
	    ) {
	    	return service.getAllEmployees(page, pageSize, sortBy);
	        
	    }

//	    @DeleteMapping("/employees/{id}")
//	    public void deleteEmployee(@PathVariable String id) {
//	        employeeRepository.deleteById(id);
//	    }
	    
	    @GetMapping("/employee/{id}")
	    public ResponseEntity<Employee> getById(@PathVariable String id){
	    	return service.getById(id);
	    	
	    }
	    
	    @DeleteMapping("/employees/{id}")
	    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
	        // Check if the employee exists in the database
	    	return service.deleteEmployee(id);
	        
	    }
	       
	    

	    @PutMapping("/employees/{id}")
	    public ResponseEntity<String> updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee) {
	    	 
	        return service.updateEmployee(id, updatedEmployee);
	    }
	    
	    @GetMapping("/employees/{employeeId}/{level}")
	    public ResponseEntity<Employee> getNthLevelManager(@PathVariable String employeeId, @PathVariable int level) {
	        return service.getNthLevelManager(employeeId, level);
	    }

	   
	    
	    
	}



