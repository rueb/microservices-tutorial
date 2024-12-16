package com.tutorial.carro_service.controller;

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

import com.tutorial.carro_service.entity.Carro;
import com.tutorial.carro_service.service.CarroServices;

@RestController
@RequestMapping("/carro")
public class CarroController {
	@Autowired
	private CarroServices carroServices;
	
	@GetMapping
	public ResponseEntity<List<Carro>>getAll(){
		List<Carro>carros = carroServices.getAll();
		if(carros.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(carros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carro>getById(@PathVariable("id") int id){
		Carro carro = carroServices.getUserById(id);
		if(carro == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(carro);
	}
	
	@PostMapping
	public ResponseEntity<Carro>save(@RequestBody Carro carro){
		Carro newCarro = carroServices.save(carro);
		System.out.println(newCarro.getBrand()+" "+newCarro.getModel());
		return ResponseEntity.ok(newCarro);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Carro>delete(@PathVariable("id") int id){
		carroServices.delete(id);
		return (ResponseEntity<Carro>) ResponseEntity.ok();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Carro>update(@PathVariable("id") int id, @RequestBody Carro carro){
		Carro updateCarro = carroServices.update(carro, id);
		return ResponseEntity.ok(updateCarro);
	}
	
	@GetMapping("/byuser/userId")
	public ResponseEntity<List<Carro>>getByUserId(@PathVariable("userId") int userId){
		List<Carro>carros = (List<Carro>) carroServices.byUserId(userId);
		//if(carros.isEmpty())
			//return ResponseEntity.noContent().build();
		return ResponseEntity.ok(carros);
	}
}
