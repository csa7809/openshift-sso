package com.orange.lizard.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.orange.lizard.auth.user.entities.Role;
import com.orange.lizard.auth.user.entities.User;
import com.orange.lizard.auth.user.repositories.RoleRepository;
import com.orange.lizard.auth.user.repositories.UserRepository;


//@EnableResourceServer
//@EnableAuthorizationServer


//SpringBootApplication equals:  @Configuration, @EnableAutoConfiguration and @ComponentScan
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
//	@Autowired
//	UserRepository userService;
//	@Autowired
//	RoleRepository roleService;
//	
//	
//	@Bean
//	CommandLineRunner  initDB(){
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//				
//				PasswordEncoder encoder = new BCryptPasswordEncoder();
//				
//				Role admin=new Role("OAUTH_ADMIN");
//				Role actuator=new Role("ACTUATOR");
//				Role api=new Role("API");
//
//				roleService.save(admin);
//				roleService.save(actuator);
//				roleService.save(api);
//				
//				userService.save(new User("admin",encoder.encode("admin"),admin,api));
//				userService.save(new User("manager",encoder.encode("manager"),actuator));
//				userService.save(new User("egecko",encoder.encode("password"),api));
//				userService.save(new User("csa",encoder.encode("claude"),api));
//			}
//		};
//	}
//	
//    // a setter method so that the Spring container can inject a MovieFinder
//    public void setUserRepository(UserRepository movieFinder) {
//        this.userService = movieFinder;
//    }
    
}