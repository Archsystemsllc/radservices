package com.archsystemsinc.qam.service.mail;

public interface MailService {
	public boolean sendEmail(String emailType, String fromEmail, String toEmail);
	
}
