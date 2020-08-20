package com.mtto.sat.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtto.sat.modelo.Permission;
import com.mtto.sat.modelo.PermissionId;
import com.mtto.sat.repositorio.IMPermissionRepo;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Permisos")
public class RestPermissionControler {

	@Autowired
	private IMPermissionRepo repPermision;
	
	@GetMapping
	public List<Permission> listar(){
	    System.out.print(" + RestPermisionController listar \n");

		return repPermision.findAll(Sort.by(Sort.Direction.ASC, "grouping"));
	}

	@GetMapping(path = {"/{id}"})
	public Optional<Permission> buscar(@PathVariable("id") String id){
		System.out.print(" + RestPermissionControler buscar id: " + id + " \n");

	    PermissionId Clave = new PermissionId();
	    Integer idPermission = Integer.valueOf(id);
		Clave.setId(idPermission);

        return repPermision.findById(idPermission);
	}

	@PostMapping
	public Optional<Permission> insertar(@RequestBody Permission NuevoPermiso){
		repPermision.save(NuevoPermiso);
		PermissionId Clave = new PermissionId();

		Clave.setId(NuevoPermiso.getId());

		return repPermision.findById(NuevoPermiso.getId());
	}

	@PutMapping
	public Optional<Permission> modificar(@RequestBody Permission ModifPermiso){
		repPermision.save(ModifPermiso);
		PermissionId Clave = new PermissionId();

		Clave.setId(ModifPermiso.getId());

		return repPermision.findById(ModifPermiso.getId());
	}	

	@DeleteMapping(path = {"/{id}"})
	public void Eliminar(@PathVariable("id") String id){
		System.out.print(" + RestPermissionControler Eliminar id: " + id + " \n");

	    PermissionId Clave = new PermissionId();
	    Integer idRol = Integer.valueOf(id);
		Clave.setId(idRol);

        repPermision.deleteById(Integer.valueOf(id));
	}

}
