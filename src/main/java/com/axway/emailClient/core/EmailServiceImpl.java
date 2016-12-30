package com.axway.emailClient.core;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.axway.emailClient.config.schema.Attachment;
import com.axway.emailClient.config.schema.EmailConfigSchema;

import javax.mail.internet.MimeMessage;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmailServiceImpl implements EmailService {



	@Autowired
    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
 
	public void sendEmail(final EmailConfigSchema config) {
	
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setTo(EmailHelper.getToList(config.getSendTo()));
	             message.setBcc(EmailHelper.getBCCList(config.getBccTo()));
	             message.setCc(EmailHelper.getCCList(config.getCcTo()));
			     message.setFrom(EmailHelper.getFrom(config.getFrom()));
			     message.setSubject(config.getSubject());
			     message.setSentDate(new Date());
			     String text = VelocityEngineUtils.mergeTemplateIntoString(
			        velocityEngine, config.getTemplate().getLocation(), "UTF-8",EmailHelper.getTemplateParameters(config.getTemplate().getTemplateParameters()));
			     message.setText(text, true);
			     for(Attachment attachment: config.getAttachments())
			     {
			    	 if(attachment.getName()==null) throw new IllegalArgumentException("Attachment with description "+ attachment.toString() + " cannot have null value for \"name\"");
			    	 if(attachment.getFileLocation()==null) throw new IllegalArgumentException("Attachment with description "+ attachment.toString() + "Cannot have null value for \"fileLocation\"");
					    
			    	 message.addAttachment(attachment.getName(), new File(attachment.getFileLocation()));
			     }

			}
		};
			
		       mailSender.send(preparator);
	}
    
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	

}
