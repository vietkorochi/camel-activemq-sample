package com.iamvtn.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.iamvtn.cxf.log.SLF4JLoggerProxy;


@Component
public class ResponseProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String msg = exchange.getIn().getBody(String.class);
		
		SLF4JLoggerProxy.info(this, "Received: " + msg);
		msg = msg + " is modified";
		SLF4JLoggerProxy.info(this, "Modified: " + msg);
		
		exchange.getIn().setBody(msg);
	}

}
