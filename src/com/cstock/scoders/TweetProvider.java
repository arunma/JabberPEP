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

	

	public void processText(XmlPullParser xpp) throws XmlPullParserException {
		char ch[] = xpp.getTextCharacters(holderForStartAndLength);
		System.out.println("Char STRING:::"+new String(ch));
		int start = holderForStartAndLength[0];
		int length = holderForStartAndLength[1];
		System.out.print("Characters:    \"");
		for (int i = start; i < start + length; i++) {
			switch (ch[i]) {
			case '\\':
				System.out.print("\\\\");
				break;
			case '"':
				System.out.print("\\\"");
				break;
			case '\n':
				System.out.print("\\n");
				break;
			case '\r':
				System.out.print("\\r");
				break;
			case '\t':
				System.out.print("\\t");
				break;
			default:
				System.out.print(ch[i]);
				break;
			}
		}
		System.out.print("\"\n");
	}

}
