package com.orange.lizard.auth.user.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="utilisateur")
public class User implements Serializable{
	private static final long serialVersionUID = -5371870052211560058L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userid;
	
	@Column(length=45)
	@OrderColumn
	private String username;
	
	@Column(length=255)
	private String email;
	
	@Column(length=60)
	private String password;
		
	private boolean  enabled;
	
	
	@JoinTable(name="user_role",
			joinColumns={@JoinColumn(name="userid")},
	inverseJoinColumns= { 
        @JoinColumn(name = "name", referencedColumnName = "name")
        }
	)
	
	@ManyToMany(fetch=FetchType.EAGER)
	//@Fetch(FetchMode.SUBSELECT)
	private List<Role> roles = new ArrayList<>();

	public User(){
		// Default serializable constructor
	}
	
	public User(User usr){
		this.userid=usr.userid;
		this.username=usr.username;
		this.password=usr.password;
		this.email=usr.email;
		this.enabled=usr.enabled;
	}
	
	
	
	public User(String username, String password, Role... roles) {
		this.username=username;
		this.password=password;
		this.roles.addAll(Arrays.asList(roles));
		this.enabled=true;
	}




	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
