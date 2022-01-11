package com.securityjwt.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securityjwt.customException.BusinessException;
import com.securityjwt.model.Employee;
import com.securityjwt.service.EmployeeService;

@RestController
@RequestMapping("/api/test")
public class TestController {

	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee,
			@RequestHeader(value = "TraceID", defaultValue = "") String traceId) throws Exception {
		log.info("Employee Details:::::{}", employee.toString());
		log.info("traceId value=====>>>" + traceId);
		if (traceId.isEmpty()) {
			traceId = UUID.randomUUID().toString();
			log.info("traceId value=====>>>" + traceId);
		}
		if (!employeeService.getAllEmployeesId().contains(employee.getEmpId()))
			return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.CREATED);
		else
			return new ResponseEntity<BusinessException>(new BusinessException(409, "Employee already exists", traceId),
					HttpStatus.CONFLICT);
	}

	@PutMapping("/employee/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) throws Exception {
		if (employeeService.getEmployeeById(id) != null)
			return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(
					new BusinessException(404, "Employee not found", UUID.randomUUID().toString()),
					HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/employee/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) throws Exception {
		if (employeeService.getEmployeeById(id) != null) {
			employeeService.deleteEmployee(id);
			return new ResponseEntity<String>("Employee Deleted successfully", HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<String>("Employee Not Found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/employee/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) throws Exception {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else
			return new ResponseEntity<BusinessException>(
					new BusinessException(404, "Employee not found", UUID.randomUUID().toString()),
					HttpStatus.NOT_FOUND);
	}
}
