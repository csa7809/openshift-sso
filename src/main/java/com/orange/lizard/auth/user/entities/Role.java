package com.orange.lizard.auth.user.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role implements Serializable{
	private static final long serialVersionUID = 3835690345715132016L;
	
	@Id
	String name;

	public Role(){
		// Default serializable constructor
	}
	public Role(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
