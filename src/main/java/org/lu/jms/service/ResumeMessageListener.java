package org.lu.jms.service;

import org.lu.jms.base.ConsumerMessageListener;
import org.springframework.stereotype.Service;

@Service
public class ResumeMessageListener extends ConsumerMessageListener<String> {

	@Override
	protected Class<String> getClazz() {
		return String.class;
	}

	@Override
	protected void process(String t) {
	}
}
