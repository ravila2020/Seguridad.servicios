package com.mtto.sat.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtto.sat.modelo.AppUser;
import com.mtto.sat.modelo.AppUserRole;
import com.mtto.sat.modelo.AppUserRoleId;
import com.mtto.sat.modelo.PermissionId;
import com.mtto.sat.modelo.Role;
import com.mtto.sat.modelo.RolePermission;
import com.mtto.sat.modelo.RolePermissionId;
import com.mtto.sat.repositorio.IMAppUserRepo;
import com.mtto.sat.repositorio.IMAppUserRoleRepo;
import com.mtto.sat.repositorio.IMPermissionRepo;
import com.mtto.sat.repositorio.IMRolePermissionRepo;
import com.mtto.sat.repositorio.IMRoleRepo;

import Respuesta.AnswAuth;
import Respuesta.EntradaAuth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Auth")
public class RestAuthController {

	@Autowired
	private BCryptPasswordEncoder codificador;
	
	@Autowired
	private IMAppUserRepo repAppUser;

	@Autowired
	private IMAppUserRoleRepo repAppUserRole;

	@Autowired
	private IMRoleRepo repRole;

	@Autowired
	private IMRolePermissionRepo repRolePerm;
	
	@Autowired
	private IMPermissionRepo repPerm;

	@GetMapping
	public List<AppUser> listar(){
		return repAppUser.findAll();
	}

	
	@PostMapping
	public AnswAuth autorizacion(@RequestBody EntradaAuth entrada){
    	String autoriz = entrada.getUser();
    	String pass = entrada.getPassword();
    	
	    System.out.print(" + RestAuthController autorizacion Entrada user: " + autoriz + "\n" + "Entrada password: " + pass + "\n");
    	
    	String CryptoPass = codificador.encode(entrada.getPassword());
	    System.out.print(" + RestAuthController autorizacion Password encriptado: " + CryptoPass + "\n");

	    // -------------------  Instancia de objetos
    	AnswAuth Respuesta = new AnswAuth();
    	
    	AppUser Usuario = new AppUser();
    	
    	AppUserRoleId UsuarioRolClave = new AppUserRoleId();
    	AppUserRole UsuarioRol = new AppUserRole();
    	List<AppUserRole> ListUsuarioRol; // = (List<AppUserRole>) new AppUserRole();
    	
    	Role  Rol = new Role();
//    	RoleId RolClave = new RoleId();
    	
    	RolePermissionId RolPermClave = new RolePermissionId();
    	PermissionId PermClave = new PermissionId();
    	List<RolePermission> ListRolPerm;
    	
	    // -------------------  carga de objetos

    	Optional<AppUser> respuestaAppUser = repAppUser.findByUsername(autoriz);

    	
    	if (respuestaAppUser.isPresent()) {
    	Usuario = respuestaAppUser.get();
    	System.out.print(" + RestAuthController autorizacion Usuario: " + Usuario.getId() + "\n \n");                        //Usuario consultado

    	int cveUs = Usuario.getId();
    	Integer id = Integer.valueOf(cveUs);
    	System.out.print(" + RestAuthController autorizacion  Usuario Id: " + cveUs + "\n \n");
    	UsuarioRolClave.setAppUserId(Integer.valueOf(cveUs));
    	System.out.print(" + RestAuthController autorizacion  UsuarioRolClave AppUserId: " + UsuarioRolClave.getAppUserId() + "\n \n ");
    	ListUsuarioRol = repAppUserRole.findAllByAppUserId(Integer.valueOf(cveUs)); 
    	System.out.print(" + RestAuthController autorizacion Usuario Rol: " + UsuarioRol.getRoleId()  + " \n \n");
    	
    	ListRolPerm = repRolePerm.findAllByRoleId(1);
    	
//    	RolClave.setId(UsuarioRol.getRoleId());
//    	Rol = repRole.getOne(UsuarioRol.getRoleId());  
//    	System.out.print(" Rol: " + Rol.getDescription()  + " \n \n");
    	
//   	RolPermClave.setRoleId(UsuarioRol.getRoleId());
//    	List<RolePermission> RolPerm = repRolePerm.findAllByRoleId(UsuarioRol.getRoleId());
//    	RolPerm = repRolePerm.findByroleId(UsuarioRol.getRoleId());
//    	System.out.print(" Rol Perm: " + RolPerm.getPermissionId()  + " \n \n");

//    	PermClave.setId(Integer.valueOf(RolPerm.getPermissionId()));
//    	List<Permission> Perm. = repPerm.findById(RolPerm.get().getPermissionId());
//    	System.out.print(" Perm: " + RolPerm.get().getPermissionId()  + " \n \n");

 //* validación de password
    	
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String existingPassword = pass;
    	String dbPassword       = Usuario.getPassword();

     	
    	
    	respuestaAppUser.of(Usuario);
    //	return respuestaAppUser;
//    	Respuesta.setAuthenticated(true);
    	System.out.print(" + RestAuthController autorizacion existingPassword: " + existingPassword  +  " -- dbPassword :" + dbPassword +" \n \n");
    	if (passwordEncoder.matches(existingPassword, dbPassword)) {
        	System.out.print(" + RestAuthController autorizacion Match!!");
        	Respuesta.setAuthenticated(true);
        	Respuesta.setOfficeId(Usuario.getOfficeId());
        	Respuesta.setUsername(Usuario.getUsername());
        	Respuesta.setUserId(Usuario.getId());
        	Respuesta.setName(Rol.getName());
        	Respuesta.setDescription(Rol.getDescription());
        	Respuesta.setDisable(Rol.isDisable());
//        	Respuesta.setPermissions(Perm.get().getCode());
        	Respuesta.setBase64EncodedAuthenticationKey(Usuario.getPassword());
//        	
        	Respuesta.setRoleId(repAppUserRole.findAllByAppUserId(Integer.valueOf(cveUs)));
        	Respuesta.setRolePerms(repRolePerm.findAllByRoleId(1));
//        	
        	Respuesta.setNonlocked(Usuario.isNonlocked());
        	if (!Usuario.isNonlocked()) {
        		Respuesta.setDescription("Usuario bloqueado");
        	}
        	else {
        		Respuesta.setDescription("Acceso autorizado");
        		String token = Jwts.builder()
        				.setSubject(autoriz)
        				.signWith(SignatureAlgorithm.HS512,"0neProj3ct")
        				.compact();
        		Respuesta.setToken("Bearer" + token);
        		Usuario.setIsDeleted(0);
        		Usuario.setNonlocked(true);
        		repAppUser.save(Usuario);
        	}

    	} else {
        	System.out.print(" + RestAuthController autorizacion No Match!!");
        	int intentos = Usuario.getIsDeleted();
        	intentos = intentos + 1;
        	if (intentos > 2)  	{
        		Usuario.setIsDeleted(intentos);
        		Usuario.setNonlocked(false);
            	repAppUser.save(Usuario);
            	Respuesta.setDescription("Usuario bloqueado");
        	}
        	else      	{
        		Respuesta.setDescription("Usuario/Password erroneo");
        		Usuario.setIsDeleted(intentos);
            	repAppUser.save(Usuario);
            }
        	Respuesta.setAuthenticated(false);
        	Respuesta.setUsername(Usuario.getUsername());
        	Respuesta.setBase64EncodedAuthenticationKey("--");
    	 }   
        }
        else
        {
        	Respuesta.setDescription("Usuario/Password erroneo");
        	Respuesta.setAuthenticated(false);
        	Respuesta.setUsername(Usuario.getUsername());
        	Respuesta.setBase64EncodedAuthenticationKey("--");
        }

   	
    	return Respuesta;
    }
	
}
