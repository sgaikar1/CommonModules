package com.example.smartrealitymodules.models.share;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class SmsSentReceiver extends BroadcastReceiver {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String DeliverDate;

    @Override
    public void onReceive(Context context, Intent intent) {


        switch (getResultCode()) {
            case Activity.RESULT_OK:
                Toast.makeText(context, "SMS sent successfully", Toast.LENGTH_SHORT)
                        .show();


                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:

                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE:

                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:

                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                break;
        }
    }


}