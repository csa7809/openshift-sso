package com.orange.lizard.auth.user;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.orange.lizard.auth.user.repositories.RoleRepository;


@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "customerEntityManagerFactory",
		transactionManagerRef = "customerTransactionManager",basePackageClasses={RoleRepository.class})
//@EnableJpaRepositories
//@EnableTransactionManagement
public
class UserConfig {

	@Bean
	PlatformTransactionManager customerTransactionManager() {
		return new JpaTransactionManager(customerEntityManagerFactory().getObject());
	}

	@Bean
	LocalContainerEntityManagerFactoryBean customerEntityManagerFactory() {

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(customerDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan(UserConfig.class.getPackage().getName());

		return factoryBean;
	}

	@Bean
	@Primary
	DataSource primaryDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource_user")
	DataSource customerDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@PostConstruct
	public void migrateFlyway(){
	         Flyway flyway = new Flyway();
	         //if default config is not sufficient, call setters here

	         //source 2
	         flyway.setDataSource(customerDataSource());
	         flyway.setLocations("flyway.h2.user");
	         flyway.migrate();

	}
}
