package com.lampshadesoftware.ourmessage;

import java.sql.Time;

/**
 * Created by danielmccrystal on 12/25/17.
 */


public class Message {
	private String sender;
	private String content;
	private Time timestamp;
	private boolean self;

	public Message(String content) {
		this(content, "Me", true);
	}
	public Message(String sender, String content) {
		this(sender, content, false);
	}

	private Message(String content, String sender, boolean self) {
		this.sender = sender;
		this.content = content;
		this.self = self;
	}
}