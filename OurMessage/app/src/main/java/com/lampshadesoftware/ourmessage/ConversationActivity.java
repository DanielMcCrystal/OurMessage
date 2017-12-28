package com.lampshadesoftware.ourmessage;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ConversationActivity extends AppCompatActivity {

	private ListView conversationList;
	private Button sendButton;
	private EditText inputEditText;
	private Conversation convo;
	private MessageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);

		conversationList = findViewById(R.id.conversationListView);
		sendButton = findViewById(R.id.sendButton);
		inputEditText = findViewById(R.id.inputEditText);

		convo = new Conversation("John Doe");

		adapter = new MessageAdapter(this, convo);
		conversationList.setAdapter(adapter);
	}

	public void sendButtonPressed(View view) {
		convo.sendMessage(inputEditText.getText().toString());
		inputEditText.setText("");
		adapter.notifyDataSetChanged();
	}
}
