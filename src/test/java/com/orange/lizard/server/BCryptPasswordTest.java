package com.orange.lizard.server;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class BCryptPasswordTest {
	PasswordEncoder encoder= new BCryptPasswordEncoder();

	@Test
	public void encyptDecrypt(){
		String rawPassword = "admin";
		String encodedPassword= encoder.encode(rawPassword);
		
		Assert.assertTrue("mismatch password",encoder.matches(rawPassword, encodedPassword));
		System.out.println("admin: " + encodedPassword);
		
	}
	
	@Test
	public void encryptMyBigSecret(){
		String rawPassword = "MyBigSecret";
		String encodedPassword= encoder.encode(rawPassword);
		
		Assert.assertTrue("mismatch password",encoder.matches(rawPassword, encodedPassword));
		System.out.println("MyBigSecret: " + encodedPassword);
	}
	@Test
	public void encryptClaude(){
		String rawPassword = "claude";
		String encodedPassword= encoder.encode(rawPassword);
		
		Assert.assertTrue("mismatch password",encoder.matches(rawPassword, encodedPassword));
		System.out.println("claude: " + encodedPassword);
	}
	
	@Test
	public void encryptUser(){
		String rawPassword = "user";
		String encodedPassword= encoder.encode(rawPassword);
		
		Assert.assertTrue("mismatch password",encoder.matches(rawPassword, encodedPassword));
		System.out.println("user: " + encodedPassword);
	}
}
