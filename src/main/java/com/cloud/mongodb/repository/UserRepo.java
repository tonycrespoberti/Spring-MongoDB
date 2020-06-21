package com.cloud.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cloud.mongodb.entity.User;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

	
}
