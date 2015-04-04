package org.lu.jms.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class MessageSender<T> {
	private static final Logger LOG = Logger.getLogger(MessageSender.class);

	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	public void sendMessage(final T t, final Destination destination) {

		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				final String jmsMsg = t instanceof String ? t.toString()
						: marshal(t);
				return session.createTextMessage(jmsMsg);
			}
		});
	}

	protected String marshal(T t) {
		JaxbBinder jb = new JaxbBinder(t.getClass());
		return jb.toXml(t);
	}

	private void sendMessageConcurrently(T t, Destination destination) {
		ExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.execute(new ProcessMessage(t, destination));
	}

	private class ProcessMessage implements Runnable {
		private T t;
		private Destination destination;

		ProcessMessage(T t, Destination destination) {
			this.t = t;
			this.destination = destination;
		}

		@Override
		public void run() {
			try {
				final String jmsMsg = t instanceof String ? t.toString()
						: marshal(t);
				LOG.info("sending JMS message: " + jmsMsg);
				jmsTemplate.send(destination, new MessageCreator() {
					public Message createMessage(Session session)
							throws JMSException {
						TextMessage msg = session.createTextMessage();
						msg.setText(jmsMsg);
						return msg;
					}
				});
			} catch (Exception e) {
				LOG.equals(e.getMessage());
				LOG.error(e, e.fillInStackTrace());
			}
		}
	}
}
