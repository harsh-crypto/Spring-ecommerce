package com.shopme.WebParent.BackEnd.user;

import org.springframework.data.repository.CrudRepository;

import com.shopme.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	

}
