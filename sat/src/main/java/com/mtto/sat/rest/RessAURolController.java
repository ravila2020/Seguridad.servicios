package com.mtto.sat.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtto.sat.modelo.AppUser;
import com.mtto.sat.modelo.AppUserRole;
import com.mtto.sat.modelo.AppUserRoleId;
import com.mtto.sat.modelo.RolePermission;
import com.mtto.sat.repositorio.IMAppUserRepo;
import com.mtto.sat.repositorio.IMAppUserRoleRepo;
import com.mtto.sat.repositorio.IMPermissionRepo;
import com.mtto.sat.repositorio.IMRolePermissionRepo;
import com.mtto.sat.result.AnsUserRolList;

import io.jsonwebtoken.Jwts;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/UserRol")
public class RessAURolController {

	@Autowired
	private IMPermissionRepo userPermRel;

	@Autowired
	private IMRolePermissionRepo RolPermRel;

	@Autowired
	private IMAppUserRoleRepo userRolPermRel;

	@Autowired
	private IMAppUserRepo usuarioJWT;

	// Consulta de lista de todos los usuarios rol con validacion de token.
	@GetMapping
	public AnsUserRolList listar(HttpServletRequest peticion){
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RessAURolController token: " + token + "\n ");
		AnsUserRolList Respuesta = new AnsUserRolList();
	    System.out.print(" + RessAURolController listar \n");
	    
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
	    
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(userRolPermRel.findAll());
		return Respuesta;
	}

	// Consulta de un usuario - rol con validacion de token.	
	@GetMapping(path = {"/{id}"})
	public AnsUserRolList buscar(HttpServletRequest peticion, 
								@PathVariable("id") String id){
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RessAURolControllertoken: " + token + "\n ");		
		AnsUserRolList Respuesta = new AnsUserRolList();
		
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
	
	
	// Alta de un usuario - rol con validacion de token.	
	@PostMapping
	public AnsUserRolList insertar(HttpServletRequest peticion,
									@RequestBody AppUserRole NuevoUserRol){
		
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RessAURolController contenido de token: " + token + "\n ");	
    	AnsUserRolList Respuesta = new AnsUserRolList();
    	String userPeticion;

		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n + RestAppUserController Usuario codificado en el token: " + user + "\n ");
			userPeticion= user;
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
		// buscar el Id del usuario
		System.out.print("\n + RestAppUserController Usuario que hace la petición (userPeticion): " + userPeticion + "\n ");
		Optional<AppUser> UsuarioPet = usuarioJWT.findByUsername(userPeticion);
		System.out.print("\n + RestAppUserController Numero de userPeticion: " + UsuarioPet.get().getId() + "\n ");
		
		// buscar los roles asociados al Id del usuario
		AppUserRoleId UsuarioId = new  AppUserRoleId();
		UsuarioId.setAppUserId(UsuarioPet.get().getId());
		System.out.print("\n + RestAppUserController UsuarioId getAppUserId: " + UsuarioId.getAppUserId());
		System.out.print("\n + RestAppUserController UsuarioId getRoleId: " + UsuarioId.getRoleId());

		List<AppUserRole> UserRolPeticion  = userRolPermRel.findAllByAppUserId(UsuarioId.getAppUserId());
		System.out.print("\n + RestAppUserController Numero de Roles: " + UserRolPeticion.size() + "\n ");
		
		// Buscar los permisos asociados a los roles.
		boolean autorizado = false;
        for (int i=0; i<UserRolPeticion.size();i++) 
        {
       	 System.out.print("          + RestAppUserController Rol: " + i + " - " + UserRolPeticion.get(i).getRoleId() + "\n ");
       	 List<RolePermission> permisosRol = RolPermRel.findAllByRoleId( UserRolPeticion.get(i).getRoleId());
         for (int j=0; j<permisosRol.size();j++) 
            {
           	 System.out.print("                 + RestAppUserController Permiso: " + j + " - " + permisosRol.get(j).getPermissionId() + "\n ");
           	 if (permisosRol.get(j).getPermissionId()==7)
           	    {
           		 autorizado = true;
           	    }         	 
            }
        }
        if(!autorizado)
        {
			Respuesta.setCr("94");
			Respuesta.setDescripcion("Usuario sin permiso.");		
			return Respuesta;	
        }
		
//		Optional<Permission> PermUserPet = userPermRel.findById(UserRolPeticion.get().getRoleId());
//		System.out.print("\n + RestAppUserController Numero de userPeticion: " + UserRolPeticion.get().getRoleId() + "\n ");
		
		// Buscar si tiene el permiso asociado a esta funcion (alta de Usuario - Rol)
		

    	AppUserRole UserRolEnProceso = userRolPermRel.save(NuevoUserRol);
	
	    AppUserRoleId Clave = new AppUserRoleId();
	    
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setContenido(userRolPermRel.findAllByAppUserId(Integer.valueOf(NuevoUserRol.getAppUserId())));
		return Respuesta;
	
	}
	
	// Eliminación de un usuario - rol con validacion de token.	

	@DeleteMapping(path = {"/{id}"})
	public AnsUserRolList Eliminar(HttpServletRequest peticion,
								   @PathVariable("id") String id){
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RessAURolControllertoken: " + token + "\n ");	
    	
		AnsUserRolList Respuesta = new AnsUserRolList();
	    System.out.print(" + RessAURolController Eliminar id: " + id + " \n");

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
	    
	    
	    AppUserRoleId Clave = new AppUserRoleId();
	    Integer idRol = Integer.valueOf(id);
		Clave.setAppUserId(idRol);

		userRolPermRel.deleteByAppUserId(idRol);
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    return Respuesta;
	}
}
