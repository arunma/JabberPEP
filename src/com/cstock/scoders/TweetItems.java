package com.cstock.scoders;

import org.jivesoftware.smackx.packet.PEPEvent;

public class TweetItems extends PEPEvent {

	private TweetItem tweetItem;
	public TweetItems(TweetItem ti) {
		this.tweetItem=ti;
	}

	public TweetItem getTweetItem() {
		return tweetItem;
	}

	 @Override
	   public String getNamespace() {
	      return ("http://jabber.org/protocol/pubsub#event");
	   }
	 
	public String getItemDetailsXML() {
		System.out.println("TweetItem...."+tweetItem);
		return (tweetItem.toXML());
	}

	public String getNode() {
		return Tweet.NAMESPACE;
	}

	@Override
	public String toXML() {
		StringBuilder builder=new StringBuilder("<event xmlns=\"");
		builder.append(getNamespace()).append("\"><items node=\">");
		builder.append(getNode()).append("\">");
		builder.append(getItemDetailsXML());
		builder.append("</items></event>");
		System.out.println("Constructed String is ...."+builder.toString());
		return builder.toString();
		
		
		
		
		
		
	}
}
