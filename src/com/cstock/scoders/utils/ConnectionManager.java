package com.cstock.scoders.utils;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.PEPManager;

public class ConnectionManager {

	public static XMPPConnection getConnection(String jid, String password, String hostname)
			throws XMPPException {
		ConnectionConfiguration configuration = new ConnectionConfiguration(
				hostname, 5222);

		configuration.setSASLAuthenticationEnabled(true);
		configuration.setSelfSignedCertificateEnabled(true);
		XMPPConnection connection = new XMPPConnection(configuration);

		SASLAuthentication.supportSASLMechanism("PLAIN", 0);
		connection.connect();

		connection.login(jid, password, "Smack");
		System.out.println("Connected");
		return connection;
	}
	
	
	
}
