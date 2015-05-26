package com.iamvtn.jms;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.iamvtn.cxf.log.SLF4JLoggerProxy;


@Component("jmsTextMessageConverter")
public class JMSTextMessageConverter implements MessageConverter {
	// private static final String BYTES_MESSAGE_CHARSET = "UTF-8";
	public JMSTextMessageConverter() {
	}

	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		if (SLF4JLoggerProxy.isDebugEnabled(this))
			SLF4JLoggerProxy.debug(this, "fromMessage().:" + message);
		if (message instanceof TextMessage) {
			try {
				return ((TextMessage) message).getText();
			} catch (JMSException e) {
				throw new MessageConversionException(
						"fromMessage.JMSException", e);
			}
		} else if (message instanceof BytesMessage) {
			 SLF4JLoggerProxy.error(this, "fromMessage.JMSException.:Not supported.:"
						+ message.toString());
			throw new MessageConversionException(
					"fromMessage.JMSException.:Not supported BytesMessage!");
		} else if (message instanceof ObjectMessage) {
			return ((ObjectMessage) message).getObject().toString();
		} else {
			SLF4JLoggerProxy.error(this, "fromMessage.JMSException.:Not supported.:"
					+ message.toString());
			throw new MessageConversionException(
					"fromMessage.JMSException.:Not supported.:"
							+ message.toString());
		}
	}

	/** Converts from the Object to the JMS text message format */
	public Message toMessage(Object message, Session session)
			throws JMSException, MessageConversionException {
		javax.jms.Message jmsMessage = null;
		if (message instanceof String) {
			jmsMessage = session.createTextMessage((String) message);
		} else {
			if (SLF4JLoggerProxy.isDebugEnabled(this))
				SLF4JLoggerProxy.debug(this, "toMessage.JMSException.:Not supported.:" + message.toString());
			throw new MessageConversionException("toMessage.JMSException.:Not supported.:"+ message.toString());
		}
		if (SLF4JLoggerProxy.isDebugEnabled(this))
			SLF4JLoggerProxy.debug(this, "toMessage().:" + jmsMessage);
		return jmsMessage;
	}

}
