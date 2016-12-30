package com.axway.emailClient.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.axway.emailClient.core.EmailServiceController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;


import javax.mail.MessagingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-spring-context-email.xml") // the Spring context file
public class EmailServiceControllerTest {

	

	
	@Autowired
	EmailServiceController emailServiceController;
	


	
	@Test
	public void testSendNotificationEmail() throws MessagingException, JsonParseException, JsonMappingException, IOException {

		
		String testdata1= readFile("C:\\Axway\\podcastpedia-develop\\emailClient\\src\\test\\resources\\testdata.json");
		try{
			System.out.println(testdata1);
			emailServiceController.sendEmail(testdata1);
			
			//Email.main(new String[]{testdata1});
		}catch(Exception e)
		{
			System.out.println("Error Description : "+e.getMessage());
		}
		
	}
	

	private String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
	
	
}
