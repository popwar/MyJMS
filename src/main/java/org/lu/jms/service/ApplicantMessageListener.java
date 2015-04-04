package org.lu.jms.service;

import org.apache.log4j.Logger;
import org.lu.jms.base.ConsumerMessageListener;
import org.lu.jms.testEntity.Applicant;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ApplicantMessageListener extends
		ConsumerMessageListener<Applicant> {

	private static final Logger LOG = Logger
			.getLogger(ApplicantMessageListener.class);

	private Applicant applicant;

	@Override
	protected Class<Applicant> getClazz() {
		return Applicant.class;
	}

	@Override
	protected void process(Applicant applicant) {
		LOG.info("Applicant name: " + applicant.getName());
		this.applicant = applicant;

	}

	public Applicant getApplicant() {
		return applicant;
	}
}
