package com.nt.niranjana.dao;

import java.util.List;

import com.nt.niranjana.dto.Employee;

public interface IEmployeeDao 
{
	public List<Employee> fetchAllEmployeeData();
	
	public List<Employee> fetchEmployeeCrudFromDBUsingID(String id);

}
