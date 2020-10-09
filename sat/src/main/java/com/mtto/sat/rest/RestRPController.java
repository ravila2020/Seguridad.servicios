package com.mtto.sat.rest;

import java.util.List;

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

import com.mtto.sat.modelo.RolePermission;
import com.mtto.sat.repositorio.IMRolePermissionRepo;
import com.mtto.sat.result.AnsRPList;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/RolPerm")
public class RestRPController {
	
	@Autowired
	private IMRolePermissionRepo rolPermRel;

	@GetMapping
	public AnsRPList listar(){
		AnsRPList Respuesta = new AnsRPList();
	    System.out.print(" + RestRPController listar \n");
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(rolPermRel.findAll());

        return Respuesta;
	}

	@GetMapping(path = {"/{id}"})
	public AnsRPList buscar(@PathVariable("id") String id){
		AnsRPList Respuesta = new AnsRPList();
	    System.out.print(" + RestRPController buscar " + id + " \n");
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(rolPermRel.findAllByRoleId(Integer.valueOf(id)));

        return Respuesta;
	}

	@PostMapping
	public AnsRPList insertar(@RequestBody RolePermission NuevoRolPermiso){
		AnsRPList Respuesta = new AnsRPList();
		RolePermission RolPermisoEnProceso = rolPermRel.save(NuevoRolPermiso);
		
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(rolPermRel.findAllByRoleId(NuevoRolPermiso.getRoleId()));	
        return Respuesta;
	}

	@PutMapping
	public AnsRPList agrupar(@RequestBody List<RolePermission> NuevoRolPermiso){
		AnsRPList Respuesta = new AnsRPList();
		Iterable <RolePermission> RolPermisoEnProceso = rolPermRel.saveAll(NuevoRolPermiso);

	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(NuevoRolPermiso);	
        return Respuesta;
	}

	
	@DeleteMapping(path = {"/{id}"})
	public AnsRPList Eliminar(@PathVariable("id") String id){
		AnsRPList Respuesta = new AnsRPList();
	    System.out.print(" + RessAURolController Eliminar id: " + id + " \n");

		rolPermRel.deleteByRoleId(Integer.valueOf(id));
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");

        return Respuesta;
	}
}
