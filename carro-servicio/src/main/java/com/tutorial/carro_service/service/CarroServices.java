package com.tutorial.carro_service.service;    
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.carro_service.entity.Carro;
import com.tutorial.carro_service.repositorio.CarroRepository;


@Service
public class CarroServices {

	@Autowired
	private CarroRepository carroRepository;
	
	public List<Carro>getAll(){
		return (List<Carro>) carroRepository.findAll();
	}
	
	public Carro getUserById(int id) {
		return carroRepository.findById(id).orElseThrow();
	}
	
	public Carro save(Carro carro) {
		Carro newUser = carroRepository.save(carro);
		System.out.println(newUser.getBrand()+" "+newUser.getModel());
		return newUser;
	}
	
	public void delete(int id) {
		carroRepository.deleteById(id);
	}
	
	public Carro update(Carro carro,int id) {
		Carro updateUser = new Carro();
		carroRepository.findById(id);
		updateUser.setBrand(carro.getBrand());
		updateUser.setModel(carro.getModel());
		 updateUser = carroRepository.save(carro);
		return updateUser;
	}
	
	public List<Carro>byUserId(int id){
		return carroRepository.findByUserId(id);
	}
	
}
// https://www.youtube.com/watch?v=czWbpqC1fLY&list=PL4bT56Uw3S4yTSw5Cg1-mhgoS85fVeFkT&index=2