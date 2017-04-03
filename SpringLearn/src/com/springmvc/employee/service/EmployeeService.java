package com.springmvc.employee.service;

import java.util.List;

import com.springmvc.entity.Employee;

public interface EmployeeService {

	Employee login(Employee employee);

	void save(Employee employee);

	Employee findById(Integer eid);

	void update(Employee employee);

	void delete(Employee employee);

	List<Employee> getAllEmployee();

}
