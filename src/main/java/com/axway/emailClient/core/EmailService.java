package com.axway.emailClient.core;

import com.axway.emailClient.config.schema.EmailConfigSchema;

/**
 * Email notification service used in the context of podcast submitting.
 * After a podcast has been submit the Podcastpedia personnel will be informed about it.
 * 
 */
public interface EmailService {

	/**
	 * Send notification with suggested podcast, packed as one line of metadata
	 * 
	 * @param addPodcastFormData
	 */
	
	public void sendEmail(EmailConfigSchema config); 

}
