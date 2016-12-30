package com.axway.emailClient.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


import com.axway.emailClient.config.schema.TemplateParameter;

public class EmailHelper {

	public static InternetAddress[] getToList(List<String> emails) throws AddressException
	{
		return convertListToInternetAddress(emails);
		
	}
	
	public static InternetAddress[] getCCList(List<String> emails) throws AddressException
	{
		return convertListToInternetAddress(emails);
		
	}
	
	public static InternetAddress[] getBCCList(List<String> emails) throws AddressException
	{
		return convertListToInternetAddress(emails);
		
	}
	
	public static InternetAddress getFrom(String email) throws AddressException
	{
		return convertStringToInternetAddress(email);
		
	}
	
	public static Map<String,Object> getTemplateParameters(List<TemplateParameter> emailtemplateParameters) throws IllegalArgumentException
	{
	
		if(emailtemplateParameters.size()<=0) return null;
		Map<String,Object> model=new HashMap<String, Object>(emailtemplateParameters.size());
		for(TemplateParameter param:emailtemplateParameters)
		{
			if(param.getValue().length()>0&&param.getName().trim().length()<=0) throw new IllegalArgumentException("Template Configuration "+param.toString()+" cannot have null as a value for \"name\" ");
			model.put(param.getName(),param.getValue());
		}
		return model;
		
		
	}
	
	private static InternetAddress[] convertListToInternetAddress(List<String> emails) throws AddressException
	{
		//if(emails.size()<=0) return new InternetAddress[0];
		InternetAddress[] toList= new InternetAddress[emails.size()];
		int i=0;
		for(String email:emails)
		{
			toList[i]=new InternetAddress(email);
			i++;
		}
		return toList;
	}
	
	private static InternetAddress convertStringToInternetAddress(String email) throws AddressException
	{
		return new InternetAddress(email);
		}
		

}
