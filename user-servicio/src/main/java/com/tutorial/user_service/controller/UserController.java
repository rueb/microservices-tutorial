package com.tutorial.user_service.controller;

import java.util.List;
import java.util.Map;

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

import com.tutorial.user_service.entity.User;
import com.tutorial.user_service.model.Carro;
import com.tutorial.user_service.model.Moto;
import com.tutorial.user_service.service.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServices userServices;
	
	@GetMapping
	public ResponseEntity<List<User>>getAll(){
		List<User>users = userServices.getAll();
		if(users.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User>getById(@PathVariable("id") int id){
		User user = userServices.getUserById(id);
		if(user == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User>save(@RequestBody User user){
		User newUser = userServices.save(user);
		System.out.println(newUser.getName()+" "+newUser.getEmail());
		return ResponseEntity.ok(newUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User>delete(@PathVariable("id") int id){
		userServices.delete(id);
		return (ResponseEntity<User>) ResponseEntity.ok();
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<User>update(@PathVariable("id") int id, @RequestBody User user){
		User updateUser = userServices.update(user, id);
		return ResponseEntity.ok(updateUser);
	}
	
	@GetMapping("/carros/{userId}")
	public ResponseEntity<List<Carro>>getCarros(@PathVariable("userId") int userId){
		User user = userServices.getUserById(userId);
		if(user == null)
			return ResponseEntity.notFound().build();
		List<Carro>carros = userServices.getCarros(userId);
		return ResponseEntity.ok(carros);
		
	}
	
	@GetMapping("/motos/{userId}")
	public ResponseEntity<List<Moto>>getMotos(@PathVariable("userId")int userId){
		System.out.println(userId);
		System.out.println(userServices.getUserById(userId));
		User user = userServices.getUserById(userId);
		if(user == null)
			return ResponseEntity.notFound().build();
		List<Moto>motos = userServices.getMotos(userId);
		
		return ResponseEntity.ok(motos);
	}
	@PostMapping("/savecar/{userId}")
	public ResponseEntity<Carro>guardarCarro(@PathVariable("userId") int userId,@RequestBody Carro carro){
		if(userServices.getUserById(userId) == null)
			return ResponseEntity.notFound().build();
		Carro newCar = userServices.saveCar(userId, carro);
		return ResponseEntity.ok(newCar);
	}
	
	@PostMapping("/savemoto/{userId}")
	public ResponseEntity<Moto>guardarMoto(@PathVariable("userId") int userId,@RequestBody Moto moto){
		if(userServices.getUserById(userId) == null)
			return ResponseEntity.notFound().build();
		Moto newMoto = userServices.saveMoto(userId, moto);
		return ResponseEntity.ok(newMoto);
	}
	
	@GetMapping("/getAll/{userId}")
	public ResponseEntity<Map<String, Object>>getALlVehicles(@PathVariable("userId")int userId){
		Map<String, Object>result = userServices.getUserAndVehicles(userId);
		return ResponseEntity.ok(result);
	}
	
	

}


// https://www.youtube.com/watch?v=P-T_E5uP6m8&list=PL4bT56Uw3S4yTSw5Cg1-mhgoS85fVeFkT&index=5