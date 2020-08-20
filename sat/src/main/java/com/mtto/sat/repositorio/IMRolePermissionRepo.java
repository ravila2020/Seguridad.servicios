package com.mtto.sat.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mtto.sat.modelo.RolePermission;
import com.mtto.sat.modelo.RolePermissionId;

public interface IMRolePermissionRepo extends JpaRepository<RolePermission, RolePermissionId>{

	
//	@Transactional	
//	public Optional<RolePermission> findByroleId(Integer Id);
	
	
	@Transactional	
	List<RolePermission> findAllByRoleId(Integer RolId);
	
	@Transactional	
	List<RolePermission> deleteByRoleId(Integer RolId);
	
}
