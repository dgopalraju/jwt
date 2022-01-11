package com.securityjwt.service;

import java.util.List;

import com.securityjwt.model.Employee;

public interface EmployeeService {

	List<Integer> getAllEmployeesId() throws Exception;

	Employee addEmployee(Employee employee);

	Employee getEmployeeById(Integer id);

	Employee updateEmployee(Employee employee, Integer id);

	void deleteEmployee(Integer id);


}
