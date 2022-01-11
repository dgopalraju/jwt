package com.securityjwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_details")
public class Employee {
	@Id
	@SequenceGenerator(name = "employees_sequ", sequenceName = "employees_sequ", allocationSize = 1)
	@GeneratedValue(generator = "employees_sequ", strategy = GenerationType.SEQUENCE)
	private int empId;
	private String empName;
	private String empEmail;
	private double salary;
	private long empMobileNumber;

}
