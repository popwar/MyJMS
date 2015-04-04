package org.lu.jms.base;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public abstract class ConsumerMessageListener<T> implements MessageListener {
	private static final Logger LOG = Logger
			.getLogger(ConsumerMessageListener.class);

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			LOG.info("Receive JMS message: " + message);
			TextMessage tm = (TextMessage) message;
			String text = null;
			try {
				text = tm.getText();
				LOG.info("processing JMS message: " + text);
				if (isXML(text)) {
					process(unmarshal(text));
				}
				LOG.info("finished process and confirm to the message");
			} catch (JMSException e) {
				LOG.error("failed consume JMS message", e.fillInStackTrace());
				throw new RuntimeException(e);
			}
		}
	}

	protected T unmarshal(String xml) {
		JaxbBinder jb = new JaxbBinder(getClazz());
		return (T) jb.fromXml(xml);
	}

	protected abstract Class<T> getClazz();

	/**
	 * Business logic process
	 * 
	 * @param t
	 */
	protected abstract void process(T t);

	private static boolean isXML(String value) {
		try {
			DocumentHelper.parseText(value);
		} catch (DocumentException e) {
			return false;
		}
		return true;
	}
}
