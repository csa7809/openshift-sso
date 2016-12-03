package com.orange.lizard.auth.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.orange.lizard.auth.user.entities.Role;
import com.orange.lizard.auth.user.entities.User;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;
	public CustomUserDetails(User usr){
		this.user=usr;
	}
	

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> res = new ArrayList<>();
		for(Role r : user.getRoles()){
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_" + r.getName().toUpperCase());
			res.add(ga);
		}
		return res;
	}

	@Override
	public String toString(){
		return "CustomUserDetails { " + user.getUsername() + "}";
	}
}
