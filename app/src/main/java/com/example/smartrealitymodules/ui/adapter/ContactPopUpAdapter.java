package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.share.PhoneNumber;

import java.util.ArrayList;


public class ContactPopUpAdapter extends BaseAdapter {

    Context con;
    ArrayList<PhoneNumber> arrPhone;
    int parentPositon;
    ViewHolder holder = null;

    public ContactPopUpAdapter(Context applicationContext, ArrayList<PhoneNumber> arrPhone, int parentPositon) {
        // TODO Auto-generated constructor stub
        con = applicationContext;
        this.arrPhone = arrPhone;
        this.parentPositon = parentPositon;
    }

    public int getCount() {
        return arrPhone.size();
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
            convertView = mInflater.inflate(R.layout.contactpopupadapter, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.txtContactName);
            holder.chkName = (CheckBox) convertView.findViewById(R.id.checkContactCheckBox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (arrPhone.get(pos).getCheck() == true) {
            holder.chkName.setChecked(true);

        } else {
            holder.chkName.setChecked(false);
        }

        holder.chkName.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckBox ch1 = (CheckBox) v;
//				if(ch1.isChecked())
//				{
//					if(ListRowAdapter.arrContact.get(parentPositon).getArrPhoneNumber().get(pos).getCheck()==true)
                ContactListRowAdapter.arrContact.get(parentPositon).getArrPhoneNumber().get(pos).setCheck(ch1.isChecked());
//					if(ch1.isChecked()==true)
//					ListRowAdapter.countSend++;
//					else if(ListRowAdapter.countSend==0)
//					ListRowAdapter.countSend=0;
//					else
//						ListRowAdapter.countSend--;
//				}

            }
        });
        holder.txtName.setText(arrPhone.get(pos).getPhoneNumber());

        return convertView;
    }

    public class ViewHolder {

        TextView txtName;
        CheckBox chkName;
        Button btnSubmit;

    }

}
