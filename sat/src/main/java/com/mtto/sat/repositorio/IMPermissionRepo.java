package com.mtto.sat.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mtto.sat.modelo.AppUser;
import com.mtto.sat.modelo.Permission;

public interface IMPermissionRepo extends JpaRepository<Permission,Integer>{

	@Transactional	
	   void deleteById(Integer Id);
	 
	@Transactional	
		Permission save(Permission x);
}
