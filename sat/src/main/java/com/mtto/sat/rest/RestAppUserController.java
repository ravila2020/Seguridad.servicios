package com.mtto.sat.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtto.sat.exception.ApiRequestException;
import com.mtto.sat.log.logRegistra;
import com.mtto.sat.modelo.AppUser;
import com.mtto.sat.repositorio.IMAppUserRepo;
import com.mtto.sat.repositorio.IMLogTransacctionRepo;
import com.mtto.sat.result.AnsUserPag;
import com.mtto.sat.result.AnsUserPagList;
import com.mtto.sat.result.AnsUserPagOpc;

import Respuesta.AppUserPag;
import io.jsonwebtoken.Jwts;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Users")
public class RestAppUserController {

	@Autowired
	private IMAppUserRepo repAppUser;

	@Autowired
	private IMAppUserRepo repAppUserBack;

	@Autowired
	private BCryptPasswordEncoder codificador;
	
	@Autowired
	private IMLogTransacctionRepo repLog;

	@Autowired
	private logRegistra registrar;
	
//	@GetMapping
//	public List<AppUser> listar(HttpServletRequest peticion){
//		boolean enabled = true;
//		
//		String token = peticion.getHeader("Authorization");
//		System.out.print("\n\n + RestAppUserController token: " + token + "\n ");
//		if (token != null) {
//			String user = Jwts.parser()
//					.setSigningKey("0neProj3ct")
//					.parseClaimsJws(token.replace("Bearer",  ""))
//					.getBody()
//					.getSubject();
//			System.out.print("\n\n + RestAppUserController Usuario: " + user + "\n ");
//		}
//		
//		
//		return repAppUser.findByEnabled(enabled);
//	}

	@GetMapping
	public AnsUserPag listar(HttpServletRequest peticion){
		boolean enabled = true;
        AnsUserPag respuesta = new AnsUserPag();
        
		String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestAppUserController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestAppUserController Usuario: " + user + "\n ");
			respuesta.setCr("00");
			respuesta.setDescripcion("Correcto");	
			respuesta.setContenido(repAppUser.findByEnabled(enabled));			
		}else
		{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			}
		return respuesta;
	}

    @GetMapping(path = {"/{id}"})
    public AnsUserPagOpc listarId(@PathVariable("id") String id){
        AnsUserPagOpc respuesta = new AnsUserPagOpc();
    	System.out.print(" + RestAppUserController listarId id: " + id + "\n ");
    	respuesta.setCr("00");
    	respuesta.setDescripcion("Correcto");
    	respuesta.setContenido(repAppUser.findByUsername(id));
        return respuesta;
    }

    
    
    @GetMapping(path = {"like/{id}"})
    public AnsUserPag listarLike(@PathVariable("id") String id){
    	String like = "%" + id + "%";
    	AnsUserPag respuesta = new AnsUserPag();
    	System.out.print(" + RestAppUserController listarId id: " + like + "\n ");
    	respuesta.setCr("00");
    	respuesta.setDescripcion("Correcto");
    	respuesta.setContenido(repAppUser.findByFirstname(like));
        return respuesta;
    }
    
    
    
 /*   @GetMapping(path = {"/unic"})
    public Optional<Integer> listarDistinctId(@PathVariable("id") String id){
    	System.out.print(" + RestAppUserController listarId id: " + id + "\n ");
        return repAppUser.findDistinctByName();
    }
*/
    
    @GetMapping(path = {"/pag"})
    public AnsUserPagList listarPag(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
    		                    HttpServletRequest peticion) {
    	
    	AnsUserPagList respuesta = new AnsUserPagList();
    	String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestAppUserController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestAppUserController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
		boolean enabled = true;
		AppUser usuarioCero = new AppUser();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<AppUser> todosUsuarios;
		List<AppUser> paginaUsuarios; 
		Integer usuarioInicial, usuarioFinal;
		
    	AppUserPag resultado = new AppUserPag();
    	
    	System.out.print(" + RestAppUserController listarPag page: " + page + " perpage: " + perPage +"\n ");

    	// obtener el total.
//         todos = repAppUser.count();
         todos = repAppUser.countByEnabled(true);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
         // Obtener la lista solicitada
         usuarioInicial = (perPage  * (page - 1) );
         usuarioFinal   = (usuarioInicial + perPage) - 1;
         todosUsuarios  = repAppUser.findByEnabled(enabled);
         paginaUsuarios = repAppUser.findByEnabled(enabled);
         paginaUsuarios.clear();
         for (int i=0; i<todos;i++) {
        	 System.out.print("\n " + "          + RestAppUserController Usuario: " + i + " - " + todosUsuarios.get(i).getUsername() );
        	 if(i>=usuarioInicial && i<=usuarioFinal)
        	 {
        		 usuarioCero = todosUsuarios.get(i);
        		 paginaUsuarios.add(usuarioCero);
        		 System.out.print("  -- En lista  --" + usuarioCero.getUsername() );
        	 }
         }
         
         
     	System.out.print("\n + RestAppUserController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) repAppUser.countByEnabled(true));
         resultado.setTotalPages(pagEntero);
         resultado.setUsers(paginaUsuarios);

	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

    @GetMapping(path = {"/paglike"})
    public AnsUserPagList listarPagLike(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
    		                    @RequestParam(required = false, value = "id") String id, 
    		                    HttpServletRequest peticion) {
    	String like = "%" + id + "%";
    	AnsUserPagList respuesta = new AnsUserPagList();
    	String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestAppUserController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestAppUserController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
		boolean enabled = true;
		AppUser usuarioCero = new AppUser();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<AppUser> todosUsuarios;
		List<AppUser> paginaUsuarios; 
		Integer usuarioInicial, usuarioFinal;
		
    	AppUserPag resultado = new AppUserPag();
    	
    	System.out.print(" + RestAppUserController listarPag page: " + page + " perpage: " + perPage +"\n ");
    	// obtener el total.
         todos = repAppUser.countByFirstname(like);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
         // Obtener la lista solicitada
         usuarioInicial = (perPage  * (page - 1) );
         usuarioFinal   = (usuarioInicial + perPage) - 1;
         todosUsuarios  = repAppUser.findByFirstname(like);
         paginaUsuarios = repAppUser.findByFirstname(like);
         paginaUsuarios.clear();
         for (int i=0; i<todos;i++) {
        	 System.out.print("\n " + "          + RestAppUserController Usuario: " + i + " - " + todosUsuarios.get(i).getUsername() );
        	 if(i>=usuarioInicial && i<=usuarioFinal)
        	 {
        		 usuarioCero = todosUsuarios.get(i);
        		 paginaUsuarios.add(usuarioCero);
        		 System.out.print("  -- En lista  --" + usuarioCero.getUsername() );
        	 }
         }
         
         
     	System.out.print("\n + RestAppUserController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) repAppUser.countByFirstname(like));
         resultado.setTotalPages(pagEntero);
         resultado.setUsers(paginaUsuarios);

	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

    
	@PostMapping
	public  AnsUserPagOpc  creaUsuario(@RequestBody AppUser NuevoUsuario){
		AnsUserPagOpc respuesta = new AnsUserPagOpc();
		registrar.registra("creaUsuario", "AppUser", "/Users", NuevoUsuario);
    	try {
			String idAppUser = NuevoUsuario.getUsername();
	    	String CryptoPass = codificador.encode(NuevoUsuario.getPassword());
	    	NuevoUsuario.setPassword(CryptoPass);
			AppUser usuarioProc = repAppUser.save(NuevoUsuario);
			System.out.print(" + RestAppUserController insertar id: " + usuarioProc.getId() + " " + usuarioProc.getUsername() + "\n ");
			respuesta.setCr("00");
			respuesta.setDescripcion("Correcto");
			respuesta.setContenido(repAppUser.findByUsername(usuarioProc.getUsername()));
	        return respuesta;
	    	} catch (Exception ex) {
	    		throw new ApiRequestException("Upsi");
	    	}
		}

	@PutMapping(path = {"/{id}"})
	public AnsUserPagOpc cambiaPassword(@PathVariable("id") String id, @RequestBody AppUser NuevoUsuario){
		AnsUserPagOpc respuesta = new AnsUserPagOpc();
		String Password = NuevoUsuario.getPassword();
    	String CryptoPass = codificador.encode(NuevoUsuario.getPassword());
    	System.out.print(" + RestAppUserController listarId id: " + id + " Password:  " + Password + " " + CryptoPass + " \\n");
    	try {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(NuevoUsuario);
		System.out.print(" + Objeto: " + jsonInString);	
		
		registrar.registra("modificaPassword", "AppUser", "/Users/"+id, NuevoUsuario);
		
		
//    	Date date = new Date();
//    	LogTransacction log = new LogTransacction();
//    	log.setActionName("modificaPassword");
//    	log.setEntityName("AppUser");
//    	log.setOfficeId(0);
//    	log.setApiGetUrl("/Users/"+id);
//    	log.setResourceId(0);
//    	log.setSubresourceId(0);
//    	log.setCommandAsJson(jsonInString);
//    	log.setMakerId(0);
//    	log.setMadeOnDate(date);
//    	log.setCheckerId(0);
//    	log.setCheckedOnDate(date);
//    	log.setProcessingResultEnum(0);
//		repLog.save(log);    	
    	
		Optional<AppUser> UsuarioExistente = repAppUser.findByUsername(id);
		NuevoUsuario = UsuarioExistente.get();
    	NuevoUsuario.setPassword(CryptoPass);
		repAppUser.save(NuevoUsuario);
		respuesta.setContenido(repAppUser.findByUsername(id));
		respuesta.setCr("00");
		respuesta.setDescripcion("Correcto");	
		return respuesta;
    	} catch (Exception ex) {
    		throw new ApiRequestException("Upsi");
    	}
	}

	@PutMapping
	public AnsUserPagOpc modificar(@RequestBody AppUser NuevoUsuario){
		AnsUserPagOpc respuesta = new AnsUserPagOpc();
		String idAppUser = NuevoUsuario.getUsername();
 //   	String CryptoPass = codificador.encode(NuevoUsuario.getPassword());
 //   	NuevoUsuario.setPassword(CryptoPass);
    	try {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(NuevoUsuario);
		System.out.print(" + Objeto: " + jsonInString);		
		registrar.registra("modificaUsuario", "AppUser", "/Users/", NuevoUsuario);
		
//    	Date date = new Date();
//    	LogTransacction log = new LogTransacction();
//    	log.setActionName("modificaUsuario");
//    	log.setEntityName("AppUser");
//    	log.setOfficeId(0);
//    	log.setApiGetUrl("/Users");
//    	log.setResourceId(0);
//    	log.setSubresourceId(0);
//    	log.setCommandAsJson(jsonInString);
//    	log.setMakerId(0);
//    	log.setMadeOnDate(date);
//    	log.setCheckerId(0);
//    	log.setCheckedOnDate(date);
//    	log.setProcessingResultEnum(0);
//		repLog.save(log);    	

		repAppUser.save(NuevoUsuario);
		
		respuesta.setContenido(repAppUser.findByUsername(idAppUser));
		respuesta.setCr("00");
		respuesta.setDescripcion("Correcto");	
		return respuesta;

    	} catch (Exception ex) {
    		throw new ApiRequestException("Upsi");
    	}
	}
	
    @DeleteMapping(path = {"/{id}"})
    public AnsUserPagOpc EliminarId(@PathVariable("id") String id){
    	System.out.print(" + RestAppUserController EliminarId id: " + id + " ");
    	AnsUserPagOpc Resultado = new AnsUserPagOpc();
    	Optional<AppUser> Usuario = repAppUser.findByUsername(id);
    	AppUser UsuarioD = Usuario.get();
    	UsuarioD.setEnabled(false);

    	try {
    		ObjectMapper mapper = new ObjectMapper();
    		String jsonInString =id;
    		System.out.print(" + Objeto: " + jsonInString);		
    		registrar.registra("eliminaUsuario", "AppUser", "/Users/"+id, UsuarioD);
    		
//        	Date date = new Date();
//        	LogTransacction log = new LogTransacction();
//        	log.setActionName("eliminaUsuario");
//        	log.setEntityName("AppUser");
//        	log.setOfficeId(0);
//        	log.setApiGetUrl("/Users/"+id);
//        	log.setResourceId(0);
//        	log.setSubresourceId(0);
//        	log.setCommandAsJson(jsonInString);
//        	log.setMakerId(0);
//        	log.setMadeOnDate(date);
//        	log.setCheckerId(0);
//        	log.setCheckedOnDate(date);
//        	log.setProcessingResultEnum(0);
//    		repLog.save(log);  
    		
    		repAppUser.save(UsuarioD);
    		Resultado.setContenido(Usuario);
    		Resultado.setCr("00");
    		Resultado.setDescripcion("Correcto");
    		return Resultado; }
    	catch (Exception e) {
    		Resultado.setDescripcion("Registro con incidencia");
    		return Resultado;}
    }	
}
