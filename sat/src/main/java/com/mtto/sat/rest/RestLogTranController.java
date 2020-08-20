package com.mtto.sat.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtto.sat.modelo.LogTransacction;
import com.mtto.sat.repositorio.IMLogTransacctionRepo;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Log")
public class RestLogTranController {

	@Autowired
	private IMLogTransacctionRepo repLog;

	@GetMapping
	public List<LogTransacction> listar(){
	    System.out.print(" + RestLogTranController listar \n");

		return repLog.findAll(Sort.by(Sort.Direction.ASC, "Id"));
	}
}
