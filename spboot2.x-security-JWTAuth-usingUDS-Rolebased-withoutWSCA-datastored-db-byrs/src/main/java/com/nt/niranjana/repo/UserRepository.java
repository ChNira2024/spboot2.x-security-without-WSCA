package com.nt.niranjana.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.niranjana.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
}
