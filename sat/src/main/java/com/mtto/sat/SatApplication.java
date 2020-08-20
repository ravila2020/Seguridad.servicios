package com.mtto.sat;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;





@SpringBootApplication
public class SatApplication {

	private static final Logger log = LoggerFactory.getLogger(SatApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SatApplication.class, args);
	}
	
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(getHttpConnector());
        return tomcat;
    }

    private Connector getHttpConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(9898);
        connector.setSecure(false);
        connector.setRedirectPort(9443);
        return connector;
    }
    
    
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
/*
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			final String uri = "http://10.200.14.110:3091/rest/BANSEFI/LOGON001";
		     
//		    RestTemplate restTemplateHost = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setBasicAuth("BANSJM3","SOPTEC03");
			log.info(headers.toString());
			 
			log.info("{  \"USUARIO\": \"BANSJM3\",  \"PASSWORD\": \"SOPTEC03\",  \"NEWPASSWORD\": \"\",  \"IP\": \"X\"}");
		    
			HttpEntity<String> entity = new HttpEntity<String>("{  \"USUARIO\": \"BANSJM3\",  \"PASSWORD\": \"SOPTEC03\",  \"NEWPASSWORD\": \"\",  \"IP\": \"X\"}", headers);
   
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		     
			log.info(result.toString());

		};    
	}*/
}
