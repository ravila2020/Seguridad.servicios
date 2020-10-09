package com.mtto.sat.rest;

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
import com.mtto.sat.repositorio.IMPermissionRepo;
import com.mtto.sat.result.AnsPermPag;
import com.mtto.sat.result.AnsPermPagOpc;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Permisos")
public class RestPermissionControler {

	@Autowired
	private IMPermissionRepo repPermision;
	
	@GetMapping
	public AnsPermPag listar(){
		AnsPermPag Respuesta = new AnsPermPag();
	    System.out.print(" + RestPermisionController listar \n");
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(repPermision.findAll(Sort.by(Sort.Direction.ASC, "grouping")));
		return Respuesta;
	}

	@GetMapping(path = {"/{id}"})
	public AnsPermPagOpc buscar(@PathVariable("id") String id){
		AnsPermPagOpc Respuesta = new AnsPermPagOpc();
		
		System.out.print(" + RestPermissionControler buscar id: " + id + " \n");

//	    PermissionId Clave = new PermissionId();
	    Integer idPermission = Integer.valueOf(id);
//		Clave.setId(idPermission);
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(repPermision.findById(idPermission));

        return Respuesta;
	}

	@PostMapping
	public AnsPermPagOpc insertar(@RequestBody Permission NuevoPermiso){
		AnsPermPagOpc Respuesta = new AnsPermPagOpc();

		System.out.print(" ++    RestPermissionControler buscar NuevoPermiso: " + NuevoPermiso.getAction_name() + " \n"
																				+ NuevoPermiso.getCode() + " \n"
																				+ NuevoPermiso.getEntityName() + " \n"
																				+ NuevoPermiso.getGrouping() + " \n"
																				+ NuevoPermiso.getId() + " \n"
																				+ NuevoPermiso.isCanMakerChecker() + " \n"); 
		
		repPermision.save(NuevoPermiso);
//		PermissionId Clave = new PermissionId();

//		Clave.setId(NuevoPermiso.getId());
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(repPermision.findById(NuevoPermiso.getId()));

        return Respuesta;
	}

	@PutMapping
	public AnsPermPagOpc  modificar(@RequestBody Permission ModifPermiso){
		AnsPermPagOpc Respuesta = new AnsPermPagOpc();

		repPermision.save(ModifPermiso);
//		PermissionId Clave = new PermissionId();

//		Clave.setId(ModifPermiso.getId());
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(repPermision.findById(ModifPermiso.getId()));

        return Respuesta;
	}	

	@DeleteMapping(path = {"/{id}"})
	public AnsPermPagOpc Eliminar(@PathVariable("id") String id){
		AnsPermPagOpc Respuesta = new AnsPermPagOpc();
		System.out.print(" + RestPermissionControler Eliminar id: " + id + " \n");

//	    PermissionId Clave = new PermissionId();
	    Integer idRol = Integer.valueOf(id);
//		Clave.setId(idRol);

        repPermision.deleteById(Integer.valueOf(id));
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");

        return Respuesta;
	}

}
