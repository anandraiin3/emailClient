package com.axway.emailClient.core;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.axway.emailClient.config.schema.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*import com.github.fge.jsonschema.exceptions.ProcessingException;*/

public class EmailServiceController {
	
	@Autowired
	private EmailService emailService;

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	

	public void sendEmail(String jsonString ) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper=new ObjectMapper();
		EmailConfigSchema emailConfig;
		
			try {
				emailConfig = objectMapper.readValue(jsonString, EmailConfigSchema.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				
				System.out.println("Incorrect JSON Payload");
				throw e;
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
			
				System.out.println("JSON Payload does not comply with JSON Schema");
				throw e;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				System.out.println("Unable to read either JSON Payload or JSON Schema ");
				throw e;
			}
			sendEmail(emailConfig);
	}
	
	private void sendEmail(EmailConfigSchema config)
	{
		emailService.sendEmail(config);
	}

}
