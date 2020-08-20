package com.mtto.sat.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtto.sat.modelo.Mpdt026;
import com.mtto.sat.modelo.Mpdt026Id;
import com.mtto.sat.repositorio.IMpdt026Repo;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/mpdt026")
public class RestDemoControllerMP26 {

	@Autowired
	private IMpdt026Repo repo;
	
	@GetMapping
	public List<Mpdt026> listar(){
		return repo.findAll();
	}
	
    @GetMapping(path = {"/{id}"})
    public Optional<Mpdt026> listarId(@PathVariable("id") String id){
    	String codent  = id.substring(0,4);
    	String codmar  = id.substring(4,6);
    	String indtipt  = id.substring(6,8);
 		Integer icodmar = Integer.valueOf(codmar); 
 		Integer iindtipt = Integer.valueOf(indtipt);
   	
		System.out.print("codent: " + codent + " " + "codmar: " + codmar + " " + "indtipt: " + indtipt + " ");
		Mpdt026Id llave26 = new Mpdt026Id();
		
		llave26.setCodent(codent);
		llave26.setCodmar(icodmar);
		llave26.setIndtipt(iindtipt);

        return repo.findById(llave26);
    }

	@PostMapping
	public void insertar(@RequestBody Mpdt026 marca){
		repo.save(marca);
	}
	
	@PutMapping
	public void modificar(@RequestBody Mpdt026 marca){
		repo.save(marca);
	}
		
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") String id){
    	String codent  = id.substring(0,4);
    	String codmar  = id.substring(4,6);
    	String indtipt  = id.substring(6,8);
 		Integer icodmar = Integer.valueOf(codmar); 
 		Integer iindtipt = Integer.valueOf(indtipt);
   	
		Mpdt026Id llave26 = new Mpdt026Id();
		
		llave26.setCodent(codent);
		llave26.setCodmar(icodmar);
		llave26.setIndtipt(iindtipt);
		
		repo.deleteById(llave26);
	}

}
