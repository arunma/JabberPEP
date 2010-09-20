package com.cstock.scoders.utils;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class UserManager {

	public void subscribeTo(String user, String name, XMPPConnection connection) throws XMPPException{
		Roster roster=connection.getRoster();
		
		roster.createEntry(user, name, null);
		//Write subscription info to database
	}
	
	
	public static void main(String[] args) throws XMPPException {
		XMPPConnection connection=ConnectionManager.getConnection("arun", "arun", Constants.HOSTNAME);
		UserManager userManager =new UserManager();
		userManager.subscribeTo("admin@"+Constants.HOSTNAME, "admin", connection);
		
	}
}
