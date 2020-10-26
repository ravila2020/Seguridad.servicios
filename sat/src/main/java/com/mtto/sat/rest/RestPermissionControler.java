package com.mtto.sat.rest;

import javax.servlet.http.HttpServletRequest;

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

import io.jsonwebtoken.Jwts;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Permisos")
public class RestPermissionControler {

	@Autowired
	private IMPermissionRepo repPermision;

	// Consulta de lista de permisos con validacion de token.
	@GetMapping
	public AnsPermPag listar(HttpServletRequest peticion){
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestPermissionControler: " + token + "\n ");	
		AnsPermPag Respuesta = new AnsPermPag();
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestPermissionControler Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
    	
	    System.out.print(" + RestPermisionController listar \n");
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(repPermision.findAll(Sort.by(Sort.Direction.ASC, "grouping")));
		return Respuesta;
	}

	// Consulta de permiso con validacion de token.
	@GetMapping(path = {"/{id}"})
	public AnsPermPagOpc buscar(HttpServletRequest peticion,
								@PathVariable("id") String id){
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestPermissionControler: " + token + "\n ");
		AnsPermPagOpc Respuesta = new AnsPermPagOpc();

		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestPermissionControler Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
		
		System.out.print(" + RestPermissionControler buscar id: " + id + " \n");

//	    PermissionId Clave = new PermissionId();
	    Integer idPermission = Integer.valueOf(id);
//		Clave.setId(idPermission);
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(repPermision.findById(idPermission));

        return Respuesta;
	}

	// Alta de permiso con validacion de token.
	@PostMapping
	public AnsPermPagOpc insertar(HttpServletRequest peticion,
								  @RequestBody Permission NuevoPermiso){
		AnsPermPagOpc Respuesta = new AnsPermPagOpc();
		
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestPermissionControler: " + token + "\n ");	

		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestPermissionControler Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}

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
	
	// Modificación de permiso con validacion de token.
	@PutMapping
	public AnsPermPagOpc  modificar(HttpServletRequest peticion,
			 						@RequestBody Permission ModifPermiso){
		AnsPermPagOpc Respuesta = new AnsPermPagOpc();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestPermissionControler: " + token + "\n ");	

		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestPermissionControler Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}

		
		repPermision.save(ModifPermiso);
//		PermissionId Clave = new PermissionId();

//		Clave.setId(ModifPermiso.getId());
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(repPermision.findById(ModifPermiso.getId()));

        return Respuesta;
	}	

	// Eliminación de permiso con validacion de token.
	@DeleteMapping(path = {"/{id}"})
	public AnsPermPagOpc Eliminar(HttpServletRequest peticion,
								  @PathVariable("id") String id){
		AnsPermPagOpc Respuesta = new AnsPermPagOpc();
		System.out.print(" + RestPermissionControler Eliminar id: " + id + " \n");
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestPermissionControler: " + token + "\n ");
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestAppUserController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}

//	    PermissionId Clave = new PermissionId();
	    Integer idRol = Integer.valueOf(id);
//		Clave.setId(idRol);

        repPermision.deleteById(Integer.valueOf(id));
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");

        return Respuesta;
	}

}
