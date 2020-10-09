package com.mtto.sat.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
//	@Query("select m from AppUser m where m.username like \"%:marca%\"")
//		List<AppUser> findByLike(@Param("marca") String marca);
	
	@Query("select m from AppUser m where m.enabled = 1 and m.username like :username")
	List<AppUser> findByFirstname(@Param("username") String username);
	
	@Query("select count(*) from AppUser m where m.enabled = 1 and m.username like :username")
	   long countByFirstname(@Param("username") String username);
}
