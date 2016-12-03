package com.orange.lizard.auth.security.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes("authorizationRequest")
public class UserController {
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}