package com.lampshadesoftware.ourmessage;

import java.util.ArrayList;

/**
 * Created by danielmccrystal on 12/25/17.
 */


public class Conversation {
	protected MessagesHandler mh;
	private String id;
	public String getID() {
		return id;
	}

	private ArrayList<Message> messages;
	public ArrayList<Message> getAllMessages() {
		return messages;
	}

	public Conversation(String buddy) {
		this.id = buddy;
		messages = new ArrayList<Message>();
	}

	public void sendMessage(String message) {
		messages.add(new Message(message));
	}

	public void addFillerMessage(String message) {
		messages.add(new Message(message, "John Doe"));
	}

	public int getNumMessages() {
		return messages.size();
	}
	public Message getMessage(int i) {
		return messages.get(i);
	}
}