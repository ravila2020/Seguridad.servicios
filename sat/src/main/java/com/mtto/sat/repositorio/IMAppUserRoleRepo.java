package com.mtto.sat.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mtto.sat.modelo.AppUserRole;
import com.mtto.sat.modelo.AppUserRoleId;

public interface IMAppUserRoleRepo extends JpaRepository<AppUserRole, AppUserRoleId>{

	
	@Transactional	
	List<AppUserRole> findAllByAppUserId(Integer RolId);

//	@Transactional	
//	Optional<AppUserRole> findAllByAppUserId(Integer RolId);	
	
	@Transactional	
	List<AppUserRole> findAllByRoleId(Integer RolId);
	
	@Transactional	
	   void deleteByAppUserId(Integer AppUserId);
	
}
