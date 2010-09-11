package com.cstock.scoders;

import org.jivesoftware.smackx.packet.PEPItem;

public class TweetItem extends PEPItem {

	private final String id;
	private Tweet tweet;
	public Tweet getTweet() {
		return tweet;
	}


	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}


	public TweetItem(String i, Tweet tw) {
		super(i);
		id=i;
		this.tweet=tw;
	}

	
	public String getId() {
		return id;
	}

	@Override
	public String getItemDetailsXML() {
		System.out.println("Tweet...."+tweet);
		return (tweet!=null?tweet.toXML():"<tweet/>");
	}

	@Override
	public String getNode() {
		return Tweet.NAMESPACE;
	}

}
