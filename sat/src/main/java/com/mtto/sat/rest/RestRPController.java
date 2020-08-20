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

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/RolPerm")
public class RestRPController {
	
	@Autowired
	private IMRolePermissionRepo rolPermRel;

	@GetMapping
	public List<RolePermission> listar(){
	    System.out.print(" + RestRPController listar \n");

		return rolPermRel.findAll();
	}

	@GetMapping(path = {"/{id}"})
	public List<RolePermission> buscar(@PathVariable("id") String id){
	    System.out.print(" + RestRPController buscar " + id + " \n");

		return rolPermRel.findAllByRoleId(Integer.valueOf(id));
	}

	@PostMapping
	public void insertar(@RequestBody RolePermission NuevoRolPermiso){
		RolePermission RolPermisoEnProceso = rolPermRel.save(NuevoRolPermiso);
	}

	@PutMapping
	public void agrupar(@RequestBody List<RolePermission> NuevoRolPermiso){
		Iterable <RolePermission> RolPermisoEnProceso = rolPermRel.saveAll(NuevoRolPermiso);
	}

	
	@DeleteMapping(path = {"/{id}"})
	public void Eliminar(@PathVariable("id") String id){
	    System.out.print(" + RessAURolController Eliminar id: " + id + " \n");

		rolPermRel.deleteByRoleId(Integer.valueOf(id));
	}
}
