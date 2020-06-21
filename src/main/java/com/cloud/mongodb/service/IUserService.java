package com.cloud.mongodb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.Query;

import com.cloud.mongodb.entity.User;

public interface IUserService {
	
	List<User> getAllUsers();
	
	User geUserById(String userId);
	
	User addNewUser(User user);
	
	Object getAllUserSettigns(String userId);
	
	Map<String, String> getUserSettigns(String userId, String key);
	
	Object addUserSettings(String userId, String key, String value);
	
	@Query("{$and: [{'age': {$gt: ?0}},{'age': {$lt: ?1}}]}")
	List<User> findUsersByAge(Integer ageIni, Integer ageEnd);
	
	@Query("{'position': {$regex: /?0/i}}")
	List<User> findUsersByPositionPatter(String pattern);
	
	String deleteById(String userId);
	
	String updateUser(User user);

}
