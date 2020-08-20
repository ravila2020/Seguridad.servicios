package com.mtto.sat.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mtto.sat.modelo.Permission;
import com.mtto.sat.modelo.PermissionId;

public interface IMPermissionRepo extends JpaRepository<Permission,Integer>{

	@Transactional	
	   void deleteById(Integer Id);
	 
}
