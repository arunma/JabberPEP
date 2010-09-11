package com.cstock.scoders;

import javax.net.ssl.SSLSocketFactory;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class OldPublisher {

	/**
	 * @param args
	 */
	static String hostname = "arun-laptop";

	private XMPPConnection connect(String jid, String password)
			throws XMPPException {
		ConnectionConfiguration configuration = new ConnectionConfiguration(hostname, 5222);
       // configuration.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
       // configuration.setSocketFactory(new DummySSLSocketFactory());
        //configuration.setSocketFactory(SSLSocketFactory.getDefault());

		 //configuration.setCompressionEnabled(true);
		 configuration.setSASLAuthenticationEnabled(true);
		 //configuration.setExpiredCertificatesCheckEnabled(false);
		configuration.setSelfSignedCertificateEnabled(true);
		//SecurityMode mode=configuration.getSecurityMode();
		//configuration.setSecurityMode(SecurityMode.disabled);
		//System.out.println(mode);
		XMPPConnection connection = new XMPPConnection(configuration);
		//XMPPConnection connection = new XMPPConnection(hostname);
		//jid="jid"+"@"+hostname;
		
		SASLAuthentication.supportSASLMechanism("PLAIN", 0);
		connection.connect();

		connection.login(jid, password, "Smack");
		System.out.println("Connected");
		return connection;
	}

	public static void main(String[] args) {

		OldPublisher publisher=new OldPublisher();
		try {
			XMPPConnection connection=publisher.connect("arun","arun");
			System.out.println("connection:"+connection);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		//holdOn();
		
	}
	


}
