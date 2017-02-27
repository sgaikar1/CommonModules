package com.example.smartrealitymodules.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;

/**
 * Created by user on 22/2/17.
 */

public class ContactUsActivity extends BaseActivity {
    private TextView txtheadoffice, txtaddress2, txtaddress;
    private RelativeLayout rlGetSms, rlGetDir;
    private LinearLayout rlMail, rlCall;
    private Dialog alert;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getDeps().inject(this);
        renderView();
        initClicks();
    }

    public void renderView() {
        setContentView(R.layout.activity_contact_us);
        rlGetSms = (RelativeLayout) findViewById(R.id.rlGetSms);
        rlGetDir = (RelativeLayout) findViewById(R.id.rlGetDir);
        rlMail = (LinearLayout) findViewById(R.id.rlMail);
        rlCall = (LinearLayout) findViewById(R.id.relative_call);
        txtaddress2 = (TextView) findViewById(R.id.txt_address2);
        txtaddress = (TextView) findViewById(R.id.txt_address);
        txtheadoffice = (TextView) findViewById(R.id.txt_headoffice);

    }

    private void initClicks() {

        rlGetDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = null;
                double Lat = 19.068716;
                double Lon = 72.868604;

                gmmIntentUri = Uri.parse("geo:" + Double.toString(Lat)
                        + "," + Double.toString(Lon) + "?q="
                        + Double.toString(Lat) + ","
                        + Double.toString(Lon) + "(Wadhwa Group)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                        gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        rlGetSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        rlCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel: 18002096669"));
                startActivity(callIntent);
            }
        });

        rlMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"inquiry@thewadhwagroup.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Enquiry");
                email.putExtra(Intent.EXTRA_TEXT, "");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

    }

    private void showDialog() {

        LayoutInflater inflater = ContactUsActivity.this
                .getLayoutInflater();
        alert = new Dialog(ContactUsActivity.this,
                R.style.FullHeightDialog);
        View view = inflater.inflate(R.layout.dialog_message, null);
        alert.setContentView(view);
        alert.setCancelable(true);
        alert.show();

        TextView txtHeading, txtAlert;
        ImageView ivClose;
        Button btnOk;

        txtHeading = (TextView) view.findViewById(R.id.txt_dialog_Heading);
        txtAlert = (TextView) view.findViewById(R.id.txt_dialog_msg);
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        btnOk = (Button) view.findViewById(R.id.btnOk);

        txtHeading.setText("GET SMS");
        txtAlert.setText("Do you wish to receive a sms of our location?");

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cd.isConnectingToInternet()) {
                    SendSMSofLocation();
                    alert.dismiss();
                } else {
                    mUtils.toastMe(mContext, getResources().getString(R.string.no_internet));
                }


            }
        });

    }

    private void SendSMSofLocation() {
        mUtils.toastMe(mContext, "Use your webservice to call send sms");
    }

}
