package com.mtto.sat.rest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtto.sat.modelo.AppUserRole;
import com.mtto.sat.modelo.Role;
import com.mtto.sat.modelo.RoleId;
import com.mtto.sat.repositorio.IMAppUserRoleRepo;
import com.mtto.sat.repositorio.IMRolePermissionRepo;
import com.mtto.sat.repositorio.IMRoleRepo;
import com.mtto.sat.result.GenericResponse;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Rol")
public class RestRolController {

	@Autowired
	private IMRoleRepo repRole;

	@Autowired
	private IMRolePermissionRepo rolPermRel;
	
	@Autowired
	private IMAppUserRoleRepo userRolPermRel;
	
	@GetMapping
	public List<Role> listar(){
	    System.out.print(" + RestRolController listar \n");

		return repRole.findAll();
	}

	@GetMapping(path = {"/{id}"})
	public Optional<Role> buscar(@PathVariable("id") String id){
	    System.out.print(" + RestRolController buscar id: " + id + " \n");

	    RoleId Clave = new RoleId();
	    Integer idRol = Integer.valueOf(id);
		Clave.setId(idRol);

		return repRole.findById(idRol);
	}

	@PostMapping
	public Optional<Role> insertar(@RequestBody Role NuevoRol){
		Role RolEnProceso = repRole.save(NuevoRol);
	    System.out.print(" + RestRolController insertar id: " + RolEnProceso.getId() + " \n");
		
		RoleId Clave = new RoleId();

		Clave.setId(RolEnProceso.getId());
 
		return repRole.findById(RolEnProceso.getId());
	}

	@PutMapping
	public Optional<Role> modificar(@RequestBody Role ModifRol){
		repRole.save(ModifRol);
		RoleId Clave = new RoleId();

		Clave.setId(ModifRol.getId());

		return repRole.findById(ModifRol.getId());
	}

	@DeleteMapping(path = {"/{id}"})
	public GenericResponse Eliminar(@PathVariable("id") String id){
		GenericResponse Respuesta = new GenericResponse();
// Evaluar si hay usuarios con  ese Rol.
		List<AppUserRole> Resultado = userRolPermRel.findAllByRoleId(Integer.valueOf(id));
	    System.out.print(" + RestRolController Cuantos hay: " + Resultado.size() + " \n");
//  Borrar los permisos del rol 
	    if(Resultado.size() == 0) {
	    System.out.print(" + RestRolController Eliminar Rol-Permisos: " + id + " \n");
		rolPermRel.deleteByRoleId(Integer.valueOf(id));	    
//  Borrar el Rol
	    System.out.print(" + RestRolController Eliminar Rol: " + id + " \n");
	    RoleId Clave = new RoleId();
	    Integer idRol = Integer.valueOf(id);
		Clave.setId(idRol);
		repRole.deleteById(Integer.valueOf(id));
		
		Respuesta.setCodigo(10);
		Respuesta.setMensaje("Eliminación completada.");
		Respuesta.setFechaHora(ZonedDateTime.now());
		return Respuesta;
	    } else
	    {
		    System.out.print(" + RestRolController Hay roles asociados. \n");	    	
			Respuesta.setCodigo(99);
			Respuesta.setMensaje("Hay roles asociados.");
			Respuesta.setFechaHora(ZonedDateTime.now());
			return Respuesta;
	    }
	}

}
