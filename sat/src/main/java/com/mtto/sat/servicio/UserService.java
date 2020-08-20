package com.mtto.sat.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mtto.sat.modelo.AppUser;
import com.mtto.sat.repositorio.IMAppUserRepo;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private IMAppUserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<AppUser> usuarioConsul = repo.findByUsername(username);
		AppUser us = usuarioConsul.get();
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		UserDetails userDet = new User(us.getUsername(), us.getPassword(), roles);
		
		return userDet;
	}

}
