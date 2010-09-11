package com.cstock.scoders;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.provider.PEPProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TweetProvider extends PEPProvider {
	int holderForStartAndLength[] = new int[2];
	@Override
	public PacketExtension parseExtension(XmlPullParser parser)
			throws Exception {

		boolean stop = false;
		int eventType;
		String tagName = null;
		String id = null;
		Tweet tweet = new Tweet();

		while (!stop) {
			eventType = parser.next();
			tagName = parser.getName();
			switch (eventType) {
			case (XmlPullParser.START_TAG):
				if ("item".equals(tagName)) {
					id = parser.getAttributeValue("", "id");
				} else if ("message".equals(tagName)) {
					parser.next();
					tweet.setMessage(parser.getText());
					//tweet.setMessage("Helloooo there");
					//processText(parser);
				}

				break;
			case (XmlPullParser.END_TAG):
				if ("item".equals(tagName)) {
					stop = true;
				}
				break;
			}
		}

		return new TweetItems(new TweetItem(id, tweet));
	}

}
