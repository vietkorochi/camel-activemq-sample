package com.iamvtn.jms;

import java.util.UUID;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.stereotype.Component;

import com.iamvtn.cxf.log.SLF4JLoggerProxy;

@Component
public class RequestGateway {

	private static final class ProducerConsumer implements SessionCallback<Message> {

		private final String msg;

		private final DestinationResolver destinationResolver;

		private final String queue;

		private final Integer timeout;

		public ProducerConsumer(final String msg, String queue, Integer timeout, final DestinationResolver destinationResolver) {
			this.msg = msg;
			this.queue = queue;
			this.timeout = timeout;
			this.destinationResolver = destinationResolver;
		}

		@Override
		public Message doInJms(final Session session) throws JMSException {
			MessageConsumer consumer = null;
			MessageProducer producer = null;
			String correlationId = "";

			try {
				correlationId = UUID.randomUUID().toString();

				final Destination requestQueue = destinationResolver.resolveDestinationName(session, queue + "-in", false);
				final Destination replyQueue = destinationResolver.resolveDestinationName(session, queue + "-out?consumer.prefetchSize=100000", false);

				// Create the consumer first!
				consumer = session.createConsumer(replyQueue, "JMSCorrelationID = '" + correlationId + "'");

				//Create text message from session
				final TextMessage textMessage = session.createTextMessage(msg);

				//Set Correlation ID for text message
				textMessage.setJMSCorrelationID(correlationId);

				// Send the request second!
				producer = session.createProducer(requestQueue);
				//writeLog("createProducer MSGCorrelationID ", correlationId, startTime, endTime);

				//The effect of this difference is that persistent messaging is usually slower than non-persistent delivery
				//But when use non-persistent the messages will survive a broker restart and persistent is not.
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
				//	producer.setDeliveryMode(DeliveryMode.PERSISTENT);

				//The messages sending only live in 5's
				//producer.setTimeToLive(TIMEOUT);

				//Send message to queue
				producer.send(requestQueue, textMessage);
				//writeLog("send MSGCorrelationID ", correlationId, startTime, endTime);

				// Block on receiving the response with a timeout
				Message msgReplied = consumer.receive(timeout);

				return msgReplied;
			} finally {
				try {
					// Don't forget to close your resources
					JmsUtils.closeMessageConsumer(consumer);
					JmsUtils.closeMessageProducer(producer);
				} catch (Exception e) {
					SLF4JLoggerProxy.error(this, e);
				}
			}
		}
	}

	private final JmsTemplate jmsTemplate;

	@Autowired
	public RequestGateway(final JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public String request(final String msg, String queue, String timeout) throws JMSException {
		SLF4JLoggerProxy.debug(this, "Received: " + msg);
		Integer timeoutInt = Integer.parseInt(timeout);
		// Must pass true as the second param to start the connection
		TextMessage msgReplied = (TextMessage) jmsTemplate.execute(new ProducerConsumer(msg, queue, timeoutInt, jmsTemplate.getDestinationResolver()), true);

		String result = null;
		if (msgReplied != null) {
			result = msgReplied.getText();
		}

		SLF4JLoggerProxy.debug(this, "Replied: " + msgReplied);

		return result;
	}
}