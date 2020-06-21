package com.cloud.mongodb.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	
	@Id
	private String userId;
	
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date createAt = new Date();
	
	private Integer age;
	
	private String position;

	private Map<String, String> userSettings = new HashMap<>();
	
	//**********

}
