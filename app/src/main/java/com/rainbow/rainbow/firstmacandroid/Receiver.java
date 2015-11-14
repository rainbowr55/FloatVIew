package com.rainbow.rainbow.firstmacandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by rainbow on 15/7/17.
 */
public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "receiver intent:" + intent.toString());
    }

}
