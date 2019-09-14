package com.archsystemsinc.qam.service.mail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.archsystemsinc.qam.sec.util.GenericConstants;
import com.archsystemsinc.qam.utils.EmailObject;
 

 
@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    JavaMailSender mailSender;
    
    @Value("${radui.environment}")
    String radEnvironment;
 
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
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>> <br>Scorecard created successfully. <br> View Link: <<LINK>> <br>Thanks Admin";        	
        	
        } else  if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_SC_UPDATE)) {
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>> <br>Scorecard updated successfully. <br> View Link: <<LINK>> <br>Thanks Admin";
           
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_CSRLIST)) {
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>> <br>CSR List uploaded successfully. <br> Thanks Admin";
           
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_CREATE)) {
        	
        	/*emailBody = "Dear User,<br>"+
	        	"A rebuttal was added to the CRAD Portal.<br>"+
	        	"Date Created: <<DATE_CREATED>><br>"+
	        	"MAC Name: <<MAC_NAME>><br>"+
	        	"Jurisdiction: <<JURIS_NAME>><br>"+
	        	"PCC Location: <<PCC_LOCATION>><br>"+
	        	"Reporting Period: <<REPORTING_PERIOD>><br>"+
	        	"Status: <<STATUS>><br>"+
	        	"Result: <<REBUTTAL_RESULT>><br><br>"+        	
	        	"Please login to the portal at <<LINK>> view details of the Rebuttal, Please contact  qamadmin@archsystemsinc.com  should you have any questions.<br>"+
	        	"Thank you,<br>" +
	        	"CRAD Support Team";        */
        	emailBody = 
        	"This is to inform you that a Rebuttal was added to the CRAD. MAC User would receive a response to the submitted Rebuttal in five business days.<br>"+
        	"Please login to the <a href='<<LINK>>'>CRAD</a> to view details of the Rebuttal. Please contact <a href='mailto:qamadmin@archsystemsinc.com'>QAM Mailbox</a> if you have any questions.<br>"+
        	"<br>Thank you,<br>"+
        	"CRAD Helpdesk";
        	
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_UPDATE_MU)) {
        	
        	emailBody = 
			"This is to inform you that a Rebuttal was updated to the CRAD. MAC User would receive a response to the updated Rebuttal in five business days.<br>"+        			
        	"Please login to the <a href='<<LINK>>'>CRAD</a> to view details of the Rebuttal. Please contact <a href='mailto:qamadmin@archsystemsinc.com'>QAM Mailbox</a> if you have any questions.<br>"+
        	"<br>Thank you,<br>"+
        	"CRAD Helpdesk";
        	
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_UPDATE_CRAD)) {
        	
        	emailBody = 
			"This is to inform you that a Rebuttal was updated to the CRAD. MAC User has three business days to respond.<br>"+
		    "Please login to the <a href='<<LINK>>'>CRAD</a> to view details of the Rebuttal. Please contact <a href='mailto:qamadmin@archsystemsinc.com'>QAM Mailbox</a> if you have any questions.<br>"+
		    "<br>Thank you,<br>"+
		    "CRAD Helpdesk";
        	
        }else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_UM_CREATE_ADMIN_EMAIL)) {
        	
        	emailBody = "MAC Name: <<MAC_NAME>>, Jurisdiction: <<JURIS_NAME>>, Role: <<ROLE>> <br>User created successfully. <br> Thanks Admin";
            
        } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_UM_CREATE_USER_EMAIL)) {
        	
        	emailBody = "Following user has been created with, <br>Username: <<USERNAME>>, Password:<<system generated password>> <br>"
        			+ "Link to RAD Application:<<LINK>>.<br>" + 
        			" Thanks Admin";            
        }
        
        if (emailBody.contains("<<DATE_CREATED>>") && emailObject.getDateCreated() !=null) {
        	emailBody = emailBody.replace("<<DATE_CREATED>>", emailObject.getDateCreated());
        }
        
        if (emailBody.contains("<<PCC_LOCATION>>") && emailObject.getPccLocation() !=null) {
        	emailBody = emailBody.replace("<<PCC_LOCATION>>", emailObject.getPccLocation());
        }
        
        if (emailBody.contains("<<REPORTING_PERIOD>>") && emailObject.getReportingPeriod() !=null) {
        	emailBody = emailBody.replace("<<REPORTING_PERIOD>>", emailObject.getReportingPeriod());
        }
        
        if (emailBody.contains("<<STATUS>>") && emailObject.getStatus() !=null) {
        	emailBody = emailBody.replace("<<STATUS>>", emailObject.getStatus());
        }
        
        if (emailBody.contains("<<REBUTTAL_RESULT>>") && emailObject.getRebttalResult() !=null) {
        	emailBody = emailBody.replace("<<REBUTTAL_RESULT>>", emailObject.getRebttalResult());
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
        
        final String finalEmailBody = emailBody;
        preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	
            	InternetAddress[] iAdressArrayToList = InternetAddress.parse(emailObject.getToEmail());
            	InternetAddress[] iAdressArrayToBccList = InternetAddress.parse(emailObject.getToBccEmail());
            	
                mimeMessage.setFrom(emailObject.getFromEmail());
                mimeMessage.setRecipients(Message.RecipientType.TO, iAdressArrayToList);
                
                mimeMessage.setRecipients(Message.RecipientType.BCC, iAdressArrayToBccList);
                
                StringBuffer emailSubject = new StringBuffer(radEnvironment);
                               
                if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_SC_CREATE)) {                	
                	
                	emailSubject.append("New Scorecard Created");
                    
                } else  if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_SC_UPDATE)) {
                	
                	emailSubject.append("New Scorecard Updated");
                    
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_CSRLIST)) {
                	
                	emailSubject.append("CSR List Uploaded Succesfully");
                   
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_CREATE)) {
                	
                	emailSubject.append("Rebuttal Created Successfully By MAC User");
                    
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_UPDATE_MU)) {
                	
                	emailSubject.append("Rebuttal Updated Successfully By MAC User");
                    
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_UPDATE_CRAD)) {
                	
                	emailSubject.append("Rebuttal Updated Successfully By CRAD");
                    
                }else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_UM_CREATE_ADMIN_EMAIL)) {
                	
                	emailSubject.append("User Created Succesffully");
                   
                } else if (emailObject.getEmailType().equalsIgnoreCase(GenericConstants.EMAIL_TYPE_UM_CREATE_USER_EMAIL)) {
                	
                	emailSubject.append("User Created Succesffully");
                    
                }
                mimeMessage.setText(finalEmailBody , "UTF-8", "html");
                mimeMessage.setSubject(emailSubject.toString());
            }
        };
        return preparator;
    }
 
}
