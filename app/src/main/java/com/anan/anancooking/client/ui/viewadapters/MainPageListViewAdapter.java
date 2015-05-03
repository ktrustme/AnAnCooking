package com.anan.anancooking.client.ui.viewadapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.RecipePreviewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuoxin on 4/4/15.
 */
public class MainPageListViewAdapter extends ArrayAdapter<RecipePreviewImplementation> {


    Context context;

    public MainPageListViewAdapter(Context context, int resourceId, //resourceId=your layout
                                   List<RecipePreviewImplementation> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private static class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDescription;
        TextView recipeTime;
        TextView recipeId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        RecipePreviewInterface rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_briefintroduction, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title_recipe);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.desc_recipe);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon_recipe);
            holder.recipeId = (TextView) convertView.findViewById(R.id.hidden_recipe_id);
            holder.recipeTime = (TextView) convertView.findViewById(R.id.preview_time);
            //holder.recipeId = (TextView)
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        //the following line should be performed in a AsynTask
        holder.txtTitle.setText(rowItem.getName());
        holder.txtDescription.setText(rowItem.getIngredients());
        holder.recipeId.setText(rowItem.getRecipeId());
        holder.recipeTime.setText(""+rowItem.getTime());
        //set image
        if(rowItem.getPreviewByteCode()==null)
            holder.imageView.setImageResource(R.drawable.ic_menu_rotate);
        else
            holder.imageView.setImageDrawable(ImageByteArrayToDrawableConverter.convert(rowItem.getPreviewByteCode()));

        return convertView;
    }

    private class testRunnable implements Runnable {
        ViewHolder vh = null;
        int sourceId = 0;

        public testRunnable(ViewHolder viewHolder, int sourceId) {
            this.vh = viewHolder;
            this.sourceId = sourceId;
        }

        @Override
        public void run() {
            vh.imageView.setImageResource(sourceId);

        }
    }
}