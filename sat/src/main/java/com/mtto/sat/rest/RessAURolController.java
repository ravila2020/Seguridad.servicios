package com.mtto.sat.rest;

import java.util.List;

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
import com.mtto.sat.result.AnsUserRolList;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/UserRol")
public class RessAURolController {

	@Autowired
	private IMAppUserRoleRepo userRolPermRel;

	@GetMapping
	public AnsUserRolList listar(){
		AnsUserRolList Respuesta = new AnsUserRolList();
	    System.out.print(" + RessAURolController listar \n");
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(userRolPermRel.findAll());
		return Respuesta;
	}

	@GetMapping(path = {"/{id}"})
	public AnsUserRolList buscar(@PathVariable("id") String id){
		AnsUserRolList Respuesta = new AnsUserRolList();

	    System.out.print(" + RessAURolController buscar " + id + " \n");
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(userRolPermRel.findAllByAppUserId(Integer.valueOf(id)));
		return Respuesta;
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
	public AnsUserRolList insertar(@RequestBody AppUserRole NuevoUserRol){
		AnsUserRolList Respuesta = new AnsUserRolList();
		AppUserRole UserRolEnProceso = userRolPermRel.save(NuevoUserRol);
	
	    AppUserRoleId Clave = new AppUserRoleId();
	    
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(userRolPermRel.findAllByAppUserId(Integer.valueOf(NuevoUserRol.getAppUserId())));
		return Respuesta;
	
	}
	
	
	@DeleteMapping(path = {"/{id}"})
	public AnsUserRolList Eliminar(@PathVariable("id") String id){
		AnsUserRolList Respuesta = new AnsUserRolList();
	    System.out.print(" + RessAURolController Eliminar id: " + id + " \n");

	    AppUserRoleId Clave = new AppUserRoleId();
	    Integer idRol = Integer.valueOf(id);
		Clave.setAppUserId(idRol);

		userRolPermRel.deleteByAppUserId(idRol);
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    return Respuesta;
	}
}
