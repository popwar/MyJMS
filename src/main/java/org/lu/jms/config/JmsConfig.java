package org.lu.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.lu.jms.service.ResumeMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@ComponentScan(basePackages={"org.lu.jms.service","org.lu.jms.base"})
public class JmsConfig {

	private static final String BROKER_URL = "tcp://localhost:61616";
	private static final String RESUME_QUEUE = "resumeQueue";

	@Bean(name = { "connectionFactory" })
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(BROKER_URL);
		return connectionFactory;
	}

	@Bean(name = { "resumeQueue" })
	public ActiveMQQueue resumeQueue() {
		ActiveMQQueue resumeQueue = new ActiveMQQueue(RESUME_QUEUE);
		return resumeQueue;
	}

	@Bean(name = { "jmsTemplate" })
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestination(resumeQueue());
		jmsTemplate.setMessageConverter(new MarshallingMessageConverter());
		return jmsTemplate;
	}
	
	@Bean(name = { "resumejmsContainer" })
	public DefaultMessageListenerContainer resumejmsContainer(){
		DefaultMessageListenerContainer resumejmsContainer = new DefaultMessageListenerContainer();
		resumejmsContainer.setConnectionFactory(connectionFactory());
		resumejmsContainer.setDestination(resumeQueue());
		resumejmsContainer.setMessageListener(new ResumeMessageListener());
		resumejmsContainer.setSessionTransacted(true);
		return resumejmsContainer;
	}

}
