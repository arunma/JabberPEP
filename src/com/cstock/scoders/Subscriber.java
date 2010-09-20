package com.cstock.scoders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smackx.PEPListener;
import org.jivesoftware.smackx.PEPManager;
import org.jivesoftware.smackx.packet.PEPEvent;
import org.jivesoftware.smackx.provider.PEPProvider;

import com.cstock.scoders.utils.Constants;

public class Subscriber implements PEPListener, Observable{

	//static String hostname = "arun-laptop";
	//static String hostname = "Aruns-MacBook-Pro.local";
	private String message;
	List<Observer> observers=new ArrayList<Observer>();

	public static void main(String[] args) {

		Subscriber subscriber=new Subscriber();
		subscriber.subscribe("admin", "orange", Constants.HOSTNAME);
	
		
	}

	public void subscribe(String username, String password, String server) {

		PEPProvider provider=new PEPProvider();
		provider.registerPEPParserExtension(Tweet.NAMESPACE, new TweetProvider());
		ProviderManager.getInstance().
			addExtensionProvider("event", "http://jabber.org/protocol/pubsub#event", provider);
		
		try {
			PEPManager manager=new PEPManager(connect(username, password, server));
			manager.addPEPListener(this);
			
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//holdOn();
		
	}
	
	public void subscribe(XMPPConnection connection) {

		PEPProvider provider=new PEPProvider();
		provider.registerPEPParserExtension(Tweet.NAMESPACE, new TweetProvider());
		ProviderManager.getInstance().
			addExtensionProvider("event", "http://jabber.org/protocol/pubsub#event", provider);
	
		PEPManager manager=new PEPManager(connection);
		manager.addPEPListener(this);
		
		//holdOn();
		
	}
	public void eventReceived(String from, PEPEvent pepEvent) {

		TweetItems items=(TweetItems)pepEvent;
		String message=items.getTweetItem().getTweet().getMessage();
		System.out.println("Event Received :::");
		System.out.println(message);
		this.message=message;
		notifyObservers();
		System.out.println("Event complete...");
	}
	
	

	private static void holdOn() {
		Object obj = new Object();
		try {
			synchronized (obj) {
				obj.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static XMPPConnection connect(String jid, String password, String hostname)
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
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}
   
	 private void notifyObservers() {
		 for (Observer observer:observers){
			  observer.update( getMessage() );
		 }

   }

}
