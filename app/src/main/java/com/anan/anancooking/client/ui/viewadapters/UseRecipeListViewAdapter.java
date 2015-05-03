package com.anan.anancooking.client.ui.viewadapters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anan.anancooking.R;

import com.anan.anancooking.model.Step;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuoxin on 4/4/15.
 * Here we don't need asyn task, since all data has been downloaded in the previous Activity
 */
public class UseRecipeListViewAdapter extends ArrayAdapter<Step> {


    Context context;

    public UseRecipeListViewAdapter(Context context, int resourceId, //resourceId=your layout
                                    List<Step> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDescription;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        //Get the i'th step
        Step rowItem = getItem(position);
        //Inflate corresponding layout
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_single_step, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title_recipe);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.desc_recipe);
            holder.imageView = ((ImageView) convertView.findViewById(R.id.icon_recipe));
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        //if (holder.imageView.getDrawable() == null) {
        DisplayMetrics dm = new DisplayMetrics();
        int width = parent.getMeasuredWidth();

        //Set the listview image, text, etc
        holder.txtDescription.setText(rowItem.getDescription());
        holder.txtTitle.setText("Step " +position);
        //holder.imageView.setImageResource(R.drawable.ic_menu_rotate);

        if(rowItem.getBytes()==null){
            //holder.imageView.setImageResource(R.drawable.ic_menu_rotate);
            holder.imageView.setVisibility(View.GONE);
        }else{
            holder.imageView.setImageDrawable(ImageByteArrayToDrawableConverter.convert(rowItem.getBytes()));
        }
        holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(width, width * 3 / 4));
        return convertView;
    }

    /*
    private class testRunnable implements Runnable {
        ViewHolder vh = null;
        int sourceId = 0;

        public testRunnable(ViewHolder viewHolder, int sourceId) {
            this.vh = viewHolder;
            this.sourceId = sourceId;
        }
        @Override
        public void run() {
            vh.imageView.setImageResource(R.drawable.ic_menu_rotate);
        }
    }
    */
}