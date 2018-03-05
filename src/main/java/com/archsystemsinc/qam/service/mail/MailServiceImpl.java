package com.archsystemsinc.qam.service.mail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.archsystemsinc.cmts.sec.util.GenericConstants;
import com.archsystemsinc.qam.utils.EmailObject;
 

 
@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    JavaMailSender mailSender;
 
    @Override
    public boolean sendEmail(EmailObject emailObject) {
 
       MimeMessagePreparator preparator = getMessagePreparator(emailObject);
 
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }
 
    private MimeMessagePreparator getMessagePreparator(EmailObject emailObject) {
 
        MimeMessagePreparator preparator = null;
        
        String emailBody = "";
        
        if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_SC_CREATE)) {
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>> \nScorecard created successfully. \n View Link: <<LINK>> \nThanks Admin";        	
        	
        } else  if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_SC_UPDATE)) {
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>> \nScorecard updated successfully. \n View Link: <<LINK>> \nThanks Admin";
           
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_CSRLIST)) {
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>> \nCSR List uploaded successfully. \n Thanks Admin";
           
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_CREATE)) {
        	
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>>, Status:<<STATUS>> \nRebuttal created successfully. \n Thanks Admin";
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_UPDATE)) {
        	
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>>, Status:<<STATUS>> \nRebuttal updated successfully. \n Thanks Admin";
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_UM_CREATE_ADMIN_EMAIL)) {
        	
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>>, Role: <<ROLE>> \nUser created successfully. \n Thanks Admin";
            
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_UM_CREATE_USER_EMAIL)) {
        	
        	emailBody = "Following user has been created with, \nUsername: <<USERNAME>>, Password:<<system generated password>> \n"
        			+ "Link to RAD Application:<<LINK>>.\n" + 
        			" Thanks Admin";
            
        }
        
        if (emailBody.contains("<<MAC_NAME>>") && emailObject.getMacName() !=null) {
        	emailBody = emailBody.replace("<<MAC_NAME>>", emailObject.getMacName());
        }
        
        if (emailBody.contains("<<JURIS_NAME>>") && emailObject.getJurisidctionName() !=null) {
        	emailBody = emailBody.replace("<<JURIS_NAME>>", emailObject.getJurisidctionName());
        }
        
        if (emailBody.contains("<<LINK>>") && emailObject.getLink() !=null) {
        	emailBody = emailBody.replace("<<LINK>>", emailObject.getLink());
        }
        if (emailBody.contains("<<ROLE>>")  && emailObject.getRole() !=null) {
        	emailBody = emailBody.replace("<<ROLE>>", emailObject.getRole());
        }
        if (emailBody.contains("<<USERNAME>>")  && emailObject.getUsername() !=null) {
        	emailBody = emailBody.replace("<<USERNAME>>", emailObject.getUsername());
        }
        if (emailBody.contains("<<PASSWORD>>")  && emailObject.getPassword() !=null) {
        	emailBody = emailBody.replace("<<PASSWORD>>", emailObject.getPassword());
        }
        
        if (emailBody.contains("<<STATUS>>")  && emailObject.getStatus() !=null) {
        	emailBody = emailBody.replace("<<STATUS>>", emailObject.getStatus());
        }
        	
        	
        
        final String finalEmailBOdy = emailBody;
        preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	
            	InternetAddress[] iAdressArray = InternetAddress.parse(emailObject.getToEmail());
                mimeMessage.setFrom(emailObject.getFromEmail());
                mimeMessage.setRecipients(Message.RecipientType.TO,
                		iAdressArray);
                
                if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_SC_CREATE)) {
                	
                	mimeMessage.setText(finalEmailBOdy );
                    mimeMessage.setSubject("New Scorecard Created");
                } else  if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_SC_UPDATE)) {
                	mimeMessage.setText(finalEmailBOdy );
                    mimeMessage.setSubject("Scorecard Updated");
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_CSRLIST)) {
                	mimeMessage.setText(finalEmailBOdy );
                    mimeMessage.setSubject("CSR List Uploaded Succesfully");
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_CREATE)) {
                	mimeMessage.setText(finalEmailBOdy );
                    mimeMessage.setSubject("Rebuttal Created Successfully");
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_UPDATE)) {
                	mimeMessage.setText(finalEmailBOdy );
                    mimeMessage.setSubject("Rebuttal Updated Successfully");
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_UM_CREATE_ADMIN_EMAIL)) {
                	mimeMessage.setText(finalEmailBOdy );
                    mimeMessage.setSubject("User Created Succesffully");
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_UM_CREATE_USER_EMAIL)) {
                	mimeMessage.setText(finalEmailBOdy );
                    mimeMessage.setSubject("User Created Succesffully");
                }
                
            }
        };
        return preparator;
    }
 
}
