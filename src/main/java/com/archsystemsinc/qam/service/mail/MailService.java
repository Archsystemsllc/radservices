package com.archsystemsinc.qam.service.mail;

import com.archsystemsinc.qam.utils.EmailObject;

public interface MailService {
	public boolean sendEmail(EmailObject emailObject);
	
}
