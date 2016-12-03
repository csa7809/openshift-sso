package com.orange.lizard.auth.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.orange.lizard.auth.user.domain.CustomUserDetails;
import com.orange.lizard.auth.user.entities.User;
import com.orange.lizard.auth.user.repositories.UserRepository;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public JpaUserDetailsService() {
		// default empty constructor
	}

	public JpaUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
		// this.userRolesRepository=userRolesRepository;
	}

	/**
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		} else {
			return new CustomUserDetails(user);
		}
	}

}
