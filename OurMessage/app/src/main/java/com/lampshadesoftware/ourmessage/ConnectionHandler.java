package com.lampshadesoftware.ourmessage;

/**
 * Created by danielmccrystal on 12/21/17.
 */


import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Packet;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public class ConnectionHandler {
	private Session session;
	private Channel channel;

	public ConnectionHandler() {
		System.out.println("here I am");
		try  {
			JSch jsch = new JSch();
			session = jsch.getSession("danielmccrystal","192.168.1.15", 22);
			session.setPassword("1212");

			// Avoid asking for key confirmation
			Properties prop = new Properties();
			prop.put("StrictHostKeyChecking", "no");
			session.setConfig(prop);

			session.connect();
			Log.v("CH", "Successfully made connection");

		} catch (Exception e) {
			e.printStackTrace();
			Log.v("CH", "Failed to make SSH connection");
		}
	}

	public void disconnect() {
		channel.disconnect();
		session.disconnect();
	}


	private Channel getChannel() {
		try {
			channel = session.openChannel("exec");
		} catch(Exception e) {
			Log.v("CH", "Problem opening channel");
		}

		return channel;
	}

	public String executeCommand(String command) {
		String output = "failed";
		try {

			ChannelExec ce = (ChannelExec) getChannel();
			ce.setCommand(command);
			channel.setInputStream(null);
			((ChannelExec)channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			ce.connect();

			byte[] tmp=new byte[1024];
			while(true){
				while(in.available()>0){
					int i=in.read(tmp, 0, 1024);
					if(i<0)break;
					output = new String(tmp, 0, i);
				}
				if(channel.isClosed()){
					if(in.available()>0) continue;
					//System.out.println("exit-status: "+channel.getExitStatus());
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}
			}
			ce.disconnect();
		}
		catch (Exception e)
		{
			Log.v("CH", "Failed to execute command");
			System.out.println(e.getMessage());
		}
		return output;
	}
}
