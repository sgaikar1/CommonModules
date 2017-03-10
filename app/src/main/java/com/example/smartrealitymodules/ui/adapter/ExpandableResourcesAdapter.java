package com.example.smartrealitymodules.ui.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.databinding.RowExpandableResourceGroupBinding;
import com.example.smartrealitymodules.databinding.RowExpandableResourceItemBinding;
import com.example.smartrealitymodules.models.response.GetResourcesResponse;
import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;

/**
 * Created by user on 10/3/17.
 */
public class ExpandableResourcesAdapter extends BaseExpandableListAdapter {

    private final OnItemClickListener listener;
    private Activity context;
    ArrayList<GetResourcesResponse.Data> mArraylist;

    public ExpandableResourcesAdapter(Activity context, ArrayList<GetResourcesResponse.Data> mArraylist, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.mArraylist = mArraylist;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.mArraylist.get(groupPosition).getContact_no().get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosititon) {
        return childPosititon;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
//        final String expandedListText = (String) getChild(groupPosition, childPosition);

        ChildViewHolder viewHolder;
        if (convertView == null) {
            RowExpandableResourceItemBinding mRowExpandableResourceItemBinding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.row_expandable_resource_item, parent, false);
            convertView = mRowExpandableResourceItemBinding.getRoot();
            viewHolder = new ChildViewHolder();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }



        viewHolder.rowExpandableResourceItemBinding.tvNumEmergency.setText("Number "+(childPosition+1));
        viewHolder.rowExpandableResourceItemBinding.tvNumberEmergency.setText(mArraylist.get(groupPosition).getContact_no().get(childPosition).getNumber());

        viewHolder.rowExpandableResourceItemBinding.imgContactsCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(mArraylist.get(groupPosition).getContact_no().get(childPosition).getNumber());

            }
        });


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mArraylist.get(groupPosition).getContact_no().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mArraylist.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mArraylist.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
//        String listTitle = (String) getGroup(groupPosition);

        GroupViewHolder viewHolder;
        if (convertView == null) {
            RowExpandableResourceGroupBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(),R.layout.row_expandable_resource_group,parent,false);
            convertView  = binding.getRoot();
            viewHolder = new GroupViewHolder();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }

        viewHolder.binding.tvProfileEmergency.setText(mArraylist.get(groupPosition).getDepartment_name());
        new Utils().loadImageInImageview(context, mArraylist.get(groupPosition).getImage(),viewHolder.binding.ivProfileEmergency);

        if(isExpanded){
            viewHolder.binding.ivIconEmergency.setImageResource(R.drawable.ic_down_arrow);
        }else{
            viewHolder.binding.ivIconEmergency.setImageResource(R.drawable.ic_forward);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosititon) {
        return true;
    }

    private class GroupViewHolder {
        RowExpandableResourceGroupBinding binding;
    }

    private class ChildViewHolder {

        RowExpandableResourceItemBinding rowExpandableResourceItemBinding;
    }

    public interface OnItemClickListener {
        void onClick(String number);
    }

}