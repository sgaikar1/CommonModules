package com.example.smartrealitymodules.ui.activity;

import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.api.ApiNames;
import com.example.smartrealitymodules.models.share.CheckForShareReq;
import com.example.smartrealitymodules.models.share.CheckForShareRes;
import com.example.smartrealitymodules.models.share.ContactEntity;
import com.example.smartrealitymodules.models.share.MobileandRemark;
import com.example.smartrealitymodules.models.share.PhoneNumber;
import com.example.smartrealitymodules.models.share.ReferralEntity;
import com.example.smartrealitymodules.models.share.SmsDeliveredReceiver;
import com.example.smartrealitymodules.models.share.SmsSentReceiver;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.presenter.SharePresenter;
import com.example.smartrealitymodules.mvp.view.ShareView;
import com.example.smartrealitymodules.ui.BaseActivity.BaseActivity;
import com.example.smartrealitymodules.ui.adapter.ContactListRowAdapter;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

public class ListContact extends BaseActivity implements ShareView {
    @Inject
    public MainModel mainModel;
    // protected ArrayList<M_Share> list_summary;
    protected boolean isInternetPresent = false;
    protected String METHOD_NAME;
    protected String struserID = "";
    protected String struserType = "";
    protected String strPopMessageSend = "";
    protected String strPopMessageNotSend = "";
    String strNumbers = "";
    String sAppUserName = "", sAppPassword = "", sProjectCode = "", sUserID = "", sUserType = "";
    // protected String strProjectID="";
    Dialog alert;
    int count_send = 0;
    int count_notsend = 0;
    ArrayList<MobileandRemark> arraylist_mobile_remark;
    SharedPreferences mpref;
    // DBHelper db;
    String user_name = null;
    LinkedHashMap<String, String> users_number;
    ArrayList<ContactEntity> arrContactEntities;
    ArrayList<PhoneNumber> arrPhoneNumbers;
    ListView listContact;
    Context context;
    AutoCompleteTextView autocomplete_search;
    int position = 0;
    ArrayList<ReferralEntity> arrReferral;
    Button btnSend;
    String mobileNumber = "";
    boolean flag = false;
    ReferralEntity ref;
    Toolbar toolbar;
    TextView toolbar_title;
    private ArrayList<Map<String, String>> mPeopleList;
    private SimpleAdapter mAdapter;
    private Dialog dilaog;
    private ContactListRowAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcontact);
        getDeps().inject(this);
        setProgressBar();


        flag = false;
        users_number = new LinkedHashMap<String, String>();

        mpref = getSharedPreferences("myData", MODE_PRIVATE);
        mPeopleList = new ArrayList<Map<String, String>>();
        mPeopleList.clear();
        listContact = (ListView) findViewById(R.id.listContact);
        autocomplete_search = (AutoCompleteTextView) findViewById(R.id.autocomplete_search);
        context = ListContact.this;
        // db=new DBHelper(context);
        new LoadingContact().execute();

        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    InputMethodManager inputManager = (InputMethodManager) ListContact.this
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(ListContact.this
                                    .getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (Exception e) {
                    // TODO: handle exception
                }

                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                final String id = telephonyManager.getDeviceId();

                if (telephonyManager.getSimState() != TelephonyManager.SIM_STATE_ABSENT) {
                    arrReferral = new ArrayList<ReferralEntity>();
                    mobileNumber = "";
                    flag = false;
                    for (int i = 0; i < arrContactEntities.size(); i++) {
                        if (arrContactEntities.get(i).getArrPhoneNumber().size() > 1) {
                            for (int j = 0; j < arrContactEntities.get(i)
                                    .getArrPhoneNumber().size(); j++) {
                                if (arrContactEntities.get(i).getArrPhoneNumber()
                                        .get(j).getCheck() == true) {
                                    String name = arrContactEntities.get(i)
                                            .getConatactName();
                                    flag = true;
                                    String strMobile = arrContactEntities.get(i)
                                            .getArrPhoneNumber().get(j)
                                            .getPhoneNumber();
                                    String strEmail = arrContactEntities.get(i)
                                            .getContactEmail();

                                    strMobile = strMobile.replaceAll(" ", "");
                                    strMobile = strMobile.replaceAll("-", "");
                                    if (strMobile.length() > 10) {
                                        int len = strMobile.length() - 10;
                                        strMobile = strMobile.substring(len);
                                        if (mobileNumber == null
                                                || mobileNumber.isEmpty()) {

                                            mobileNumber = strMobile;
                                            user_name = name;
                                            users_number.put(strMobile, name);
                                            ref = new ReferralEntity(name,
                                                    strMobile);
                                            arrReferral.add(ref);
                                        } else {
                                            mobileNumber = mobileNumber + "|"
                                                    + strMobile;
                                            user_name = user_name + "|" + name;
                                            users_number.put(strMobile, name);
                                            ref = new ReferralEntity(name,
                                                    strMobile);
                                            arrReferral.add(ref);
                                        }
                                    } else {
                                        if (mobileNumber == null
                                                || mobileNumber.isEmpty()) {
                                            mobileNumber = strMobile;
                                            user_name = name;
                                            users_number.put(strMobile, name);
                                            ref = new ReferralEntity(name,
                                                    strMobile);
                                            arrReferral.add(ref);
                                            // summary.put(
                                            // Integer.parseInt(strMobile),
                                            // name);
                                        } else {
                                            mobileNumber = mobileNumber + "|"
                                                    + strMobile;
                                            user_name = user_name + "|" + name;
                                            users_number.put(strMobile, name);
                                            ref = new ReferralEntity(name,
                                                    strMobile);
                                            arrReferral.add(ref);
                                            // summary.put(
                                            // Integer.parseInt(strMobile),
                                            // name);
                                        }
                                    }
                                }

                            }
                        } else {
                            if (!(arrContactEntities.get(i).getArrPhoneNumber()
                                    .size() < 1)) {
                                if (arrContactEntities.get(i).getArrPhoneNumber()
                                        .get(0).getCheck() == true) {
                                    flag = true;
                                    String name = arrContactEntities.get(i)
                                            .getConatactName();
                                    String strMobile = arrContactEntities.get(i)
                                            .getArrPhoneNumber().get(0)
                                            .getPhoneNumber();
                                    String strEmail = arrContactEntities.get(i)
                                            .getContactEmail();
                                    strMobile = strMobile.replaceAll(" ", "");
                                    strMobile = strMobile.replaceAll("-", "");
                                    if (strMobile.length() > 10) {
                                        int len = strMobile.length() - 10;
                                        strMobile = strMobile.substring(len);
                                        if (mobileNumber == null
                                                || mobileNumber.isEmpty()) {
                                            mobileNumber = strMobile;
                                            users_number.put(strMobile, name);
                                            ref = new ReferralEntity(name,
                                                    strMobile);
                                            arrReferral.add(ref);
                                        } else {
                                            mobileNumber = mobileNumber + "|"
                                                    + strMobile;
                                            users_number.put(strMobile, name);
                                            ref = new ReferralEntity(name,
                                                    strMobile);
                                            arrReferral.add(ref);
                                        }

                                    } else {
                                        if (mobileNumber == null
                                                || mobileNumber.isEmpty()) {
                                            mobileNumber = strMobile;
                                            user_name = name;
                                            users_number.put(strMobile, name);
                                            ref = new ReferralEntity(name,
                                                    strMobile);
                                            arrReferral.add(ref);
                                            // summary.put(
                                            // Integer.parseInt(strMobile),
                                            // name);
                                        } else {
                                            mobileNumber = mobileNumber + "|"
                                                    + strMobile;
                                            user_name = user_name + "|" + name;
                                            users_number.put(strMobile, name);
                                            ref = new ReferralEntity(name,
                                                    strMobile);
                                            arrReferral.add(ref);
                                            // summary.put(
                                            // Integer.parseInt(strMobile),
                                            // name);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (mobileNumber.equalsIgnoreCase("")) {
                        Toast.makeText(context,
                                "Please select atleast one contact ",
                                Toast.LENGTH_LONG).show();
                    } else {
                        final TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                        if (tm.getSimState() != TelephonyManager.SIM_STATE_ABSENT) {
                            // the device has a sim card
                            if (flag == true) {
                                showAlert(arrReferral);
                            } else {
                                Toast.makeText(context,
                                        "Please select atleast one contact ",
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            // no sim card available
                            showAlertSim();
                        }
                    }
                } else {
                    Toast.makeText(ListContact.this, "No sim present", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void getContactNames() {
        for (int i = 0; i < arrContactEntities.size(); i++) {

            if (arrContactEntities.get(i).getArrPhoneNumber().size() > 1) {

                for (int j = 0; j < arrContactEntities.get(i)
                        .getArrPhoneNumber().size(); j++) {

                    String name = arrContactEntities.get(i).getConatactName();
                    String strMobile = arrContactEntities.get(i)
                            .getArrPhoneNumber().get(j).getPhoneNumber();

                    strMobile = strMobile.replaceAll("-", "");
                    strMobile = strMobile.replaceAll(" ", "");
                    if (strMobile.length() > 10) {
                        int len = strMobile.length() - 10;
                        strMobile = strMobile.substring(len);

                        Map<String, String> NamePhoneType = new HashMap<String, String>();

                        NamePhoneType.put("Name", name);
                        NamePhoneType.put("Phone", strMobile);
                        mPeopleList.add(NamePhoneType);

                        // this.name.add(name);
                        // number_search.add(strMobile);
                    } else {

                        Map<String, String> NamePhoneType = new HashMap<String, String>();

                        NamePhoneType.put("Name", name);
                        NamePhoneType.put("Phone", strMobile);
                        mPeopleList.add(NamePhoneType);
                        // this.name.add(name);
                        // number_search.add(strMobile);
                    }

                }

            } else {

                if (!(arrContactEntities.get(i).getArrPhoneNumber().size() < 1)) {

                    String name = arrContactEntities.get(i).getConatactName();
                    String strMobile = arrContactEntities.get(i)
                            .getArrPhoneNumber().get(0).getPhoneNumber();
                    strMobile = strMobile.replaceAll("-", "");
                    strMobile = strMobile.replaceAll(" ", "");
                    if (strMobile.length() > 10) {

                        Map<String, String> NamePhoneType = new HashMap<String, String>();

                        int len = strMobile.length() - 10;
                        strMobile = strMobile.substring(len);
                        // this.name.add(name);
                        // number_search.add(strMobile);

                        NamePhoneType.put("Name", name);
                        NamePhoneType.put("Phone", strMobile);
                        mPeopleList.add(NamePhoneType);
                    } else {

                        Map<String, String> NamePhoneType = new HashMap<String, String>();

                        NamePhoneType.put("Name", name);
                        NamePhoneType.put("Phone", strMobile);
                        mPeopleList.add(NamePhoneType);
                        // this.name.add(name);
                        // number_search.add(strMobile);
                    }

                }
            }

        }
        setContactForSearch();
    }

    private void setContactForSearch() {
        mAdapter = new SimpleAdapter(this, mPeopleList, R.layout.contact_row,
                new String[]{"Name", "Phone"}, new int[]{R.id.ccontName,
                R.id.ccontNo});

        autocomplete_search.setAdapter(mAdapter);

        autocomplete_search.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int index,
                                    long arg) {
                Map<String, String> map = (Map<String, String>) av
                        .getItemAtPosition(index);
                Iterator<String> myVeryOwnIterator = map.keySet().iterator();
                while (myVeryOwnIterator.hasNext()) {

                    String key = myVeryOwnIterator.next();
                    String value = map.get(key);
                    if (key.equalsIgnoreCase("Name")) {
                        autocomplete_search.setText(value);
                    }

                }
                for (position = 0; position < arrContactEntities.size(); position++) {

                    if ((arrContactEntities.get(position).getConatactName())
                            .equalsIgnoreCase(autocomplete_search.getText()
                                    .toString())) {

                        break;
                    }

                }
                listContact.setSelection(position);

            }
        });
    }

    public void showAlert(ArrayList<ReferralEntity> arrReferral1) {

        alert = new Dialog(context,
                R.style.FullHeightDialog);

        alert.setContentView(R.layout.popmessagesend);
        // alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        Button btnOk = (Button) alert.findViewById(R.id.buttonOK);
        Button btnCancel = (Button) alert.findViewById(R.id.buttonCancel);
        btnOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // list_summary = new ArrayList<M_Share>();

                // isInternetPresent = cd.isConnectingToInternet();
                // METHOD_NAME = "getBrochureUrl";

                StringBuffer strnew = null;

                for (int i = 0; i < arrReferral.size(); i++) {
                    if (i == 0) {
                        strNumbers = arrReferral.get(i).getStrMobile();
                    } else {
                        strNumbers = strNumbers + "," + arrReferral.get(i).getStrMobile();
                    }
                }
                // METHOD_NAME = "getBrochureUrlWithProspectDetails";
                // METHOD_NAME = "getBrochureUrlWithProspectMultiProjectDtls";

                StringBuilder json;
                json = new StringBuilder();
                json.append("{");
                json.append("\"result\"");
                json.append(":");
                json.append("[");

                for (int i = 0; i < arrReferral.size(); i++) {
                    json.append("{");
                    json.append("\"MobileNo\"");
                    json.append(":");
                    json.append("\"" + arrReferral.get(i).getStrMobile() + "\"");
                    json.append(",");

                    json.append("\"Name\"");
                    json.append(":");
                    json.append("\"" + arrReferral.get(i).getStrEmailID() + "\"");
                    json.append("}");
                    if (i == (arrReferral.size() - 1)) {

                    } else {
                        json.append(",");
                    }

                }
                json.append("]");
                json.append("}");
                String strjson = json.toString();

                struserID = mpref.getString("UserID", "");
                // strProjectID = mpref.getString("PROJECT_ID", "no");
                struserType = mpref.getString("UserType", "");

                String MethodName = "CheckForShare";
                if (cd.isConnectingToInternet()) {
                    ShareWithMultipleNumbers(strjson);

                } else {
                    Utils.showAlertDialog(ListContact.this, "No Internet Connection",
                            "You don't have internet connection.");

                }

            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alert.dismiss();
            }
        });
        alert.show();
    }

    private void ShareWithMultipleNumbers(String strjson) {

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


    }


    public void showAlertSim() {

        final Dialog alert = new Dialog(context);
        alert.setContentView(R.layout.simpop);
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        Button btnOk = (Button) alert.findViewById(R.id.buttonShare);
        btnOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        alert.show();

    }


    public void sendSMS(String phoneNumber, String message) {

        SharedPreferences preferences = getSharedPreferences("MYDATA",
                MODE_PRIVATE);
        int count = preferences.getInt("Counts", 0);

        Intent delivered = new Intent(context, SmsDeliveredReceiver.class);

        // Intent delivered = new Intent(DELIVERED);
        delivered.putExtra("PhoneNumber", phoneNumber);

        PendingIntent pendingIntent_delivered = PendingIntent.getBroadcast(
                context, count, delivered, 0);

        Intent send = new Intent(context, SmsSentReceiver.class);

        send.putExtra("PhoneNumber", phoneNumber);

        PendingIntent pendingIntent_sent = PendingIntent.getBroadcast(context,
                count, send, 0);

        PendingIntent.getBroadcast(context, count, send, 0);

        PendingIntent.getBroadcast(context, count, delivered, 0);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pendingIntent_sent,
                pendingIntent_delivered);

    }


    @Override
    public void GetCheckForShareSuccess(CheckForShareRes checkForShareReq) {
        mUtils.toastMe(ListContact.this, "Shared Successfully");
    }

    @Override
    public void removeWait() {
        super.removeWait();
        if (alert != null) {
            alert.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class LoadingContact extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        private LoadingContact() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // ProgressDialog pDialog;
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please wait.");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

        }

        protected String doInBackground(String... params) {

            arrContactEntities = new ArrayList<ContactEntity>();
            ContentResolver cr = context.getContentResolver();
            Cursor cur = cr
                    .query(ContactsContract.Contacts.CONTENT_URI, null, null,
                            null, ContactsContract.Contacts.DISPLAY_NAME
                                    + " ASC");
            // strName = new String[size];
            if (cur.getCount() >= 0) {
                while (cur.moveToNext()) {
                    String id = cur.getString(cur
                            .getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur
                            .getString(cur
                                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    ContactEntity con = new ContactEntity();

                    if (Integer
                            .parseInt(cur.getString(cur
                                    .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        con.setConatactName(name);
                        arrPhoneNumbers = new ArrayList<PhoneNumber>();
                        Cursor pCur = cr
                                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                                + " = ?", new String[]{id},
                                        null);
                        while (pCur.moveToNext()) {
                            PhoneNumber phone = new PhoneNumber();
                            String str = pCur
                                    .getString(pCur
                                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            phone.setPhoneNumber(str);
                            phone.setCheck(false);
                            if (!arrPhoneNumbers.contains(phone)) {
                                arrPhoneNumbers.add(phone);
                            }
                            // Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
                        }
                        pCur.close();
                        con.setArrPhoneNumber(arrPhoneNumbers);
                        arrContactEntities.add(con);
                    }

                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            adapter = new ContactListRowAdapter(context, arrContactEntities);
            // adapter.notifyDataSetChanged();
            adapter.notifychange();
            listContact.setAdapter(adapter);

            getContactNames();
        }

    }

}
