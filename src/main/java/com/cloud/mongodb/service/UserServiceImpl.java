package com.cloud.mongodb.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.mongodb.entity.User;

@Service
public class UserServiceImpl implements IUserService {
	
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	//***************

	
	//Find all user
	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		
		return mongoTemplate.findAll(User.class);
	}

	//Find By Id
	@Override
	@Transactional(readOnly = true)
	public User geUserById(String userId) {
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("_id").is(userId));
		
		User user = mongoTemplate.findOne(query, User.class);
		
		if (user != null) {
						
			return user;
			
		}else {
			
			return null;
		}
	}

	//Add new User
	@Override
	@Transactional
	public User addNewUser(User user) {
		
		mongoTemplate.save(user);
		
		return user;
	}

	//Find All User Settings
	@Override
	@Transactional(readOnly = true)
	public Object getAllUserSettigns(String userId) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		
		User user = mongoTemplate.findOne(query, User.class);
		
		return user != null ? user.getUserSettings() : "User not found";
	}

	//Find a User Setting
	@Override
	@Transactional(readOnly = true)
	public Map<String, String> getUserSettigns(String userId, String key) {
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("userId").is(userId));
		
		return mongoTemplate.findOne(query, User.class).getUserSettings();
	}

	//Add new User Setting
	@Override
	@Transactional
	public String addUserSettings(String userId, String key, String value) {

		Query query = new Query();
		
		query.addCriteria(Criteria.where("userId").is(userId));
		
		User user = mongoTemplate.findOne(query, User.class);
		
		if (user != null) {
			
			user.getUserSettings().put(key, value);
			mongoTemplate.save(user);
			
		}else {
			
			return "User not found";
		}
		
		return "Key added";
	}

	//Delete a User
	@Transactional
	public String deleteById(String userId) {
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("_id").is(userId));
		
		if (mongoTemplate.find(query, User.class).size() != 0) {
			
			mongoTemplate.remove(query, User.class);
			
			return "[Documento " + userId + " eliminado correctamente]";
			
		}else {
			
			return "[El Id " + userId + " no existe. Eliminación fallida]";
		}
		//System.out.println(mongoTemplate.getDb().getName());
	}

	@Override
	public List<User> findUsersByAge(Integer ageIni, Integer ageEnd) {

		return null;
	}

	@Override
	public List<User> findUsersByPositionPatter(String pattern) {

		return null;
	}
	
	//Update User
	public String updateUser(User user) {
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("_id").is(user.getUserId()));
		
		if (mongoTemplate.find(query, User.class).size() != 0) {
			
			Update update = new Update();
			
			update.set("name", user.getName());
			update.set("age", user.getAge());
			update.set("position", user.getPosition());
			update.set("userSettings", user.getUserSettings());
			
			mongoTemplate.updateFirst(query, update, User.class);
			
			return "[Documento " + user + " actualizado correctamente]";
			
		}else {
			
			return "[El Id " + user.getUserId() + " no existe. Actualizaciñon fallida]";
		}
	}

}
