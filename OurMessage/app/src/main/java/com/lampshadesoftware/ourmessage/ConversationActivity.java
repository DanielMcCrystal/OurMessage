package com.lampshadesoftware.ourmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ConversationActivity extends AppCompatActivity {

	private ListView conversationList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);

		conversationList = findViewById(R.id.conversationListView);

		String[] messages = {"a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b"};

		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, messages);

		conversationList.setAdapter(adapter);
	}
}
