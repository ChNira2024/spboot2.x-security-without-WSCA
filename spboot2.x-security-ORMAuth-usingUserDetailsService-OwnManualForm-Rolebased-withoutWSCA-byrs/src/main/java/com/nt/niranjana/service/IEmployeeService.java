package com.nt.niranjana.service;

import java.util.List;

import com.nt.niranjana.dto.Employee;
public interface IEmployeeService 
{
	public List<Employee>fetchAllEmployeeData();
	public List<Employee> fetchEmployeesDataByUsingID(String id);

}
