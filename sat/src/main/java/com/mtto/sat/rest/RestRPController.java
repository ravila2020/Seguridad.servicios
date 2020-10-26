package com.mtto.sat.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import io.jsonwebtoken.Jwts;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/RolPerm")
public class RestRPController {
	
	@Autowired
	private IMRolePermissionRepo rolPermRel;

	// Consulta de la lista de Rol - Permiso con validacion de token.
	@GetMapping
	public AnsRPList listar(HttpServletRequest peticion){
		AnsRPList Respuesta = new AnsRPList();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRPController: " + token + "\n ");	
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRPController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}    	
 
		System.out.print(" + RestRPController listar \n");
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(rolPermRel.findAll());

        return Respuesta;
	}

	// Consulta de un rol - permiso con validacion de token.
	@GetMapping(path = {"/{id}"})
	public AnsRPList buscar(HttpServletRequest peticion,
							@PathVariable("id") String id){
		AnsRPList Respuesta = new AnsRPList();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRPController: " + token + "\n ");
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRPController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
    	
	    System.out.print(" + RestRPController buscar " + id + " \n");
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(rolPermRel.findAllByRoleId(Integer.valueOf(id)));

        return Respuesta;
	}

	// Alta de un rol - permiso con validacion de token.
	@PostMapping
	public AnsRPList insertar(HttpServletRequest peticion,
							  @RequestBody RolePermission NuevoRolPermiso){
		AnsRPList Respuesta = new AnsRPList();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRPController: " + token + "\n ");
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRPController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
    	
		RolePermission RolPermisoEnProceso = rolPermRel.save(NuevoRolPermiso);
		
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(rolPermRel.findAllByRoleId(NuevoRolPermiso.getRoleId()));	
        return Respuesta;
	}

	// Alta de un grupo un rol - permiso con validacion de token.
	@PutMapping
	public AnsRPList agrupar(HttpServletRequest peticion,
							 @RequestBody List<RolePermission> NuevoRolPermiso){
		AnsRPList Respuesta = new AnsRPList();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRPController: " + token + "\n ");	
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRPController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}    	
    	
		Iterable <RolePermission> RolPermisoEnProceso = rolPermRel.saveAll(NuevoRolPermiso);

	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(NuevoRolPermiso);	
        return Respuesta;
	}

	// Eliminacion de un grupo un rol - permiso con validacion de token.	
	@DeleteMapping(path = {"/{id}"})
	public AnsRPList Eliminar(HttpServletRequest peticion,
							  @PathVariable("id") String id){
		AnsRPList Respuesta = new AnsRPList();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRPController: " + token + "\n ");	
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRPController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}    	
    	
	    System.out.print(" + RessAURolController Eliminar id: " + id + " \n");

		rolPermRel.deleteByRoleId(Integer.valueOf(id));
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");

        return Respuesta;
	}
}
