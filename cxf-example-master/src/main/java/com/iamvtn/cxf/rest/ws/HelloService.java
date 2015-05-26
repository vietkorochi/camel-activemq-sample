package com.iamvtn.cxf.rest.ws;

import javax.jms.JMSException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.iamvtn.cxf.Properties;
import com.iamvtn.cxf.log.SLF4JLoggerProxy;
import com.iamvtn.jms.RequestGateway;

@Path("/helloservice/")
public class HelloService {

	@Autowired
	private RequestGateway requestGateway;
	
	@Autowired
	private Properties properties;
	
	@GET
	@Path("/sendMessage/{message}/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public String sendMessage(@PathParam("message") String message) throws JMSException {
		SLF4JLoggerProxy.debug(this, "----invoking getCustomer, messsage is: " + message);
		
		SLF4JLoggerProxy.debug(this, "Queue name: " + properties.getQueue());
		String result = requestGateway.request(message, properties.getQueue(), properties.getTimeout());

		SLF4JLoggerProxy.debug(this, "Message responce: " + result);
		return result;
	}
}
