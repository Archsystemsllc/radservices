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
 

 
@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    JavaMailSender mailSender;
 
    @Override
    public boolean sendEmail(String emailType, String fromEmail, String toEmail) {
 
       MimeMessagePreparator preparator = getMessagePreparator(emailType, fromEmail, toEmail);
 
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }
 
    private MimeMessagePreparator getMessagePreparator(String emailType, String fromEmail, String toEmail) {
 
        MimeMessagePreparator preparator = null;
        
        preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	
            	InternetAddress[] iAdressArray = InternetAddress.parse(toEmail);
                mimeMessage.setFrom(fromEmail);
                mimeMessage.setRecipients(Message.RecipientType.TO,
                		iAdressArray);
                
                if (emailType.equalsIgnoreCase(GenericConstants.EMAIL_TYPE_SC_CREATE)) {
                	mimeMessage.setText("Scorecard created successfully for <<MAC Name>>. << Link>>\r\n" );
                    mimeMessage.setSubject("New Scorecard Created");
                } else  if (emailType.equalsIgnoreCase(GenericConstants.EMAIL_TYPE_SC_UPDATE)) {
                	mimeMessage.setText("Score card updated successfully for <<MAC Name>><<Link>>\r\n");
                    mimeMessage.setSubject("Scorecard Updated");
                } else if (emailType.equalsIgnoreCase(GenericConstants.EMAIL_TYPE_CSRLIST)) {
                	mimeMessage.setText("CSR list uploaded successfully for <<MAC>>\r\n" );
                    mimeMessage.setSubject("CSR List Uploaded Succesfully");
                } else if (emailType.equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_CREATE)) {
                	mimeMessage.setText("Rebuttal created successfully.<< Link>>\r\n" );
                    mimeMessage.setSubject("Rebuttal Created Successfully");
                } else if (emailType.equalsIgnoreCase(GenericConstants.EMAIL_TYPE_RB_UPDATE)) {
                	mimeMessage.setText("Rebuttal updated successfully.<<link>>\r\n" );
                    mimeMessage.setSubject("Rebuttal Updated Successfully");
                } else if (emailType.equalsIgnoreCase(GenericConstants.EMAIL_TYPE_UM_CREATE)) {
                	mimeMessage.setText("User created successfully.<<link>>\r\n" );
                    mimeMessage.setSubject("User Created Succesffully");
                }
                
            }
        };
        return preparator;
    }
 
}
