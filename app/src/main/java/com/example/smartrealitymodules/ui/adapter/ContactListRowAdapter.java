package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.share.ContactEntity;

import java.util.ArrayList;


public class ContactListRowAdapter extends BaseAdapter {

    public static ArrayList<ContactEntity> arrContact;
    Context con;
    ViewHolder holder = null;
    CheckBox ch;
    CompoundButton button;
    // int countSend=0;

    Integer arrImage[];

    public ContactListRowAdapter(Context applicationContext,
                                 ArrayList<ContactEntity> arrConatct) {
        // TODO Auto-generated constructor stub
        con = applicationContext;
        arrContact = arrConatct;
        // countSend=0;
    }

    public void notifychange() {
        notifyDataSetChanged();
    }

    public int getCount() {
        return arrContact.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int pos, View convertView, ViewGroup parent) {

        holder = null;
        LayoutInflater mInflater = (LayoutInflater) con
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.rowlistcontact, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView
                    .findViewById(R.id.txtContactName);
            holder.chkName = (CheckBox) convertView
                    .findViewById(R.id.checkContactCheckBox);
            holder.btnSend = (Button) convertView.findViewById(R.id.btnSend);

            holder.txtNumber = (TextView) convertView
                    .findViewById(R.id.tv_number);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(arrContact.get(pos).getConatactName());
        String strNumbers = "";
        if (arrContact.get(pos).getArrPhoneNumber().size() > 1) {

            for (int j = 0; j < arrContact.get(pos).getArrPhoneNumber().size(); j++) {

                String strMobile = arrContact.get(pos).getArrPhoneNumber()
                        .get(j).getPhoneNumber();

                strMobile = strMobile.replaceAll("-", "");
                strMobile = strMobile.replaceAll(" ", "");
                if (strMobile.length() > 10) {
                    int len = strMobile.length() - 10;
                    strMobile = strMobile.substring(len);
                    if (j == 0) {
                        strNumbers = strNumbers + strMobile + "\n";
                    } else if (j == 1) {

                        strNumbers = strNumbers + strMobile;
                    } else {

                        strNumbers = "\n" + strNumbers + strMobile;
                    }
                } else {
                    if (j == 0) {
                        strNumbers = strNumbers + strMobile + "\n";
                    } else if (j == arrContact.get(pos).getArrPhoneNumber()
                            .size() - 1) {

                        strNumbers = strNumbers + strMobile;
                    } else {

                        strNumbers = strNumbers + strMobile + "\n";
                    }
                }

            }
            holder.txtNumber.setText(strNumbers);
        } else {

            if (!(arrContact.get(pos).getArrPhoneNumber().size() < 1)) {

                String strMobile = arrContact.get(pos).getArrPhoneNumber()
                        .get(0).getPhoneNumber();
                strMobile = strMobile.replaceAll("-", "");
                strMobile = strMobile.replaceAll(" ", "");
                if (strMobile.length() > 10) {
                    int len = strMobile.length() - 10;
                    strMobile = strMobile.substring(len);
                    holder.txtNumber.setText(strMobile);
                } else {
                    holder.txtNumber.setText(strMobile);
                }

            }
        }

        for (int i = 0; i < arrContact.get(pos).getArrPhoneNumber().size(); i++) {
            if (arrContact.get(pos).getArrPhoneNumber().get(i).getCheck() == true) {
                holder.chkName.setChecked(true);
                break;
            } else {
                holder.chkName.setChecked(false);
            }
        }
        // holder.chkName.setOnCheckedChangeListener(new
        // CompoundButton.OnCheckedChangeListener()
        // {
        // @Override
        // public void onCheckedChanged(CompoundButton buttonView, boolean
        // isChecked) {
        // button=buttonView;
        // if(countSend < 3)
        // {
        // buttonView.setChecked(true);
        //
        // int Size=arrContact.get(pos).getArrPhoneNumber().size();
        // if(Size>1)
        // {
        // for(int i=0;i<Size;i++)
        // {
        // if(arrContact.get(pos).getArrPhoneNumber().get(i).getCheck()==true)
        // {
        // buttonView.setChecked(true);
        // break;
        // }
        // else
        // {
        // buttonView.setChecked(false);
        // }
        // }
        // showPop(con);
        // }
        // else
        // {
        // arrContact.get(pos).getArrPhoneNumber().get(0).setCheck(buttonView.isChecked());
        // if(buttonView.isChecked()==true)
        // ++countSend;
        // else if(buttonView.isChecked()==false)
        // --countSend;
        // else if(countSend==0)
        // countSend=0;
        // else if(countSend==-1)
        // countSend=0;
        // }
        //
        // }
        // else
        // {
        // Toast.makeText(con,"More the 5 is not allow",
        // Toast.LENGTH_LONG).show();
        // buttonView.setChecked(false);
        // }
        // }
        //
        // private void showPop(Context context)
        // {
        // LayoutInflater mInflater = (LayoutInflater) context
        // .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // final View pView = mInflater.inflate(R.layout.poplist,
        // null, false);
        // Button btnSubmit=(Button)pView.findViewById(R.id.butSubmit);
        // ListView listPhone=(ListView)pView.findViewById(R.id.listContact);
        // PopUpAdapter pop=new
        // PopUpAdapter(con,arrContact.get(pos).getArrPhoneNumber(),pos);
        // listPhone.setAdapter(pop);
        //
        // final PopupWindow popupWindow=new
        // PopupWindow(pView,WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT,true);
        // popupWindow.showAtLocation(pView,Gravity.CENTER,0,0);
        //
        // btnSubmit.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View v)
        // {
        // for(int i=0;i<arrContact.get(pos).getArrPhoneNumber().size();i++)
        // {
        // if(arrContact.get(pos).getArrPhoneNumber().get(i).getCheck()==true)
        // {
        //
        // button.setChecked(true);
        // // countSend++;
        //
        // break;
        // }
        // else
        // {
        // button.setChecked(false);
        // }
        // }
        // popupWindow.dismiss();
        //
        // }
        // });
        //
        // }
        // });
        // holder.txtName.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // // Toast.makeText(con, "onclick", Toast.LENGTH_LONG).show();
        //
        // // ch = (CheckBox) v;
        // // int Size = arrContact.get(pos).getArrPhoneNumber().size();
        // // if (Size > 1) {
        // // for (int i = 0; i < Size; i++) {
        // // if (arrContact.get(pos).getArrPhoneNumber().get(i)
        // // .getCheck() == true) {
        // // ch.setChecked(true);
        // // break;
        // // } else {
        // // ch.setChecked(false);
        // // }
        // // }
        // // showPop(con,pos);
        // // }
        // // else {
        // // arrContact.get(pos).getArrPhoneNumber().get(0)
        // // .setCheck(ch.isChecked());
        // // }
        // }
        // });

        holder.chkName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ch = (CheckBox) v;

                // if(countSend<5||(arrContact.get(pos).getArrPhoneNumber().get(0).getCheck()))
                // {
                // ch.setClickable(true);
                // ch.setEnabled(true);
                // ch.setFocusable(true);
                int Size = arrContact.get(pos).getArrPhoneNumber().size();
                if (Size > 1) {
                    for (int i = 0; i < Size; i++) {
                        if (arrContact.get(pos).getArrPhoneNumber().get(i)
                                .getCheck() == true) {
                            ch.setChecked(true);
                            break;
                        } else {
                            ch.setChecked(false);
                        }
                    }
                    showPop(con, pos);
                } else if (!(Size < 1)) {
                    arrContact.get(pos).getArrPhoneNumber().get(0)
                            .setCheck(ch.isChecked());
                    // if(ch.isChecked()==true)
                    // ++countSend;
                    // else if(ch.isChecked()==false)
                    // --countSend;
                    // else if(countSend==0)
                    // countSend=0;
                    // else if(countSend==-1)
                    // countSend=0;

                }
                // }
                // else
                // {
                //
                // Toast.makeText(con,"More the 5 is not allow",
                // Toast.LENGTH_LONG).show();
                // // holder.chkName.setClickable(false);
                // // holder.chkName.setEnabled(false);
                // // holder.chkName.setFocusable(false);
                // ch.setClickable(false);
                // ch.setEnabled(false);
                // // ch.setFocusable(false);
                //
                // }

            }

        });

        return convertView;
    }

    public void showPop(Context context, final int pos) {

//		LayoutInflater mInflater = (LayoutInflater) context
//				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//		final View pView = mInflater.inflate(R.layout.poplist, null, false);


        final Dialog alert = new Dialog(context, R.style.FullHeightDialog);
        alert.setContentView(R.layout.poplist);
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        alert.show();
        Button btnSubmit = (Button) alert.findViewById(R.id.butSubmit);
        Button butCancel = (Button) alert.findViewById(R.id.butCancel);
        ListView listPhone = (ListView) alert.findViewById(R.id.listContact);
        ContactPopUpAdapter pop = new ContactPopUpAdapter(con, arrContact.get(pos)
                .getArrPhoneNumber(), pos);
        listPhone.setAdapter(pop);

//		final PopupWindow popupWindow = new PopupWindow(pView,
//				WindowManager.LayoutParams.MATCH_PARENT,
//				WindowManager.LayoutParams.WRAP_CONTENT, true);
//		popupWindow.setBackgroundDrawable(new BitmapDrawable());
//		popupWindow.showAtLocation(pView, Gravity.CENTER, 0, 0);

        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arrContact.get(pos).getArrPhoneNumber()
                        .size(); i++) {
                    if (arrContact.get(pos).getArrPhoneNumber().get(i)
                            .getCheck() == true) {

                        ch.setChecked(true);
                        // countSend++;

                        break;
                    } else {
                        ch.setChecked(false);
                    }
                }
                alert.dismiss();

            }
        });

        butCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arrContact.get(pos).getArrPhoneNumber()
                        .size(); i++) {
                    if (arrContact.get(pos).getArrPhoneNumber().get(i)
                            .getCheck() == true) {
                        arrContact.get(pos).getArrPhoneNumber().get(i)
                                .setCheck(false);
                        ch.setChecked(false);
                    }
                }
                alert.dismiss();
            }
        });

    }

    public class ViewHolder {

        TextView txtName;
        TextView txtNumber;
        CheckBox chkName;
        Button btnSend;

    }

}
