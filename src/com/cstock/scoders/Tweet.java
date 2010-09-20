package com.cstock.scoders;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name=Tweet.NAME, namespace=Tweet.NAMESPACE)
public class Tweet {

	public static final String NAME="tweet";
	public static final String NAMESPACE="urn:xmpp:tweet:0";
	
	private String message;

	@XmlElement(name="message", namespace=Tweet.NAMESPACE)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toXML(){
	
		String result="<tweet/>";
		
		try {
			JAXBContext context=JAXBContext.newInstance(Tweet.class);
			Marshaller marshaller= context.createMarshaller();
			//do not generated the xml declaration
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			StringWriter sw=new StringWriter();
			marshaller.marshal(this, sw);
			result=sw.toString();
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
