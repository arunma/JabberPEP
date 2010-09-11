package com.cstock.scoders;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.PEPManager;

public class Publisher {

//	static String hostname = "arun-laptop";
	static String hostname = "Aruns-MacBook-Pro.local";
	PEPManager pepManager=null;
	
	public void publish(XMPPConnection connection, String message) throws XMPPException {

		Tweet tweet=new Tweet();
		tweet.setMessage(message);
		TweetItem item=new TweetItem(StringUtils.randomString(5),tweet);
		System.out.println("TWeetItem"+item.getItemDetailsXML());
		System.out.println("Tweet  :"+item.getTweet());
		System.out.println("Tweet message"+item.getTweet().getMessage());
		getPEPManager(connection).publish(item);
		System.out.println("PUblished.....");
		//holdOn();
	
	}

	public PEPManager getPEPManager(XMPPConnection connection) throws XMPPException{
		if (pepManager==null){
			pepManager = new PEPManager(connection);
		}
		return pepManager;
	}

	private static void holdOn() {
		Object obj=new Object();
		try {
			synchronized(obj){
				obj.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
