package com.lampshadesoftware.ourmessage;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ConversationActivity extends AppCompatActivity {

	MessagesHandler mh;

	private ListView conversationList;
	private Button sendButton;
	private EditText inputEditText;
	private Conversation convo;
	private MessageAdapter adapter;

	private boolean ready = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);

		conversationList = findViewById(R.id.conversationListView);
		sendButton = findViewById(R.id.sendButton);
		inputEditText = findViewById(R.id.inputEditText);

		String me = "+14345668824";
		String miranda = "+17068309594";
		String haseeb = "+15712915887";
		final String address = miranda;
		final Context context = this;

		Thread t = new Thread() {
			public void run() {
				mh = new MessagesHandler();
				mh.startNewConversation(address);
				convo = mh.getConversation(address);
				ready = true;
				adapter = new MessageAdapter(context, convo);

			}
		};
		t.start();
		try {t.join();} catch (InterruptedException e) { };
		conversationList.setAdapter(adapter);

		final Runnable updateList = new Runnable() {
			public void run() {
				adapter.notifyDataSetChanged();
			}
		};
		Thread checkForMessages = new Thread() {
			public void run() {
				while(true) {
					if(convo.pullMessages()) {
						conversationList.post(updateList);
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						return;
					}
				}
			}
		};
		checkForMessages.start();
	}

	public void sendButtonPressed(View view) {
		final String message = inputEditText.getText().toString();
		if (message != "") {
			Log.v("CA", "Attempting to send: " + message);
			Thread t = new Thread() {
				public void run() {
					convo.sendMessage(message);
				}
			};
			t.start();
			try {t.join();} catch (InterruptedException e) { };
			inputEditText.setText("");
			adapter.notifyDataSetChanged();
		}
	}
}
