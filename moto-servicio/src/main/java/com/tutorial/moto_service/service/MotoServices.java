package com.tutorial.moto_service.service;    
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.moto_service.entity.Moto;
import com.tutorial.moto_service.repositorio.MotoRepository;


@Service
public class MotoServices {

	@Autowired
	private MotoRepository motoRepository;
	
	public List<Moto>getAll(){
		return (List<Moto>) motoRepository.findAll();
	}
	
	public Moto getMotoById(int id) {
		return motoRepository.findById(id).orElseThrow();
	}
	
	public Moto save(Moto moto) {
		Moto newUser = motoRepository.save(moto);
		System.out.println(newUser.getBrand()+" "+newUser.getModel());
		return newUser;
	}
	
	public void delete(int id) {
		motoRepository.deleteById(id);
	}
	
	public Moto update(Moto moto,int id) {
		Moto updateUser = new Moto();
		motoRepository.findById(id);
		updateUser.setBrand(moto.getBrand());
		updateUser.setModel(moto.getModel());
		 updateUser = motoRepository.save(moto);
		return updateUser;
	}
	
	public List<Moto>byUserId(int id){
		return motoRepository.findByUserId(id);
	}
	
}
// https://www.youtube.com/watch?v=czWbpqC1fLY&list=PL4bT56Uw3S4yTSw5Cg1-mhgoS85fVeFkT&index=2