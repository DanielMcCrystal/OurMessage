package com.lampshadesoftware.ourmessage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Packet;
import com.jcraft.jsch.Session;
import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private EditText messageBar;
	private TextView receivedMessage;
	ConnectionHandler ch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		messageBar = findViewById(R.id.editText);
		receivedMessage = findViewById(R.id.receivedMessage);

		Thread connectThread = new Thread() {
			public void run() {
				ch = new ConnectionHandler();
			}
		};
		connectThread.start();
		try { connectThread.join(); } catch(Exception e) {}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ch.disconnect();
	}

	public void buttonPressed(View v) {
		Log.v("Test", "Button pressed");

		Thread executeThread = new Thread() {
			public void run() {
				String message = messageBar.getText().toString();
				String command = "osascript ~/Scripts/sendMessage.scpt \"" + message + "\" \"" + "+13053234079" + "\"";
				ch.executeCommand(command);

			}
		};
		executeThread.start();

	}

}
