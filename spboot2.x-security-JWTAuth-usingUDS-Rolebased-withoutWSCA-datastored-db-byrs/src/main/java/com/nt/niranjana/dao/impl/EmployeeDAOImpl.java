package com.nt.niranjana.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.nt.niranjana.dao.IEmployeeDao;
import com.nt.niranjana.dto.Employee;
@Service
public class EmployeeDAOImpl implements IEmployeeDao 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> fetchAllEmployeeData() 
	{
		String fetchData = "select id,name,age from employee";
		List<Employee> list = jdbcTemplate.query(fetchData, BeanPropertyRowMapper.newInstance(Employee.class));
		return list;
	}
	
	@Override
	public List<Employee> fetchEmployeeCrudFromDBUsingID(String id) {
		String FETH_DATA= "select * from employee where id=?";
		List<Employee> data  = null;
		Object[] args = {id};
		try {
			 data = jdbcTemplate.query(FETH_DATA, args,BeanPropertyRowMapper.newInstance(Employee.class));
			 System.out.println("fetched data: "+data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}

}
