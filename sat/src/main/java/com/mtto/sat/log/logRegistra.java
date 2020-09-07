package com.mtto.sat.log;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtto.sat.exception.ApiRequestException;
import com.mtto.sat.modelo.LogTransacction;
import com.mtto.sat.repositorio.IMLogTransacctionRepo;

@Service
public class logRegistra {

	@Autowired
	private IMLogTransacctionRepo repLog;

	
	public void registra(String accion, String entidad, String urlRequest, Object objeto) {
	try {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(objeto);
		
		System.out.print(" + Objeto: " + jsonInString);
		
    	Date date = new Date();
    	LogTransacction log = new LogTransacction();
    	log.setActionName(accion);
    	log.setEntityName(entidad);
    	log.setOfficeId(0);
    	log.setApiGetUrl(urlRequest);
    	log.setResourceId(0);
    	log.setSubresourceId(0);
    	log.setCommandAsJson(jsonInString);
    	log.setMakerId(0);
    	log.setMadeOnDate(date);
    	log.setCheckerId(0);
    	log.setCheckedOnDate(date);
    	log.setProcessingResultEnum(0);
		repLog.save(log);
    	} catch (Exception ex) {
    		throw new ApiRequestException("Upsi");
    	}    
	}
}
