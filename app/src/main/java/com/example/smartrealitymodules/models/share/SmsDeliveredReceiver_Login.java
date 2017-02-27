package com.example.smartrealitymodules.models.share;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SmsDeliveredReceiver_Login extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        // TODO Auto-generated method stub
        String str = new String(arg1.getStringExtra("PhoneNumber"));

        switch (getResultCode()) {
            case Activity.RESULT_OK:
                // //////////////Log.e("ok", "send");
                break;
            case Activity.RESULT_CANCELED:
                break;
        }
    }

}
