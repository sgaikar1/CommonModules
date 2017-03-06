package com.example.smartrealitymodules.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.models.share.CheckForShareReq;
import com.example.smartrealitymodules.models.share.CheckForShareRes;
import com.example.smartrealitymodules.models.share.SmsDeliveredReceiver_Login;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.SharePresenter;
import com.example.smartrealitymodules.mvp.view.ShareView;
import com.example.smartrealitymodules.ui.base.BaseActivity;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by user on 23/2/17.
 */

public class ShareActivity extends BaseActivity implements ShareView {

    @Inject
    public MainModel mainModel;
    private ShareActivity mContext;
    private ImageView imgwhatsapp;
    private ImageView imggoogleplus;
    private ImageView imgtwitter;
    private ImageView imgfb;
    private RelativeLayout rlShareNewNumber;
    private RelativeLayout rlShareContacts;
    private Dialog alert;
    private EditText edtNumber;
    private EditText edtName;

    public static boolean filterByPackageName(Context context, Intent intent, String prefix) {
        List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith(prefix)) {
                intent.setPackage(info.activityInfo.packageName);
                return true;
            }
        }

        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);
        renderView();
        initClicks();
    }

    private void renderView() {
        setContentView(R.layout.activity_share);
        imgwhatsapp = (ImageView) findViewById(R.id.imgwhatsapp);
        imggoogleplus = (ImageView) findViewById(R.id.imggoogleplus);
        imgtwitter = (ImageView) findViewById(R.id.imgtwitter);
        imgfb = (ImageView) findViewById(R.id.imgfb);
        rlShareNewNumber = (RelativeLayout) findViewById(R.id.rlShareNewNumber);
        rlShareContacts = (RelativeLayout) findViewById(R.id.rlShareContacts);

    }

    private void initClicks() {
        rlShareNewNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });

        rlShareContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 23/2/17 Implement permission helper for read contacts
                Intent in = new Intent(ShareActivity.this, ListContact.class);
                startActivity(in);
            }
        });

        imgfb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/sharer/sharer.php?u=http://bit.ly/2gMXEFn"));
                startActivity(browserIntent);
            }
        });

        imggoogleplus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Launch the Google+ share dialog with attribution to your app.
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,
                        "Hi, just thought of sharing the Wadhwa Group App. Click on this link http://bit.ly/2gMXEFn to get the app.");
                boolean sharegoogleflag = filterByPackageName(ShareActivity.this, intent, "com.google.android.apps.plus");

                if (sharegoogleflag) {

                    startActivity(intent);

                } else {

                    Intent gplus = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://plus.google.com/share?url=http://bit.ly/2gMXEFn"));
                    startActivity(gplus);

                }
            }
        });

        imgwhatsapp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isConnected) {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "Hi, just thought of sharing the Wadhwa Group App. Click on this link http://bit.ly/2gMXEFn to get the app.";

                    waIntent.setPackage("com.whatsapp");
                    if (waIntent != null) {
                        waIntent.putExtra(Intent.EXTRA_TEXT, text);//
                        startActivity(Intent.createChooser(waIntent,
                                "Share with"));
                    } else {

                        showToast("WhatsApp is not Installed");

                    }
                } else {
                    Utils.showAlertDialog(ShareActivity.this,
                            "No Internet Connection",
                            "You don't have internet connection.");
                }
            }
        });

        imgtwitter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tw = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/intent/tweet?text=Hi, just thought of sharing the Wadhwa Group App. Click on this link http://bit.ly/2gMXEFn to get the app."));
                startActivity(tw);
            }
        });
    }

    private void showDialog() {

        LayoutInflater inflater = ShareActivity.this
                .getLayoutInflater();
        alert = new Dialog(ShareActivity.this,
                R.style.FullHeightDialog);
        View view = inflater.inflate(R.layout.dialog_share_numbers, null);
        alert.setContentView(view);
        alert.setCancelable(true);
        alert.show();

        try {
//                    alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            InputMethodManager inputManager = (InputMethodManager)
                    ShareActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(alert.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView txtHeading, txtAlert;
        ImageView ivClose;

        Button btnOk;

        txtHeading = (TextView) view.findViewById(R.id.txtHeading);
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        btnOk = (Button) view.findViewById(R.id.btnOk);
        edtNumber = (EditText) view.findViewById(R.id.edtNumber);
        edtName = (EditText) view.findViewById(R.id.edtName);


        txtHeading.setText("NEW NUMBER");

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 23/2/17 Implement permission helper
                try {
//                    alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    InputMethodManager inputManager = (InputMethodManager)
                            ShareActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(alert.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (edtNumber.getText().toString().equalsIgnoreCase("") || edtNumber.getText().toString().length() < 10) {
                    Toast.makeText(ShareActivity.this, "Kindly enter valid number", Toast.LENGTH_SHORT).show();
                } else {

                    Utils.hideKeyboard(ShareActivity.this);

                    StringBuilder json;
                    json = new StringBuilder();
                    json.append("{");
                    json.append("\"result\"");
                    json.append(":");
                    json.append("[");

//                    for (int i = 0; i < arrReferral.size(); i++) {
                    json.append("{");
                    json.append("\"MobileNo\"");
                    json.append(":");
                    json.append("\"" + edtNumber.getText().toString() + "\"");
                    json.append(",");

                    json.append("\"Name\"");
                    json.append(":");
                    json.append("\"" + edtName.getText().toString() + "\"");
                    json.append("}");
//                    if (i == (arrReferral.size() - 1)) {
//
//                    } else {
//                        json.append(",");
//                    }

//                    }
                    json.append("]");
                    json.append("}");
                    String strjson = json.toString();


                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    final String id = telephonyManager.getDeviceId();

                    if (telephonyManager.getSimState() != TelephonyManager.SIM_STATE_ABSENT) {

                        ShareNumber(edtNumber.getText().toString(), strjson);
                    } else {
                        Toast.makeText(ShareActivity.this, "No sim present", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void ShareNumber(String number, String strjson) {
        if (isConnected) {
            ArrayList<CheckForShareReq> mySoapArr = new ArrayList<CheckForShareReq>();
            CheckForShareReq mySoapObj;

            mySoapObj = new CheckForShareReq(Constants.sAppUserName);
            mySoapArr.add(mySoapObj);
            mySoapObj = new CheckForShareReq(Constants.sAppPassword);
            mySoapArr.add(mySoapObj);
            mySoapObj = new CheckForShareReq(Constants.USERID);
            mySoapArr.add(mySoapObj);
            mySoapObj = new CheckForShareReq(Constants.USERTYPE);
            mySoapArr.add(mySoapObj);
            mySoapObj = new CheckForShareReq(Constants.PROJECTCODE);
            mySoapArr.add(mySoapObj);
            mySoapObj = new CheckForShareReq(strjson);
            mySoapArr.add(mySoapObj);


//            new WebService(Share.this, (WebserviceResponseListner) this, mySoapArr,
//                    "CheckForShare").execute();

            SharePresenter presenter = new SharePresenter(mainModel, this);
            presenter.getCheckForShare(mySoapArr, ApiNames.GetCheckForShare);

        } else {
            Utils.showAlertDialog(ShareActivity.this, "No Internet Connection", "You don't have internet connection.");
        }
    }

    public void showAlert(final String strMobileNumber) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(
                ShareActivity.this);
        View mView = ShareActivity.this.getLayoutInflater().inflate(
                R.layout.popmessagesend, null, false);
        alert.setView(mView);
        // alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setCancelable(true);
        Button btnOk = (Button) mView.findViewById(R.id.buttonOK);
        Button btnCancel = (Button) mView.findViewById(R.id.buttonCancel);

        final Dialog mDialog = alert.create();
        mDialog.show();

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendSMS(strMobileNumber,
                        "Hi, just thought of sharing The Wadhwa Group App. Click on this link http://bit.ly/2gMXEFn to get the app.");
                mDialog.dismiss();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mDialog.dismiss();
            }
        });

    }

    private void sendSMS(String phoneNumber, String message) {

        SharedPreferences preferences = getSharedPreferences("myData",
                MODE_PRIVATE);
        int count = preferences.getInt("Counts", 0);

        Intent delivered = new Intent(getBaseContext(),
                SmsDeliveredReceiver_Login.class);

        delivered.putExtra("PhoneNumber", phoneNumber);

        PendingIntent pendingIntent_delivered = PendingIntent.getBroadcast(
                getBaseContext(), count, delivered, 0);

        PendingIntent pendingIntent_sent = PendingIntent.getBroadcast(
                getBaseContext(), count, new Intent("SMS_SENT"), 0);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pendingIntent_sent,
                pendingIntent_delivered);
        count++;
        SharedPreferences sharedPreferences = getSharedPreferences("myData",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Counts", count);
        editor.commit();

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:

                        mUtils.toastMe(mContext, "Thank you for sharing The Wadhawa Group App.");

                        break;

                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                        Utils.makeToast(Login.this, "Generic failure cause");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
//                        Utils.makeToast(Login.this, "Service is currently unavailable");
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
//                        Utils.makeToast(Login.this, "No pdu provided");
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
//                        Utils.makeToast(Login.this, "Radio was explicitly turned off");
                        break;
                }
            }
        }, new IntentFilter("SMS_SENT"));

    }

    @Override
    public void GetCheckForShareSuccess(CheckForShareRes checkForShareReq) {
        showAlert(edtNumber.getText().toString());
    }
}
