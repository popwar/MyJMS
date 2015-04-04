package org.lu.jms.testEntity;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"name","age","adress"})
//@XmlAccessorType(XmlAccessType.FIELD)
public class Applicant{
	protected String name;
	protected int age;
	protected Address adress;

	public Applicant() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

//	public Object clone() throws CloneNotSupportedException {
//		Applicant apllicant = (Applicant) super.clone();
//		apllicant.setAdress((Address) apllicant.getAdress().clone());
//		return apllicant;
//	}
}
