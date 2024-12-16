package com.tutorial.user_service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tutorial.user_service.model.Carro;

@FeignClient(name = "carro-service",url = "http://localhost:8002")
@RequestMapping("/car")
public interface CarroFeignClient {
	@PostMapping
	Carro save(@RequestBody Carro carro);
	
	@GetMapping("/byuser/{userId}")
	List<Carro>getCarros(@PathVariable("userId") int userId);

}
