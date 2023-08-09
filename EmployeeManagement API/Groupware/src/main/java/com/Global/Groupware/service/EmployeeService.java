package com.Global.Groupware.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import com.Global.Groupware.Dao.EmployeeRepository;
import com.Global.Groupware.ExceptionHandling.EmployeeNotFoundException;
import com.Global.Groupware.model.Employee;

@Service
public class EmployeeService {


	@Autowired
	public EmployeeRepository employeeRepository;

  

	   
	   
	    public String addEmployee( Employee employee) {

	        employeeRepository.save(employee);
	        return employee.gete_ID();
	    }

//	    @GetMapping("/getemployees")
//	    public List<Employee> getAllEmployees() {
//	        return employeeRepository.findAll();
//	    }
	    
	 
	    public List<Employee> getAllEmployees(
	             int page,
	            int pageSize,
	             String sortBy
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
	    
	   
	    public ResponseEntity<Employee> getById( String id){
	    	Optional<Employee> e=employeeRepository.findById(id);
	    	if(e.isPresent()) {
	    		Employee emp=e.get();
	    		return new ResponseEntity<>(emp,HttpStatus.OK);
	    	}
	    	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	    }
	    
	    
	    public ResponseEntity<String> deleteEmployee( String id) {
	        // Check if the employee exists in the database
	        if (!employeeRepository.existsById(id)) {
	            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
	        }

	        // Delete the employee
	        employeeRepository.deleteById(id);
	        return ResponseEntity.ok("Employee with ID: " + id + " deleted successfully");
	    }
	       
	    

	   
	    public ResponseEntity<String> updateEmployee( String id,  Employee updatedEmployee) {
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
	    
	    
	    public ResponseEntity<Employee> getNthLevelManager( String employeeId,  int level) {
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
