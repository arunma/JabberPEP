package com.cstock.scoders;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.PEPManager;

public class PEPManager1 {

	static String hostname = "arun-laptop";
	public static void main(String[] args) throws XMPPException {

		PEPManager pepManager = new PEPManager(connect("test","test"));
/*
		pepManager.addPEPListener(new PEPListener() {
			public void eventReceived(String inFrom, PEPEvent inEvent) {
				System.out.println("Event received: " + inEvent);
			}
		});*/
		

		 /*  SimplePayload payload = new SimplePayload("event", "pubsub:test:event",
		            "" +
		            "<title>New blog post</title>" +
		            "<desc>Stalker published new blog post</desc>" +
		            "");
		 
		 
		        // you could use publish() for asynchronous call
		        
		PEPProvider pepProvider = new PEPProvider();
		// pepProvider.registerPEPParserExtension("http://jabber.org/protocol/event",new EventProvider());*/
/*		ProviderManager.getInstance().addExtensionProvider("event",
				"http://jabber.org/protocol/pubsub#event", pepProvider);
*/
		Tweet tweet=new Tweet();
		tweet.setMessage("Tweeting, the first time. Jesus Christ");
		TweetItem item=new TweetItem(StringUtils.randomString(5),tweet);
		System.out.println("TWeetItem"+item.getItemDetailsXML());
		System.out.println("Tweet"+item.getTweet());
		System.out.println("Tweet message"+item.getTweet().getMessage());
		pepManager.publish(item);
		System.out.println("PUblished.....");
		holdOn();
	
		/*ConfigureForm form = new ConfigureForm(FormType.submit);
		form.setPersistentItems(false);
		form.setDeliverPayloads(true);
		form.setAccessModel(AccessModel.open);

		PubSubManager manager 
		      = new PubSubManager(connect("test","test"), "pubsub.communitivity.com");
		Node myNode = manager.createNode("http://jabber.org/protocol/geoloc", form);

		StringBuilder body = new StringBuilder(); //ws for readability
		body.append("<geoloc xmlns='http://jabber.org/protocol/geoloc' xml:lang='en'>");
		body.append("   <country>Italy</country>");
		body.append("   <lat>45.44</lat>");
		body.append("   <locality>Venice</locality>");
		body.append("   <lon>12.33</lon>");
		body.append("   <accuracy>20</accuracy>");
		body.append("</geoloc>");

		SimplePayload payload = new SimplePayload(
		                              "geoloc",
		                              "http://jabber.org/protocol/geoloc", 
		                              body.toString());
		String itemId = "zz234";
		Item item = new Item(itemId, payload);

		// Required to recieve the events being published
		//myNode.addItemEventListener(myEventHandler);

		// Publish item
		myNode.publish(item);*/

	}

	private static XMPPConnection connect(String jid, String password)
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
