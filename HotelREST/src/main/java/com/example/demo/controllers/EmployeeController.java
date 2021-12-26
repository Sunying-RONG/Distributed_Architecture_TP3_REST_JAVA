package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.HotelNotFoundException;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;
	private static final String uri = "employeeservice/api";
	
	@GetMapping(uri+"/employees")
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}
	
	@GetMapping(uri+"/employees/count")
	public String count() {
		return String.format("{\"%s\":%d}", "count", repository.count());
	}
	
	@GetMapping(uri+"/employees/{id}")
	public Employee getEmployeeById(@PathVariable long id) throws HotelNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new HotelNotFoundException(
						"Error: could not find employee with ID "+id));
	}
	
	@ResponseStatus(HttpStatus.CREATED) //return code 201 if created
	@PostMapping(uri+"/employees") //create par default
	public Employee createEmployee(@RequestBody Employee employee) {
		return repository.save(employee);
	}
	
	@PutMapping(uri+"/employees/{id}") //update par default, if not existe, create
	public Employee updateEmployee(@RequestBody Employee newEmployee, 
			@PathVariable long id) {
		return repository.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					employee.setEmail(newEmployee.getEmail());
					repository.save(employee);
					return employee;
				})
				.orElseGet(() -> {
					newEmployee.setId(id);
					repository.save(newEmployee);
					return newEmployee;
				});
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT) //204 No Content.
	@DeleteMapping(uri+"/employees/{id}")
	public void deleteEmployee(@PathVariable long id) throws HotelNotFoundException {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new HotelNotFoundException(
						"Error: could not find employee with ID "+id));
		repository.delete(employee);
	}
	
}
