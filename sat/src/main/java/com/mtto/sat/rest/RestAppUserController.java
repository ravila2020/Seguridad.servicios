package com.mtto.sat.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.mtto.sat.modelo.LogTransacction;
import com.mtto.sat.repositorio.IMAppUserRepo;
import com.mtto.sat.repositorio.IMLogTransacctionRepo;
import com.mtto.sat.result.GenericResponse;

import Respuesta.AppUserPag;
import io.jsonwebtoken.Jwts;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
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
	
	
	@GetMapping
	public List<AppUser> listar(HttpServletRequest peticion){
		boolean enabled = true;
		
		String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestAppUserController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestAppUserController Usuario: " + user + "\n ");
		}
		
		
		return repAppUser.findByEnabled(enabled);
	}

    @GetMapping(path = {"/{id}"})
    public Optional<AppUser> listarId(@PathVariable("id") String id){
    	System.out.print(" + RestAppUserController listarId id: " + id + "\n ");
        return repAppUser.findByUsername(id);
    }

 /*   @GetMapping(path = {"/unic"})
    public Optional<Integer> listarDistinctId(@PathVariable("id") String id){
    	System.out.print(" + RestAppUserController listarId id: " + id + "\n ");
        return repAppUser.findDistinctByName();
    }
*/
    
    @GetMapping(path = {"/pag"})
    public AppUserPag listarPag(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
    		                    HttpServletRequest peticion) {
    		//@QueryParam("page") int page, @QueryParam("perPage") int perPage){
		String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestAppUserController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestAppUserController Usuario: " + user + "\n ");
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
         return resultado;
    }

	@PostMapping
	public  Optional<AppUser>  creaUsuario(@RequestBody AppUser NuevoUsuario){
		registrar.registra("creaUsuario", "AppUser", "/Users", NuevoUsuario);
    	try {
			String idAppUser = NuevoUsuario.getUsername();
	    	String CryptoPass = codificador.encode(NuevoUsuario.getPassword());
	    	NuevoUsuario.setPassword(CryptoPass);
			AppUser usuarioProc = repAppUser.save(NuevoUsuario);
			System.out.print(" + RestAppUserController insertar id: " + usuarioProc.getId() + " " + usuarioProc.getUsername() + "\n ");
	        return repAppUser.findByUsername(usuarioProc.getUsername());
	    	} catch (Exception ex) {
	    		throw new ApiRequestException("Upsi");
	    	}
		}

	@PutMapping(path = {"/{id}"})
	public Optional<AppUser> cambiaPassword(@PathVariable("id") String id, @RequestBody AppUser NuevoUsuario){
		String Password = NuevoUsuario.getPassword();
    	String CryptoPass = codificador.encode(NuevoUsuario.getPassword());
    	System.out.print(" + RestAppUserController listarId id: " + id + " Password:  " + Password + " " + CryptoPass + " \\n");
    	try {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(NuevoUsuario);
		System.out.print(" + Objeto: " + jsonInString);		
    	Date date = new Date();
    	LogTransacction log = new LogTransacction();
    	log.setActionName("modificaPassword");
    	log.setEntityName("AppUser");
    	log.setOfficeId(0);
    	log.setApiGetUrl("/Users/"+id);
    	log.setResourceId(0);
    	log.setSubresourceId(0);
    	log.setCommandAsJson(jsonInString);
    	log.setMakerId(0);
    	log.setMadeOnDate(date);
    	log.setCheckerId(0);
    	log.setCheckedOnDate(date);
    	log.setProcessingResultEnum(0);
		repLog.save(log);    	
    	
		Optional<AppUser> UsuarioExistente = repAppUser.findByUsername(id);
		NuevoUsuario = UsuarioExistente.get();
    	NuevoUsuario.setPassword(CryptoPass);
		repAppUser.save(NuevoUsuario);

		return repAppUser.findByUsername(id);
    	} catch (Exception ex) {
    		throw new ApiRequestException("Upsi");
    	}
	}

	@PutMapping
	public Optional<AppUser> modificar(@RequestBody AppUser NuevoUsuario){
		String idAppUser = NuevoUsuario.getUsername();
 //   	String CryptoPass = codificador.encode(NuevoUsuario.getPassword());
 //   	NuevoUsuario.setPassword(CryptoPass);
    	try {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(NuevoUsuario);
		System.out.print(" + Objeto: " + jsonInString);		
    	Date date = new Date();
    	LogTransacction log = new LogTransacction();
    	log.setActionName("modificaUsuario");
    	log.setEntityName("AppUser");
    	log.setOfficeId(0);
    	log.setApiGetUrl("/Users");
    	log.setResourceId(0);
    	log.setSubresourceId(0);
    	log.setCommandAsJson(jsonInString);
    	log.setMakerId(0);
    	log.setMadeOnDate(date);
    	log.setCheckerId(0);
    	log.setCheckedOnDate(date);
    	log.setProcessingResultEnum(0);
		repLog.save(log);    	

		repAppUser.save(NuevoUsuario);
		return repAppUser.findByUsername(idAppUser);
    	} catch (Exception ex) {
    		throw new ApiRequestException("Upsi");
    	}
	}
	
    @DeleteMapping(path = {"/{id}"})
    public GenericResponse EliminarId(@PathVariable("id") String id){
    	System.out.print(" + RestAppUserController EliminarId id: " + id + " ");
    	GenericResponse Resultado = new GenericResponse();
    	Optional<AppUser> Usuario = repAppUser.findByUsername(id);
    	AppUser UsuarioD = Usuario.get();
    	UsuarioD.setEnabled(false);

    	try {
    		ObjectMapper mapper = new ObjectMapper();
    		String jsonInString =id;
    		System.out.print(" + Objeto: " + jsonInString);		
        	Date date = new Date();
        	LogTransacction log = new LogTransacction();
        	log.setActionName("eliminaUsuario");
        	log.setEntityName("AppUser");
        	log.setOfficeId(0);
        	log.setApiGetUrl("/Users/"+id);
        	log.setResourceId(0);
        	log.setSubresourceId(0);
        	log.setCommandAsJson(jsonInString);
        	log.setMakerId(0);
        	log.setMadeOnDate(date);
        	log.setCheckerId(0);
        	log.setCheckedOnDate(date);
        	log.setProcessingResultEnum(0);
    		repLog.save(log);  
    		
    		repAppUser.save(UsuarioD);
    		Resultado.setMensaje("Registro Eliminado");
    		Resultado.setFechaHora(null);
    		return Resultado; }
    	catch (Exception e) {
    		Resultado.setMensaje("Registro con incidencia");
    		Resultado.setFechaHora(null);
    		return Resultado;}
    }	
}
