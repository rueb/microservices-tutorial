package com.tutorial.moto_service.repositorio;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.moto_service.entity.Moto;
@Repository
public interface MotoRepository extends CrudRepository<Moto, Integer>{
	
	List<Moto>findByUserId(int userId);

}
