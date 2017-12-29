package com.lampshadesoftware.ourmessage;

import java.util.ArrayList;

/**
 * Created by danielmccrystal on 12/25/17.
 */


public class Conversation {
	protected MessagesHandler mh;
	private String address;
	public String getAddress() {
		return address;
	}

	private ArrayList<Message> messages;
	public ArrayList<Message> getAllMessages() {
		return messages;
	}

	public Conversation(String address, MessagesHandler mh) {
		this.address = address;
		this.mh = mh;
		messages = new ArrayList<Message>();

	}

	public void sendMessage(String message) {
		messages.add(new Message(message));
		mh.getConnectionHandler().executeCommand("osascript ~/OurMessage/sendMessage.scpt \"" +
				message + "\" \"" + address + "\"");
	}

	public void addReceivedMessage(String message) {
		messages.add(new Message(message, "John Doe"));
	}

	public int getNumMessages() {
		return messages.size();
	}
	public Message getMessage(int i) {
		return messages.get(i);
	}

	public boolean pullMessages() {
		String stream = mh.getConnectionHandler().executeCommand("cat ~/OurMessage/messages/" + address);
		if (stream.equals("_*_*_*")) {
			return false;
		}
		String[] newMessages = stream.split("\\R");
		if (newMessages.length > 0) {
			for (String s: newMessages) {
				addReceivedMessage(s);
			}
			mh.getConnectionHandler().executeCommand("rm ~/OurMessage/messages/" + address);
			return true;
		}
		return false;
	}
}