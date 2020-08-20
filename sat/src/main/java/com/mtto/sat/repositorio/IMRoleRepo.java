package com.mtto.sat.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mtto.sat.modelo.Role;
import com.mtto.sat.modelo.RoleId;

public interface IMRoleRepo extends JpaRepository<Role, Integer>{

	@Transactional	
	   void deleteById(Integer Id);
}
