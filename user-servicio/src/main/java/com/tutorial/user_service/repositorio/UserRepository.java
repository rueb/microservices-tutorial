package com.tutorial.user_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.user_service.entity.User;
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
