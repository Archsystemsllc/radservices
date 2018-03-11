package com.archsystemsinc.qam.service;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.archsystemsinc.qam.model.RadUser;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private RadUser user;
 
    public OnRegistrationCompleteEvent(
      RadUser user, Locale locale, String appUrl) {
        super(user);
         
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public RadUser getUser() {
		return user;
	}

	public void setUser(RadUser user) {
		this.user = user;
	}
    
    
     
    // standard getters and setters
}
