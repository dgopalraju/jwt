package com.securityjwt.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.securityjwt.model.Employee;

@Repository
public interface EmployeeRepositary extends JpaRepository<Employee, Integer> {

	@Query("select e.id from Employee e")
	List<Integer> getAllEmployeesId();

}
