package com.Global.Groupware.ExceptionHandling;

public class EmployeeNotFoundException extends RuntimeException {
	public EmployeeNotFoundException(String message) {
        super(message);
    }


}
