package com.securityjwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securityjwt.model.Employee;
import com.securityjwt.repositary.EmployeeRepositary;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepositary employeeRepositary;

	@Override
	public List<Integer> getAllEmployeesId() throws Exception {
		return employeeRepositary.getAllEmployeesId();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepositary.save(employee);
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return employeeRepositary.findById(id).orElse(null);
	}

	@Override
	public Employee updateEmployee(Employee employee, Integer id) {
		return employeeRepositary.save(employee);
	}

	@Override
	public void deleteEmployee(Integer id) {
		employeeRepositary.deleteById(id);		
	}

}
