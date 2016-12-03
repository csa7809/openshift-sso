package com.orange.lizard.auth.security;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.orange.lizard.auth.user.UserConfig;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/*
* @author Moritz Schulze
*/
@Configuration
@EnableAuthorizationServer
@Import(UserConfig.class)
public class OAuthAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {


	public static final String RESOURCE_ID = "SSO";
	
	/** Besoin d'injecter l'authenticationManager pour réaliser le grant_type=password */
	@Autowired
	private AuthenticationManager authenticationManager;

//	@Autowired
//	private UserDetailsService userDetailsService;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource_oauth")
    public DataSource oauthDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * We expose the JdbcClientDetailsService because it has extra methods that the Interface does not have. E.g.
     * {@link org.springframework.security.oauth2.provider.client.JdbcClientDetailsService#listClientDetails()} which we need for the
     * admin page.
     */
    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(oauthDataSource());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(oauthDataSource());
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(oauthDataSource());
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(oauthDataSource());
    }

    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    	oauthServer.realm("lizard");
		oauthServer
			
			// /oauth/token_key obtains key to validate JWT
			.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
			
			// oauth/check_token : access egals to "denyAll()" by default
			.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
		
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .approvalStore(approvalStore())
                .authorizationCodeServices(authorizationCodeServices())
                .tokenStore(tokenStore())
                //.userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }

    
    
	@PostConstruct
	public void migrateFlyway(){
	         Flyway flyway = new Flyway();
	         //if default config is not sufficient, call setters here

	         //source 2
	         flyway.setDataSource(oauthDataSource());
	         flyway.setLocations("flyway.h2.oauth");
	         flyway.migrate();

	}    
}
