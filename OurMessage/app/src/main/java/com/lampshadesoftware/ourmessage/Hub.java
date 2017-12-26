package com.lampshadesoftware.ourmessage;

/**
 * Created by danielmccrystal on 12/25/17.
 */

public class Hub {

	public static MessagesHandler messagesHandler;

	public static void init() {
		messagesHandler = new MessagesHandler();
	}

}
