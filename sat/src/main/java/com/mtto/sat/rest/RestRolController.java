package com.mtto.sat.rest;

import java.time.ZonedDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtto.sat.modelo.AppUserRole;
import com.mtto.sat.modelo.Role;
import com.mtto.sat.modelo.RoleId;
import com.mtto.sat.repositorio.IMAppUserRoleRepo;
import com.mtto.sat.repositorio.IMRolePermissionRepo;
import com.mtto.sat.repositorio.IMRoleRepo;
import com.mtto.sat.result.AnsRol;
import com.mtto.sat.result.AnsRolOpc;
import com.mtto.sat.result.AnsRolPag;
import com.mtto.sat.result.GenericResponse;

import Respuesta.RolPag;
import io.jsonwebtoken.Jwts;

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
	
	// Consulta de la lista de roles con validacion de token.
	@GetMapping
	public AnsRol listar(HttpServletRequest peticion){
		AnsRol Respuesta = new AnsRol();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRolController: " + token + "\n ");	

		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRolController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
		
	    System.out.print(" + RestRolController listar \n");
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setRoles(repRole.findAll());
		return Respuesta;
	}

	// Consulta de un roles con validacion de token.
	@GetMapping(path = {"/{id}"})
	public AnsRolOpc buscar(HttpServletRequest peticion,
			 				@PathVariable("id") String id){
		AnsRolOpc Respuesta = new AnsRolOpc();
	    System.out.print(" + RestRolController buscar id: " + id + " \n");
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRolController: " + token + "\n ");	

		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRolController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
		
	    RoleId Clave = new RoleId();
	    Integer idRol = Integer.valueOf(id);
		Clave.setId(idRol);
		
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setRoles(repRole.findById(idRol));
		return Respuesta;

	}

	// Consulta de la lista paginada de roles con validacion de token.
    @GetMapping(path = {"/pag"})
    public AnsRolPag listarPag(HttpServletRequest peticion,
    							@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage) {
    	AnsRolPag Respuesta = new AnsRolPag();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRolController: " + token + "\n ");	
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRolController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
    	
		boolean enabled = true;
		Role rolCero = new Role();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<Role> todosRoles;
		List<Role> paginaRoles; 
		Integer rolInicial, rolFinal;
		
		RolPag resultado = new RolPag();
    	
    	System.out.print(" + RestRolController listarPag page: " + page + " perpage: " + perPage +"\n ");

    	// obtener el total.
         todos = repRole.count();
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
         // Obtener la lista solicitada
         rolInicial = (perPage  * (page - 1) );
         rolFinal   = (rolInicial + perPage) - 1;
         todosRoles  = repRole.findAll();
         paginaRoles = repRole.findAll();
         paginaRoles.clear();
         for (int i=0; i<todos;i++) {
        	 System.out.print("\n " + "          + RestRolController Rol: " + i + " - " + todosRoles.get(i).getDescription());
        	 if(i>=rolInicial && i<=rolFinal)
        	 {
        		 rolCero = todosRoles.get(i);
        		 paginaRoles.add(rolCero);
        		 System.out.print("  -- En lista  --" + rolCero.getDescription());
        	 }
         }
         
         
     	System.out.print("\n + RestRolController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) repRole.count());
         resultado.setTotalPages(pagEntero);
         resultado.setRoles(paginaRoles);
         
 	    Respuesta.setCr("00");
 	    Respuesta.setDescripcion("Correcto");
 	    Respuesta.setContenido(resultado);
 		return Respuesta;
    }

	// Consulta de la lista con filtro paginada de roles con validacion de token.
    @GetMapping(path = {"/paglike"})
    public AnsRolPag listarPagLike(HttpServletRequest peticion,
    							@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage,
    		                    @RequestParam(required = false, value = "id") String id ) {
    	
    	String like = "%" + id + "%";
    	AnsRolPag Respuesta = new AnsRolPag();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRolController: " + token + "\n ");	
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRolController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}    	
    	
		boolean enabled = true;
		Role rolCero = new Role();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<Role> todosRoles;
		List<Role> paginaRoles; 
		Integer rolInicial, rolFinal;
		
		RolPag resultado = new RolPag();
    	
    	System.out.print(" + RestRolController listarPag page: " + page + " perpage: " + perPage +"\n ");

    	// obtener el total.
 //      todos = repRole.count();
    	 todos = repRole.countByCuenta(like);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
         // Obtener la lista solicitada
         rolInicial = (perPage  * (page - 1) );
         rolFinal   = (rolInicial + perPage) - 1;
//         todosRoles  = repRole.findAll();
//         paginaRoles = repRole.findAll();
         todosRoles  = repRole.findByDescription(like);
         paginaRoles = repRole.findByDescription(like);
         paginaRoles.clear();
         for (int i=0; i<todos;i++) {
        	 System.out.print("\n " + "          + RestRolController Rol: " + i + " - " + todosRoles.get(i).getDescription());
        	 if(i>=rolInicial && i<=rolFinal)
        	 {
        		 rolCero = todosRoles.get(i);
        		 paginaRoles.add(rolCero);
        		 System.out.print("  -- En lista  --" + rolCero.getDescription());
        	 }
         }
         
         
     	System.out.print("\n + RestRolController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal(todos.intValue());
         resultado.setTotalPages(pagEntero);
         resultado.setRoles(paginaRoles);
         
 	    Respuesta.setCr("00");
 	    Respuesta.setDescripcion("Correcto");
 	    Respuesta.setContenido(resultado);
 		return Respuesta;
    }
    
	// Insertar un rol con validacion de token.    
	@PostMapping
	public AnsRolOpc insertar(HttpServletRequest peticion,
							  @RequestBody Role NuevoRol){
		AnsRolOpc Respuesta = new AnsRolOpc();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRolController: " + token + "\n ");	
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRolController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
		
		Role RolEnProceso = repRole.save(NuevoRol);
	    System.out.print(" + RestRolController insertar id: " + RolEnProceso.getId() + " \n");
		
		RoleId Clave = new RoleId();

		Clave.setId(RolEnProceso.getId());
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setRoles(repRole.findById(RolEnProceso.getId()));
		return Respuesta;
	}

	// Modificar un rol con validacion de token.
	@PutMapping
	public AnsRolOpc modificar(HttpServletRequest peticion,
							   @RequestBody Role ModifRol){
		AnsRolOpc Respuesta = new AnsRolOpc();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRolController: " + token + "\n ");	
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRolController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");		
			return Respuesta;
			}
		
		repRole.save(ModifRol);
		RoleId Clave = new RoleId();

		Clave.setId(ModifRol.getId());
	    Respuesta.setCr("00");
	    Respuesta.setDescripcion("Correcto");
	    Respuesta.setRoles(repRole.findById(ModifRol.getId()));
		return Respuesta;
	}

	// Eliminar un rol con validacion de token.
	@DeleteMapping(path = {"/{id}"})
	public GenericResponse Eliminar(HttpServletRequest peticion,
									@PathVariable("id") String id){
		GenericResponse Respuesta = new GenericResponse();
    	String token = peticion.getHeader("Authorization");
    	System.out.print("\n\n + RestRolController: " + token + "\n ");	
    	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestRolController Usuario: " + user + "\n ");
		}	else	{
			Respuesta.setCodigo(99);
			Respuesta.setMensaje("Petición sin token");
			Respuesta.setFechaHora(ZonedDateTime.now());
			return Respuesta;
			}    	
    	
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
