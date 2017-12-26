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
	private String service;
	private LinkedList<Conversation> conversations;

	public MessagesHandler() {
		//ch = new ConnectionHandler();
		service = "E:kayakoo@aol.com";
		conversations = new LinkedList<Conversation>();
	}

	public void startNewConverstaion(String buddy) {
		conversations.add(new Conversation(buddy));
	}
	public void sendMessage(String recipient, String message) {

	}

	public void updateConversations() {

	}
}


