package org.lu.jms.testEntity;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "address")
public class Address {
	protected String streetName;
	protected int number;
	protected String suburbName;

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSuburbName() {
		return suburbName;
	}

	public void setSuburbName(String suburbName) {
		this.suburbName = suburbName;
	}

	// public Object clone() throws CloneNotSupportedException {
	// return super.clone();
	// }
}
