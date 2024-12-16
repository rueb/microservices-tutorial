package com.tutorial.carro_service.repositorio;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.carro_service.entity.Carro;
@Repository
public interface CarroRepository extends CrudRepository<Carro, Integer>{
	
	List<Carro>findByUserId(int userId);

}
