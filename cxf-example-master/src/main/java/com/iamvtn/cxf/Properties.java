package com.iamvtn.cxf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

	@Value("${iamvtn.activemq.queue.in}")
	private String queueIn;
	
	@Value("${iamvtn.activemq.queue.out}")
	private String queueOut;


	@Value("${iamvtn.message.timeout}")
	private String timeout;
	
	@Value("${iamvtn.activemq.queue}")
	private String queue;

	/**
	 * @return the queue
	 */
	public String getQueue() {
		return queue;
	}


	/**
	 * @return the queueIn
	 */
	public String getQueueIn() {
		return queueIn;
	}


	/**
	 * @return the queueOut
	 */
	public String getQueueOut() {
		return queueOut;
	}


	/**
	 * @return the timeout
	 */
	public String getTimeout() {
		return timeout;
	}
	
	
}
