package org.lu;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lu.jms.base.MessageSender;
import org.lu.jms.base.MessageSender2;
import org.lu.jms.config.JmsConfig;
import org.lu.jms.testEntity.Address;
import org.lu.jms.testEntity.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration("classpath:org/lu/jms-config.xml")
@ContextConfiguration(classes = { JmsConfig.class })
public class ProducerConsumerTest {

	@Autowired
	private MessageSender<Applicant> messageSender;

	@Autowired
	private MessageSender2<Applicant> messageSender2;

	@Resource(name = "resumeQueue")
	private Destination destination;
	
	private Applicant applicant;
	
	@Before
	public void initData(){
		applicant = new Applicant();
		Address address = new Address();
		address.setNumber(12);
		address.setStreetName("aa st");
		address.setSuburbName("aaa");
		applicant.setName("Tom");
		applicant.setAge(30);
		applicant.setAdress(address);
	}
	
	@Test
	public void testSend() {
			messageSender.sendMessage(applicant, destination);
	}

	
	public void testSendObject() {
		messageSender2.sendMessage(destination, applicant);
	}
}
