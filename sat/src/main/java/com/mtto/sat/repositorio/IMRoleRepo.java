package com.mtto.sat.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mtto.sat.modelo.Role;

public interface IMRoleRepo extends JpaRepository<Role, Integer>{

	@Transactional	
	   void deleteById(Integer Id);
	
  	@Query("select m from Role m where m.isDisable = 0 and m.name like :name")
		List<Role> findByDescription(@Param("name") String name);

	@Query("select count(*) from Role m where m.isDisable = 0 and m.name like :name")
		   long countByCuenta(@Param("name") String name);

}