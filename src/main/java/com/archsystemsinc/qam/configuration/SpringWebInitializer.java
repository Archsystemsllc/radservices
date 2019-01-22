package com.archsystemsinc.qam.configuration;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
@EnableWebMvc
public class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	@Bean
   	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		//Local Development Environment
    	String activeProfile = System.getProperty("spring.profiles.active",	"local");
    	
    	//AWS Development Environment
    	//String activeProfile = System.getProperty("spring.profiles.active",	"development");
    	
    	//AWS UAT Environment
		//String activeProfile = System.getProperty("spring.profiles.active", "uat");																																																																																																																																																																																																																																																																		
   
    	//AWS Prod Environment
    	//String activeProfile = System.getProperty("spring.profiles.active", "prod");
    	
    	//AWS CMS Prod Environment
    	//String activeProfile = System.getProperty("spring.profiles.active", "cmsprod");
    	
   		String propertiesFilename = "application-" + activeProfile	+ ".properties";
   		System.out.println("propertiesFilename:" + propertiesFilename);
   		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
   		configurer.setLocation(new ClassPathResource(propertiesFilename));

   		return configurer;
   	}
	
	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SpringConfiguration.class, CustomWebSecurityConfigurerAdapter.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
    	Filter [] singleton = { new CORSFilter() };
    	return singleton;
	}
    
    @Value("${radui.uploadfile.server.location}")
    public static String SERVER_UPLOAD_FILE_LOCATION;
 
    //private static final String LOCATION = "/usr/share/tomcat8/work/Catalina/localhost/ROOT/"; 
   
    private static final String LOCATION = SERVER_UPLOAD_FILE_LOCATION;
    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
                                                       // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
    
    private MultipartConfigElement getMultipartConfigElement() {

    MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
    
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }
    
   /* @Bean(name="multipartResolver") 
    public CommonsMultipartResolver getResolver() throws IOException{
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
         
        //Set the maximum allowed size (in bytes) for each individual file.
        resolver.setMaxUploadSizePerFile(5242880);//5MB
         
        //You may also set other available properties.
         
        return resolver;
    }*/
    
    
}