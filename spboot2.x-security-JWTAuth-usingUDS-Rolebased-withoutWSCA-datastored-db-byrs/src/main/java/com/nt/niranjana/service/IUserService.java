package com.nt.niranjana.service;

import com.nt.niranjana.entity.User;

public interface IUserService {

	public Integer saveUser(User user);
	public User findByUsername(String username);
}
