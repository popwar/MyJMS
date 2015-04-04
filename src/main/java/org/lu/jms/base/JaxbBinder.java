package org.lu.jms.base;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.lu.jms.SystemException;

public class JaxbBinder {
	// thread-safe Context.
	private JAXBContext jaxbContext;

	/**
	 * @param types
	 *            constructor for the entity need to be serialized.
	 */
	public JaxbBinder(Class<?>... types) {
		try {
			jaxbContext = JAXBContext.newInstance(types);
		} catch (JAXBException e) {
			throw new SystemException("JAXBContext new instantce error.", e);
		}
	}

	/**
	 * Java Object->Xml.
	 */
	public String toXml(Object root) {
		try {
			StringWriter writer = new StringWriter();
			createMarshaller("UTF-8", null, true).marshal(root, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw new SystemException("JAXB marsh to xml error.", e);
		}
	}

	/**
	 * Java Object->Xml.
	 */
	public String toXml(Object root, String encoding) {
		try {
			StringWriter writer = new StringWriter();
			createMarshaller(encoding, null, false).marshal(root, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw new SystemException("JAXB marsh to xml with encoding error.",
					e);
		}
	}

	/**
	 * Java Object->Xml.
	 */
	public String toXml(Object root, String encoding, String declaration) {
		try {
			StringWriter writer = new StringWriter();
			writer.append(declaration);
			createMarshaller(encoding, declaration, false)
					.marshal(root, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw new SystemException(
					"JAXB marsh to xml with encoding and declaration error.", e);
		}
	}

	/**
	 * Xml->Java Object.
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromXml(String xml) {
		try {
			StringReader reader = new StringReader(xml);
			return (T) createUnmarshaller().unmarshal(reader);
		} catch (JAXBException e) {
			throw new SystemException("JAXB unmarsh from xml error.", e);
		}
	}

	/**
	 * Create Marshaller, set encoding(could be Null), set declaration(could be
	 * Null).
	 */
	public Marshaller createMarshaller(String encoding, String declaration,
			Boolean formatted) {
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();

			// format XML?
			if (formatted) {
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
						Boolean.TRUE);
			}

			// set encoding
			if (encoding != null && encoding.length() > 0) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}

			// set XML declaration
			if (declaration != null && declaration.length() > 0) {
				marshaller.setProperty("com.sun.xml.bind.xmlDeclaration",
						Boolean.FALSE);
			}
			return marshaller;
		} catch (JAXBException e) {
			throw new SystemException("JAXB create Marshaller error.", e);
		}
	}

	/**
	 * Create UnMarshaller.
	 */
	public Unmarshaller createUnmarshaller() {
		try {
			return jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new SystemException("JAXB create Unmarshaller error.", e);
		}
	}

}
