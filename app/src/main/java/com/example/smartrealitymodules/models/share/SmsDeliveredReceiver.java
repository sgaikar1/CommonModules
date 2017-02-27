package com.example.smartrealitymodules.models.share;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class SmsDeliveredReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent arg1) {

        switch (getResultCode()) {
            case Activity.RESULT_OK:


                Toast.makeText(context, "SMS delivered successfully",
                        Toast.LENGTH_SHORT).show();

                break;
            case Activity.RESULT_CANCELED:

                break;
        }

    }


}
