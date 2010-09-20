package com.cstock.scoders.utils;

import org.apache.commons.lang.StringUtils;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class TwitterAccountManager {
	
	AccountManager manager=null;
	
	public void createAccount(String username, String password, String confirmPassword){
		boolean passwordEqual=false;
		
		passwordEqual=StringUtils.equals(password, confirmPassword);
		XMPPConnection adminConnection =null;
		try{
			if (passwordEqual){
				adminConnection = getAdminConnection();
				manager.createAccount(username, confirmPassword);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			adminConnection.disconnect();
		}
		
	}
	
	private XMPPConnection getAdminConnection() throws XMPPException{
		XMPPConnection connection=ConnectionManager.getConnection("admin", "orange", Constants.HOSTNAME);
		return connection;
		
	}
	

}
