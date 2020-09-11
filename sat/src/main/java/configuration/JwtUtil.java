package configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	static void AddAuthentication (HttpServletResponse respuesta, String username){
		String token = Jwts.builder()
				.setSubject(username)
				.signWith(SignatureAlgorithm.HS512,"juanpro")
				.compact();
		respuesta.addHeader("Authorization",  "Bearer" + token);
	}

	static Authentication GetAuthentication (HttpServletRequest peticion) {
		String token = peticion.getHeader("Authorization");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("juanpro")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			return user != null ? 
					null : 
						null;
		}
		return null;
	}
	
}
