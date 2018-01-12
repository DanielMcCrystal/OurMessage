package com.lampshadesoftware.ourmessage;

import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by danielmccrystal on 12/22/17.
 */

public class MessagesHandler {

	private ConnectionHandler ch;
	public ConnectionHandler getConnectionHandler() {
		return ch;
	}
	private String service;
	private LinkedList<Conversation> conversations;

	public MessagesHandler() {
		ch = new ConnectionHandler();
		service = "E:kayakoo@aol.com";
		conversations = new LinkedList<Conversation>();
	}

	public void startNewConversation(String address) {
		conversations.add(new Conversation(address, this));
	}


	public void updateConversations() {
		for(Conversation c: conversations) {
			c.pullMessages();
		}
	}

	public Conversation getConversation(String address) {
		for(Conversation c: conversations) {
			if (c.getAddress().equals(address)) {
				System.out.println("Returning conversation with address: " + address);
				return c;
			}
		}
		System.out.println("No existing conversation with address: " + address);
		return null;
	}

}


