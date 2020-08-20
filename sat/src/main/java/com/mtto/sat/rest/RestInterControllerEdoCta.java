package com.mtto.sat.rest;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mtto.sat.SatApplication;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/edocta")
public class RestInterControllerEdoCta {

	private static final Logger log = LoggerFactory.getLogger(SatApplication.class);
	
		@GetMapping
		public String listar(){
			final String uri2 = "http://10.200.14.110:3091/rest/BANSEFI/BECCONCS";
		    String consulta = ""; 
		    String consulta2 = ""; 

		    RestTemplate restTemplateHost = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setBasicAuth("BANSJM3","SOPTEC03");
			log.info(headers.toString());
			 
			log.info("{{\"USERHEADER\":\"BANSJM3\",\"PASSHEADER\":\"SOPTEC03\",\"IPHEADER\":\"X\",\"ENTIDAD\":\"0166\",\"CLIENTE\":\"9366629\",\"TOTALES\":\"S\",\"PAGINACION\":\"\",\"NUMREG\":\"10\"}");
		    
			HttpEntity<String> entity = new HttpEntity<String>("{\"USERHEADER\":\"BANSJM3\",\"PASSHEADER\":\"SOPTEC03\",\"IPHEADER\":\"X\",\"ENTIDAD\":\"0166\",\"CLIENTE\":\"602997\",\"TOTALES\":\"S\",\"PAGINACION\":\"\",\"NUMREG\":\"10\"}", headers);
   
			ResponseEntity<String> result = restTemplateHost.exchange(uri2, HttpMethod.POST, entity, String.class);
		     
			log.info(result.toString());
			log.info(result.getBody());
			
			consulta2 = result.getBody()  ;
 			Integer elemento = consulta2.indexOf("]");
			log.info(elemento.toString());

			consulta = "[ {" + consulta2.substring(315,elemento) + "] ";
			log.info(consulta);
 			
//			return result.getBody();
  			return consulta;
		}
		
	    @GetMapping(path = {"/{id}"})
	    public String listarId(@PathVariable("id") String id){
	    	String cliente  = id;
	 		
			final String uri3 = "http://10.200.14.110:3091/rest/BANSEFI/BECCONCS";
		    String consulta = ""; 
		    String consulta2 = ""; 

		    RestTemplate restTemplateHost = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setBasicAuth("BANSJM3","SOPTEC03");
			log.info(headers.toString());
			 
			log.info("{{\"USERHEADER\":\"BANSJM3\",\"PASSHEADER\":\"SOPTEC03\",\"IPHEADER\":\"X\",\"ENTIDAD\":\"0166\",\"CLIENTE\":\"" + cliente + "\",\"TOTALES\":\"S\",\"PAGINACION\":\"\",\"NUMREG\":\"10\"}");
		    
			HttpEntity<String> entity = new HttpEntity<String>("{\"USERHEADER\":\"BANSJM3\",\"PASSHEADER\":\"SOPTEC03\",\"IPHEADER\":\"X\",\"ENTIDAD\":\"0166\",\"CLIENTE\":\"" + cliente + "\",\"TOTALES\":\"S\",\"PAGINACION\":\"\",\"NUMREG\":\"10\"}", headers);
   
			ResponseEntity<String> result = restTemplateHost.exchange(uri3, HttpMethod.POST, entity, String.class);
		     
			log.info(result.toString());
			log.info(result.getBody());
			
			consulta2 = result.getBody()  ;
 			Integer elemento = consulta2.indexOf("]");
			log.info(elemento.toString());

			if(elemento<315) {
			consulta = "[ { } ] ";
			}
			else
			{
				consulta = "[ {" + consulta2.substring(315,elemento) + "] ";
				}
			
			log.info(consulta);
 			
//			return result.getBody();
  			return consulta;
	    }
	
	
}
