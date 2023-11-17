package com.nt.niranjana.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.niranjana.entity.UserEntityData;



public interface UserEntityRepo extends JpaRepository<UserEntityData, String> 
{

	public UserEntityData getUserByName(String username);
}
