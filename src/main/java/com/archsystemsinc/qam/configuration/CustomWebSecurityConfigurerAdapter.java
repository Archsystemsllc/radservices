/**
 * 
 */
package com.archsystemsinc.qam.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author PrakashTotta
 *
 */
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
 
	 private static String REALM="MY_QAM_REALM";
     
	    @Autowired
	    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication().withUser("qamadmin").password("123456").roles("ADMIN");
	        auth.inMemoryAuthentication().withUser("qq").password("abc123").roles("USER");
	    }
	     
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	  
	      http.csrf().disable()
	        .authorizeRequests()
	       // .antMatchers("/api/download-document").permitAll()
	        .antMatchers("/api/**").hasRole("ADMIN")
	        
	        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
	        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
	    }
	     
	    @Bean
	    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
	        return new CustomBasicAuthenticationEntryPoint();
	    }
	     
	   
}
