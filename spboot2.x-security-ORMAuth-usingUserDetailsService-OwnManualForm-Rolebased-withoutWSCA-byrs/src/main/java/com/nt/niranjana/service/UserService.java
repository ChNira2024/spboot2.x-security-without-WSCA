package com.nt.niranjana.service;

import java.util.Optional;

import com.nt.niranjana.entity.User;

public interface UserService 
{
	Integer saveUser(User user);
	
	Optional<User> getOneUserById(Integer id);

}
