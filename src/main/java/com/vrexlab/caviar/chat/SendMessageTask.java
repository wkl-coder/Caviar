package com.vrexlab.caviar.chat;

import android.os.AsyncTask;

import com.vrexlab.caviar.fragments.LiveFragment;

/**
 * Created by Sebastian Rask Jepsen on 21/07/16.
 */
public class SendMessageTask extends AsyncTask<Void, Void, Void> {
	private ChatManager mBot;
	private String message;
	private LiveFragment liveFragment;

	public SendMessageTask(ChatManager mBot, String message, LiveFragment liveFragment) {
		this.mBot = mBot;
		this.message = message;
		this .liveFragment = liveFragment;
	}

	@Override
	protected Void doInBackground(Void... voids) {
		if (mBot != null && message != null) {
			mBot.sendMessage(message);
		}
		return null;
	}

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        liveFragment.addMessage(message);
    }
}
