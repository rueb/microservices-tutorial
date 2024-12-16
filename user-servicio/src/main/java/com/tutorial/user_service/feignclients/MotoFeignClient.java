package com.tutorial.user_service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tutorial.user_service.model.Moto;

@FeignClient(name = "moto-service",url = "http://localhost:8003")
@RequestMapping("/moto")
public interface MotoFeignClient {

	@PostMapping
	Moto save(@RequestBody Moto moto);
	
	@GetMapping("/byuser/{userId}")
	List<Moto>getMotos(@PathVariable("userId") int userId);
}
