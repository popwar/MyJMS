package org.lu.jms.base;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Service;

@Service
public class MessageSender2<T> {

	@Autowired
	private JmsOperations jmsOperations;

	public void sendMessage(final Destination destination, final T t) {
		jmsOperations.convertAndSend(destination, t);
	}
}
