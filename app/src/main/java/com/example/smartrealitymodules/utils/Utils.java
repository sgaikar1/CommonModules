package com.example.smartrealitymodules.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartrealitymodules.R;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 13/2/17.
 */

public class Utils {

    public static final String BASEURL = "http://vm1.dopoints.in/spr_ws/SPR.svc/";

    public static final int CACHETIME = 432000;

    public static final String Device = "android";
    public static final String Devicedownload = "androiddownload";

    public static final String Prospect = "Prospect";
    public static final String Buyer = "Buyer";
    public static final String Employee = "Employee";
    public static final String Broker = "Broker";

    public static final String USER_ID = "USER_ID";
    public static final String USER_TYPE = "USER_TYPE";

    public static final String FACEBOOK_SHARE = "FACEBOOK_SHARE";
    public static final String TWITTER_SHARE = "TWITTER_SHARE";
    public static final String LINKEDIN_SHARE = "LINKEDIN_SHARE";
    public static final String WHATSAPP_SHARE = "WHATSAPP_SHARE";
    public static final String NO_INTERNET = "NO_INTERNET";

    public Utils() {
    }

    public static void openShareIntent(Activity activity, String message) {
        try {
            ShareCompat.IntentBuilder.from(activity)
                    .setType("text/plain")
//                  .addEmailTo(getString(R.string.support_email_id))
//                  .setSubject("")
                    .setText(message)
//                  .setHtmlText(body) //If you are using HTML in your body text
                    .setChooserTitle("Share")
                    .startChooser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertBudgetToCrore(String budget) {
        String s = "";

        try {
            if (!budget.equalsIgnoreCase("")) {
                double num = Integer.parseInt(budget);
                double float_num = 0;
                if (budget.length() >= 8) {
                    float_num = num / 10000000;
                    s = String.format("%.2f", float_num);
                    String dec = s.substring(0, s.indexOf(".") + 2);
                    s = "₹ " + dec + " Cr";
//                    if (dec.equals("00")) {
//                        int newnum = (int) Float.parseFloat(s);
//                        s = Integer.toString(newnum) +" Cr";
//                    }
                } else if (budget.length() >= 6) {
                    float_num = num / 100000;
                    s = String.format("%.2f", float_num);
                    String dec = s.substring(0, s.indexOf(".") + 2);
                    s = "₹ " + dec + " Lac";
//                    if (dec.equals("00")) {
//                        int newnum = (int) Float.parseFloat(s);
//                        s = Integer.toString(newnum) +" Lac" ;
//                    }
                } else if (budget.length() >= 4) {
                    float_num = num / 1000;
                    s = String.format("%.2f", float_num);
                    String dec = s.substring(0, s.indexOf(".") + 2);
                    s = "₹ " + dec + " K";
//                    if (dec.equals("00")) {
//                        int newnum = (int) Float.parseFloat(s);
//                        s = Integer.toString(newnum) +" K";
//                    }
                }

            } else {
                s = "";
            }
        } catch (Exception e) {
            Log.e("Covert exp", e + "");
        }
        return s;

    }

    public static String convertBudgetToCrore1(String budget) {
        String s = "";

        try {
            if (!budget.equalsIgnoreCase("")) {
                int num = Integer.parseInt(budget);
                float float_num = num / 10000000;
                s = String.format("%.2f", float_num);
                String dec = s.substring(s.indexOf(".") + 1);
                if (dec.equals("00")) {
                    int newnum = (int) Float.parseFloat(s);
                    s = Integer.toString(newnum);
                }
            } else {
                s = "";
            }
        } catch (Exception e) {
            Log.e("Covert exp", e + "");
        }
        return s;

    }

    public static void showAlertDialog(Context context, String title,
                                       String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", null);
        alertDialog.show();
    }

    public static void hideKeyboard(Activity mAcivity) {

        try {
            InputMethodManager inputManager = (InputMethodManager)
                    mAcivity.getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(mAcivity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
//            e.printStackTrace();
        }

    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        logMe("testing", "image load");
        if (!url.equals("")) {
//            Picasso.with(imageView.getContext()).load(url).resize(200, 200).into(imageView);

            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }

    /**
     * Method to check whether internet is connection or connected
     *
     * @param mContext - context of activity
     * @return status of internet connection
     */
//    public boolean isInternetPresent(Context mContext) {
//        ConnectivityManager connectivity = (ConnectivityManager) mContext.
//                getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivity != null) {
//            NetworkInfo info = connectivity.getActiveNetworkInfo();
//            if (info != null && info.isConnected()) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * Method for logging the message
     *
     * @param tag     - tag for log
     * @param message - message for log
     **/
    public static void logMe(String tag, String message) {
        Log.e(tag, message);
    }

    public void getWebserviceLog(String tag, Object obj) {
        Gson gson = new Gson();
        logMe(tag, gson.toJson(obj));
    }

    /**
     * Method for toast the message
     *
     * @param mContext - context of activity
     * @param message  - message to toast
     */
    public void toastMe(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Method for load the images from url using picasso or Bitmap
     *
     * @param mContext - context of Activity
     * @param filepath - image URL to load image in imageview
     * @param view     - Imageview where to load the image from url
     */
    public void loadImageInImageview(Context mContext, String filepath, final ImageView view) {

        if (filepath != null && URLUtil.isValidUrl(filepath)) {
            Picasso.with(mContext)
                    .load(filepath).placeholder(R.mipmap.ic_launcher)
                    //.skipMemoryCache()
                    .into(view, new Callback() {

                        @Override
                        public void onSuccess() {
                            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }

                        @Override
                        public void onError() {
                            try {
                                view.setImageResource(R.mipmap.ic_launcher);
                            } catch (Exception | OutOfMemoryError e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            view.setImageResource(R.mipmap.ic_launcher);
        }
    }

    /**
     * Mehtod for converting dates from one format to another
     *
     * @param from - date format that the actual date is in
     * @param to   - date format in which the method will convert it
     * @param date - date which have to convert in required format
     * @return Converted date
     */
    public String convertDateFormat(String from, String to, String date) {
        SimpleDateFormat sdfFrom = new SimpleDateFormat(from);
        SimpleDateFormat sdfTo = new SimpleDateFormat(to);
        Date mDate = null;
        String convertedDate = "";

        try {
            mDate = sdfFrom.parse(date);
            convertedDate = sdfTo.format(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedDate;
    }

    /**
     * Method to get configures account's email address from device
     *
     * @param mContext - context of Activity
     * @return ArrayList of accounts
     */
    public ArrayList<String> getConfigiredAccounts(Context mContext) {
        ArrayList<String> emails = new ArrayList<>();

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(mContext).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                emails.add(account.name);
            }
        }
        return emails;
    }

    public String getUserDeviceEmailID(Context context) {
        ArrayList<String> mEmailIDs = new ArrayList<>();
        try {
            Account[] accounts = AccountManager.get(context)
                    .getAccounts();
            for (Account account : accounts) {
                // Check here for the type and name to find the email
                if (account.type.equalsIgnoreCase("com.google")) {
                    mEmailIDs.add(account.name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sEmailID = mEmailIDs.toString().replace("[", "").replace("]", "");
        return sEmailID;
    }

    /**
     * Method to check the entered email address is valid or not
     *
     * @param email - email address to check
     * @return true / false accroding to email address
     */
    public boolean isEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Mehtod to get device's unique device id
     *
     * @return unique device id
     */
    public String getUniqueIdOfDevice() {
        String deviceId = null;
        String serial = null;

        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10)
                + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10)
                + (Build.DEVICE.length() % 10)
                + (Build.MANUFACTURER.length() % 10)
                + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);


        try {
            serial = Build.class.getField("SERIAL").get(null)
                    .toString();
            logMe("unique-1", serial);
            logMe("unique-2", m_szDevIDShort);
            // Go ahead and return the serial for api => 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode())
                    .toString();
        } catch (Exception e) {
            // String needs to be initialized
            serial = "serial"; // some value
        }

        deviceId = new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();

        return deviceId;
    }

    /**
     * Alert Dialog Layout
     */

    public AlertDialog createAlertDialog(Context mContext) {
        AlertDialog mAlertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.FullHeightDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(true);
        WindowManager.LayoutParams wlmp = mAlertDialog.getWindow().getAttributes();
        wlmp.gravity = Gravity.BOTTOM;
        wlmp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlmp.height = WindowManager.LayoutParams.MATCH_PARENT;
        mAlertDialog.getWindow().setAttributes(wlmp);
        return mAlertDialog;
    }

    public void toastAlert(Activity mContext, String message) {

//        Configuration croutonConfiguration = new Configuration.Builder().setDuration(2500).build();
//        Style CUSTOM_STYLE = new Style.Builder()
//                .setTextColor(R.color.colorAccent)
//                .setBackgroundColorValue(R.color.black).setHeight(100)
//                .setImageResource(R.drawable.ic_cart)
//                .setGravity(Gravity.CENTER_HORIZONTAL).setConfiguration(croutonConfiguration)
//                .build();
//        Crouton.showText(mContext, message, CUSTOM_STYLE, R.id.crouton);
//        Crouton.showText(mContext, message, Style.ALERT, R.id.crouton);
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public void toastInfo(Activity mContext, String message) {

//        Crouton.showText(mContext, message, Style.INFO, R.id.crouton);
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void openCallScreen(Context context, String phoneNo) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNo));
        context.startActivity(callIntent);
    }

    public void openShareMailIntent(Activity activity, String Recipient) {
        try {
            ShareCompat.IntentBuilder.from(activity)
                    .setType("message/rfc822")
                    .addEmailTo(Recipient)
                    .setSubject("")
                    .setText("")
                    .setChooserTitle("Choose")
                    .startChooser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyStream(InputStream input, OutputStream output)
            throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    public String getCurrentDate() {
        String formattedDate = "";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss", Locale.US);
            formattedDate = simpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            formattedDate = "";
        }
        return formattedDate;
    }

    /**
     * Convert URL to Bitmap using AsyncTask
     */
    public class GetBitmapFromUrl extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }

}
