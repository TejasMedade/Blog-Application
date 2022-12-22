package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByRoleName(String roleName);
	
}
