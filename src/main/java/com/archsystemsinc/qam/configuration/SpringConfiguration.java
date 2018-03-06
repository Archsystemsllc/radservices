package com.archsystemsinc.qam.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({"com.archsystemsinc.qam","com.archsystems"})
public class SpringConfiguration extends WebMvcConfigurerAdapter{
	
	@Value("${mail.host}")
    String mailHost;
	
	@Value("${mail.userName}")
    String mailUserName;
	
	@Value("${mail.password}")
    String mailPassword;
	
	@Value("${mail.port}")
    Integer mailPort;
	
	@Value("${mail.fromName}")
    String mailFromName;
	
	 	@Bean
	    public JavaMailSender getMailSender(){
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	         
	        //Using gmail
	        mailSender.setHost(mailHost);
	        mailSender.setPort(mailPort);
	        mailSender.setUsername(mailUserName);
	        mailSender.setPassword(mailPassword);
	         
	        Properties javaMailProperties = new Properties();
	       
	        javaMailProperties.put("mail.transport.protocol", "smtp");
	        javaMailProperties.put("mail.smtp.auth", "true");
	        javaMailProperties.put("mail.smtp.starttls.enable", "true");
	        javaMailProperties.put("mail.smtp.ssl.trust", mailHost);
	        
	        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
	        mailSender.setJavaMailProperties(javaMailProperties);
	        
	        /*//Using Go Daddy Email
		        mailSender.setHost("smtpout.secureserver.net");
		        mailSender.setPort(80);
		        mailSender.setUsername("ashaik@archsystemsinc.com");
		        mailSender.setPassword("Ahoom123");
		         
		        Properties javaMailProperties = new Properties();
		       
		        javaMailProperties.put("mail.transport.protocol", "smtp");
		        javaMailProperties.put("mail.smtp.auth", "true");
		        javaMailProperties.put("mail.smtp.starttls.enable", "true");
		        //javaMailProperties.put("mail.smtp.ssl.trust", "smtpout.secureserver.net");
		        
		        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
	      
	        mailSender.setJavaMailProperties(javaMailProperties);*/
	        return mailSender;
	    }
	 	
	 	@Bean(name="multipartResolver")
	    public StandardServletMultipartResolver resolver(){
	        return new StandardServletMultipartResolver();
	    }
}