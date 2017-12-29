package com.lampshadesoftware.ourmessage;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by danielmccrystal on 12/25/17.
 */

public class MessageAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private Conversation conversation;

	public MessageAdapter(Context context, Conversation conversation) {
		this.context = context;
		this.conversation = conversation;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if (conversation != null) {
			return conversation.getNumMessages();
		}

		return 0;
	}

	@Override
	public Object getItem(int i) {
		return conversation.getMessage(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		View rowView = inflater.inflate(R.layout.list_item_message, viewGroup, false);
		TextView messageContent = rowView.findViewById(R.id.messageContent);
		Message m = (Message) getItem(i);
		messageContent.setText(m.getContent());
		if (m.getSelfBool()) {
			rowView.setBackgroundColor(Color.BLUE);
			messageContent.setTextColor(Color.WHITE);
			messageContent.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
		} else {
			rowView.setBackgroundColor(Color.GRAY);
			messageContent.setTextColor(Color.BLACK);
			messageContent.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
		}
		return rowView;
	}
}
