package com.tutorial.user_service.service;    
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tutorial.user_service.entity.User;
import com.tutorial.user_service.feignclients.CarroFeignClient;
import com.tutorial.user_service.feignclients.MotoFeignClient;
import com.tutorial.user_service.model.Carro;
import com.tutorial.user_service.model.Moto;
import com.tutorial.user_service.repositorio.UserRepository;


@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CarroFeignClient carroFeignClient;
	
	@Autowired
	private MotoFeignClient motoFeignClient;
	
	public List<User>getAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public User getUserById(int id) {
		return userRepository.findById(id).orElseThrow();
	}
	
	public User save(User user) {
		User newUser = userRepository.save(user);
		System.out.println(newUser.getEmail()+" "+newUser.getName());
		return newUser;
	}
	
	public void delete(int id) {
		userRepository.deleteById(id);
	}
	
	public User update(User user,int id) {
		User updateUser = new User();
		userRepository.findById(id);
		updateUser.setName(user.getName());
		updateUser.setEmail(user.getEmail());
		 updateUser = userRepository.save(user);
		return updateUser;
	}
	
	public List<Carro>getCarros(int userId){
		List<Carro>carros = restTemplate.getForObject("http://localhost:8002/carro/byuser/"+userId,List.class);
		return carros;
	}
	
	public List<Moto>getMotos(int userId){
		System.out.println(userId);
		List<Moto>motos = restTemplate.getForObject("http://localhost:8003/moto/byuser/"+userId,List.class);
		
		return motos;
	}
	
	public Carro saveCar(int userId,Carro carro) {
		carro.setUserId(userId);
		Carro carroNew = carroFeignClient.save(carro);
		return carroNew;
	}
	
	public Moto saveMoto(int userId,Moto moto) {
		moto.setUserId(userId);
		Moto motoNew = motoFeignClient.save(moto);
		return motoNew;
	}
	
	public Map<String,Object>getUserAndVehicles(int userId){
		Map<String, Object>result = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) {
			result.put("Mensaje:","no existe el usuario");
			return result;
		}
		result.put("User", user);
		List<Carro>carros = carroFeignClient.getCarros(userId);
		if(carros.isEmpty())
			result.put("Carros", "Ese usuario no tiene carro");
		else
			result.put("Carros", carros);
		List<Moto>motos = motoFeignClient.getMotos(userId);
		if(motos.isEmpty())
			result.put("Motos", "ese usuario no tiene motos");
		else
			result.put("Motos", motos);
		return result;
	}
	
	
}




