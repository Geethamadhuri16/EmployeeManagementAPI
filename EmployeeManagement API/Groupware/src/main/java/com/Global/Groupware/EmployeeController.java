package com.Global.Groupware;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;




   @RestController
	public class EmployeeController {
	

	@Autowired
	public EmployeeRepository employeeRepository;

  

	    @PostMapping("/employees")
	   
	    public String addEmployee(@RequestBody Employee employee) {

	        employeeRepository.save(employee);
	        return employee.gete_ID();
	    }

//	    @GetMapping("/getemployees")
//	    public List<Employee> getAllEmployees() {
//	        return employeeRepository.findAll();
//	    }
	    
	    @GetMapping("/getemployees")
	    public List<Employee> getAllEmployees(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int pageSize,
	            @RequestParam(defaultValue = "employeeName") String sortBy
	    ) {
	        Sort sort = Sort.by(sortBy);
	        Pageable pageable = PageRequest.of(page, pageSize, sort);

	       Page<Employee> employeePage = employeeRepository.findAll(pageable);
	        return employeePage.getContent();
	    }

//	    @DeleteMapping("/employees/{id}")
//	    public void deleteEmployee(@PathVariable String id) {
//	        employeeRepository.deleteById(id);
//	    }
	    
	    
	    @DeleteMapping("/employees/{id}")
	    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
	        // Check if the employee exists in the database
	        if (!employeeRepository.existsById(id)) {
	            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
	        }

	        // Delete the employee
	        employeeRepository.deleteById(id);
	        return ResponseEntity.ok("Employee with ID: " + id + " deleted successfully");
	    }
	       
	    

	    @PutMapping("/employees/{id}")
	    public ResponseEntity<String> updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee) {
	    	 if (!employeeRepository.existsById(id)) {
		            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
		        }
	        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
	        
	        if (optionalEmployee.isPresent()) {
	        	 Employee employee = optionalEmployee.get();
	             employee.setemployeeName(updatedEmployee.getemployeeName());
	             employee.setphoneNumber(updatedEmployee.getphoneNumber());
	             employee.setemail(updatedEmployee.getemail());
	             employee.setreportsTo(updatedEmployee.getreportsTo());
	             employee.setemp_ProfileImg(updatedEmployee.getemp_ProfileImg());
	             employeeRepository.save(employee);
	        }
	        return ResponseEntity.ok("Employee with ID: " + id + " updated successfully");
	        
	    }
	    
	    @GetMapping("/employees/{employeeId}/{level}")
	    public ResponseEntity<Employee> getNthLevelManager(@PathVariable String employeeId, @PathVariable int level) {
	        Employee employee = employeeRepository.findById(employeeId).orElse(null);

	        if (employee == null) {
	            return ResponseEntity.notFound().build();
	        }

	        Employee reportsTo = getNthLevelReportsToRecursive(employee, level);

	        if (reportsTo == null) {
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok(reportsTo);
	    }

	    private Employee getNthLevelReportsToRecursive(Employee employee, int level) {
	        if (level <= 0) {
	            return employee;
	        }

	        Employee reportsTo = employeeRepository.findById(employee.getreportsTo()).orElse(null);

	        if (reportsTo == null) {
	            return null;
	        }

	        return getNthLevelReportsToRecursive(reportsTo, level - 1);
	    }

	    
	    
	}



