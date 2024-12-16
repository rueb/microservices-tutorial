package com.tutorial.moto_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.moto_service.entity.Moto;
import com.tutorial.moto_service.service.MotoServices;

@RestController
@RequestMapping("/moto")
public class MotoController {
	@Autowired
	private MotoServices motoServices;
	
	@GetMapping
	public ResponseEntity<List<Moto>>getAll(){
		List<Moto>motos = motoServices.getAll();
		if(motos.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(motos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto>getById(@PathVariable("id") int id){
		Moto moto = motoServices.getMotoById(id);
		if(moto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(moto);
	}
	
	@PostMapping
	public ResponseEntity<Moto>save(@RequestBody Moto moto){
		Moto newCarro = motoServices.save(moto);
		System.out.println(newCarro.getBrand()+" "+newCarro.getModel());
		return ResponseEntity.ok(newCarro);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Moto>delete(@PathVariable("id") int id){
		motoServices.delete(id);
		return (ResponseEntity<Moto>) ResponseEntity.ok();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Moto>update(@PathVariable("id") int id, @RequestBody Moto moto){
		Moto updateCarro = motoServices.update(moto, id);
		return ResponseEntity.ok(updateCarro);
	}
	
	@GetMapping("/byuser/{userId}")
	public ResponseEntity<List<Moto>>getByUserId(@PathVariable("userId") int userId){
		List<Moto>motos = (List<Moto>) motoServices.byUserId(userId);
	//	if(motos.isEmpty())
		//	return ResponseEntity.noContent().build();
		return ResponseEntity.ok(motos);
	}
}
