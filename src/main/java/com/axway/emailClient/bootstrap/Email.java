package com.axway.emailClient.bootstrap;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.axway.emailClient.core.EmailServiceController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class Email {

	@Autowired
	public static EmailServiceController emailServiceController;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if(args.length<2||args.length>3)
		{
			displayUsage();			
			return ;
		}
		FileSystemXmlApplicationContext ctx;
		try
		{
			//ctx=new FileSystemXmlApplicationContext("C:\\Axway\\podcastpedia-develop\\emailClient\\src\\main\\resources\\spring\\spring-context-email.xml");
			ctx=new FileSystemXmlApplicationContext(args[0]);
			emailServiceController=(EmailServiceController) ctx.getBean("emailServiceController");
			
		}
		catch(org.springframework.beans.BeansException exception)
		{
			System.out.println(exception.getLocalizedMessage());
			System.out.println("Please configure the application context correctly @ "+args[0]);
			System.out.println("Spring configuration and SMTP server configuration shoudl be similar to following");
			System.out.println("*****************************************");
			System.out.println(readFile("spring/spring-context-email.xml"));
			System.out.println("*****************************************");
			return;
		}
		
	
		
		try {
			//String testdata=" {\"sendTo\":[\"email1@xyz.com\",\"email4@xyz.com\"],\"ccTo\":[\"email2@xyz.com\"],\"bccTo\":[\"email3@xyz.com\"],\"subject\":\"Welcome to Axway\",\"from\":\"araiserver@xyz.com\",\"attachments\":[{\"name\":\"agastya.jpg\",\"fileLocation\":\"C:/Users/arai/Desktop/agastya.jpg\",\"type\":\"pdf\"},{\"name\":\"agastya2.jpg\",\"fileLocation\":\"C:/Users/arai/Desktop/agastya.jpg\",\"type\":\"pdf\"}],\"template\":{\"name\":\"tem.vm\",\"location\":\"velocity/new_contact_message.vm\",\"templateParameters\":[{\"name\":\"company\",\"value\":\"Axway\"},{\"name\":\"logo\",\"value\":\"An API Compnay\"},{\"name\":\"place\",\"value\":\"Australia\"}]}}";
			Email.emailServiceController.sendEmail(args[1]);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			displayEmailMessageConfigurationDataRequirement(args[1]);
			return;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			displayEmailMessageConfigurationDataRequirement(args[1]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		finally {
			ctx.close();
		}

	}
	private static void displayEmailMessageConfigurationDataRequirement(String args) throws IOException {
		System.out.println("Please configure the json parameters correctly @ "+args);
		System.out.println("Json parameters should comply with following Schema");
		System.out.println("*****************************************");
		System.out.println(readFile("config/emailConfigSchema.json"));
		System.out.println("*****************************************");
	}
	private static void displayUsage() throws IOException {
		System.out.println("You have specified incorrect parameters");
		System.out.println("Usage : java -jar JARFILENAME PATH_TO_SPRING_CONTEXT JSON_STRING_WITH_EMAIL_DETAILS");
		System.out.println("Usage : java -jar emailClient-1.0.0.jar \"C:\\Users\\arai\\Desktop\\spring-context-email.xml\" \"{\"sendTo\":[\"email1@xyz.com\",\"email4@xyz.com\"],\"ccTo\":[\"email2@xyz.com\"],\"bccTo\":[\"email3@xyz.com\"],\"subject\":\"Welcome to Axway\",\"from\":\"araiserver@xyz.com\",\"attachments\":[{\"name\":\"agastya.jpg\",\"fileLocation\":\"C:\\Users\\arai\\Desktop\\agastya.jpg\",\"type\":\"pdf\"},{\"name\":\"agastya2.jpg\",\"fileLocation\":\"C:\\Users\\arai\\Desktop\\agastya.jpg\",\"type\":\"pdf\"}],\"template\":{\"name\":\"tem.vm\",\"location\":\"new_contact_message.vm\",\"templateParameters\":[{\"name\":\"company\",\"value\":\"Axway\"},{\"name\":\"logo\",\"value\":\"An API Compnay\"},{\"name\":\"place\",\"value\":\"Australia\"}]}}\"");
		System.out.println("Example of PATH_TO_SPRING_CONTEXT C:\\Axway\\podcastpedia-develop\\emailClient\\src\\main\\resources\\spring\\spring-context-email.xml");
		System.out.println("Example of JSON_STRING_WITH_EMAIL_DETAILS");
		System.out.println("*****************************************");
		System.out.println(readFile("samples/testdata.json"));
		System.out.println("*****************************************");
	}
	
	
	private static String readFile(String file) throws IOException {
	
		Resource resource = new ClassPathResource(file);
		InputStream stream=resource.getInputStream();
		File tempFile = new File("tmp.txt");
		OutputStream outputStream = new FileOutputStream(tempFile);

				    try {
				        IOUtils.copy(stream, outputStream);
				    } catch (IOException e) {
				        // Handle exception
				    } finally {
				        outputStream.close();
				    }

	
	   BufferedReader reader = new BufferedReader(new FileReader("tmp.txt"));
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
