package com.orange.lizard.auth.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Déclare la datasource (primaire)
 * 
 * @author NSTW7013
 *
 */
@Configuration
public class JpaConfiguration {

	
//	@Bean
//	@Primary
////	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource primaryDataSource() {
//	    return DataSourceBuilder.create().build();
//	}
	
	
//	
//	
//	@Configuration
//	public class FlywaySlaveInitializer {
//
//	     @Autowired private DataSource dataSource2;
//	     @Autowired private DataSource dataSource3;
//	     //other datasources
//
//	     @PostConstruct
//	     public void migrateFlyway() {
//	         Flyway flyway = new Flyway();
//	         //if default config is not sufficient, call setters here
//
//	         //source 2
//	         flyway.setDataSource(dataSource2);
//	         flyway.migrate();
//
//	         //source 3
//	         flyway.setDataSource(dataSource3);
//	         flyway.migrate();
//	     }
//	}
}
