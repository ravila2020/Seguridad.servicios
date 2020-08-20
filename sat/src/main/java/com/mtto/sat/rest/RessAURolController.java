package com.mtto.sat.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtto.sat.modelo.AppUserRole;
import com.mtto.sat.modelo.AppUserRoleId;
import com.mtto.sat.repositorio.IMAppUserRoleRepo;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/UserRol")
public class RessAURolController {

	@Autowired
	private IMAppUserRoleRepo userRolPermRel;

	@GetMapping
	public List<AppUserRole> listar(){
	    System.out.print(" + RessAURolController listar \n");

		return userRolPermRel.findAll();
	}

	@GetMapping(path = {"/{id}"})
	public List<AppUserRole> buscar(@PathVariable("id") String id){
	    System.out.print(" + RessAURolController buscar " + id + " \n");

		return userRolPermRel.findAllByAppUserId(Integer.valueOf(id));
	}	
	
	/*
	 * @PostMapping public Optional<AppUserRole> insertar(@RequestBody AppUserRole
	 * NuevoUserRol){ AppUserRole UserRolEnProceso =
	 * userRolPermRel.save(NuevoUserRol);
	 * System.out.print(" + RessAURolController insertar id: " +
	 * UserRolEnProceso.getAppUserId() + " \n");
	 * 
	 * AppUserRoleId Clave = new AppUserRoleId();
	 * 
	 * Clave.setAppUserId(UserRolEnProceso.getAppUserId());
	 * 
	 * return userRolPermRel.findById(Clave); }
	 */
	
	@PostMapping
	public void insertar(@RequestBody AppUserRole NuevoUserRol){
		AppUserRole UserRolEnProceso = userRolPermRel.save(NuevoUserRol);
	
	    AppUserRoleId Clave = new AppUserRoleId();
	}
	
	
	@DeleteMapping(path = {"/{id}"})
	public void Eliminar(@PathVariable("id") String id){
	    System.out.print(" + RessAURolController Eliminar id: " + id + " \n");

	    AppUserRoleId Clave = new AppUserRoleId();
	    Integer idRol = Integer.valueOf(id);
		Clave.setAppUserId(idRol);

		userRolPermRel.deleteByAppUserId(idRol);
	}
}
