package com.nt.niranjana.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.niranjana.entity.User;
import com.nt.niranjana.repo.UserRepository;
import com.nt.niranjana.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repo;

	@Override
	public Integer saveUser(User user)
	{
		String encPwd = passwordEncoder.encode(user.getUserPwd());
		user.setUserPwd(encPwd);
		return repo.save(user).getUserId();
	}
	
	@Override
	public Optional<User> getOneUserById(Integer id) 
	{
		return repo.findById(id);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<User> opt = repo.findUserByUserEmail(username);
		if(opt.isEmpty()) 
		{
			throw new UsernameNotFoundException(username + " not exist");
		}
		else
		{
			User user =  opt.get();
			//if opt is not empty then we are fetching Developer entity  User details and handover to loadUserByUsername() of UserDetails(I).
			//UserDetails(I) impl method will be take care of our entity class object and used for spring security. 
			//because spring security takes entity class from loadUserByUsername of UserDetails(I) and convert into Security and given to authentication provider
			
			//two ways we can fetch and insert developer entity class object to loadUserByUsername() i.e using for-loop or single line stream()
			
			/*List<GrantedAuthority> authorities = new ArrayList<>();
			for(String role : user.getUserRoles())
			 {
				authorities.add(new SimpleGrantedAuthority(role));
			}
			*/
			
			//by using Java 8 Stream API
			List<GrantedAuthority> authorities = user.getUserRoles()
											.stream()
											.map(role-> new SimpleGrantedAuthority(role))
											.collect(Collectors.toList());
			return new org.springframework.security.core.userdetails.User(username, user.getUserPwd(), authorities);
		}	
	}

	

	
}
