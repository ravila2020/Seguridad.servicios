package com.mtto.sat.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mtto.sat.modelo.AppUser;

public interface IMAppUserRepo extends JpaRepository<AppUser, Integer>{

	@Transactional	
	   void deleteByUsername(String username);

	@Transactional	
		Optional<AppUser> findByUsername(String username);

	@Transactional	
	   AppUser save(AppUser x);
	
	@Transactional	
		List<AppUser> findByEnabled(boolean enabled);
	
	@Transactional	
		long countByEnabled(boolean enabled);
	
//	@Transactional
//		Optional<Integer> findDistinctByName();
}
