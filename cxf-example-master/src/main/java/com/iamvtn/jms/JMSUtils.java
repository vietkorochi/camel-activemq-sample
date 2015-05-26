package com.iamvtn.jms;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iamvtn.cxf.Properties;

public class JMSUtils {
	@Autowired
	private Properties properties;

	@Autowired
	private ProducerTemplate producerTemplate;
	
	public Boolean sendToQueueIn(String message) {
		producerTemplate.asyncSendBody(properties.getQueueIn(), message);
		
		return true;
	}
}
