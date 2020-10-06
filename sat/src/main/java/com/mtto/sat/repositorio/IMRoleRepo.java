package com.mtto.sat.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mtto.sat.modelo.Role;

public interface IMRoleRepo extends JpaRepository<Role, Integer>{

	@Transactional	
	   void deleteById(Integer Id);
	
	@Transactional
	Optional<String> findDistinctByName();
}
