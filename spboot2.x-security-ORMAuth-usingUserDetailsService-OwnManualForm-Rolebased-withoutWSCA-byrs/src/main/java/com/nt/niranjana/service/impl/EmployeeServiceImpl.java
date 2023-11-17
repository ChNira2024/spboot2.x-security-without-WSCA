package com.nt.niranjana.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.niranjana.dao.IEmployeeDao;
import com.nt.niranjana.dto.Employee;
import com.nt.niranjana.service.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService 
{
	@Autowired
	private IEmployeeDao empDao;
	
	@Override
	public List<Employee> fetchAllEmployeeData() 
	{
		List<Employee> data = empDao.fetchAllEmployeeData();
		return data;
	}
	
	@Override
	public List<Employee> fetchEmployeesDataByUsingID(String id) 
	{
		List<Employee> list = empDao.fetchEmployeeCrudFromDBUsingID(id);
		return list;
	}
}
