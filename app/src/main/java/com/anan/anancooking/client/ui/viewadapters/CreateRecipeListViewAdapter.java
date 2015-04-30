package com.anan.anancooking.client.ui.viewadapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.ImagePickerActivity;
import com.anan.anancooking.client.ui.RecipeCreationActivity;
import com.anan.anancooking.model.Step;

import java.util.ArrayList;

/**
 * Created by zihsiangsyu on 4/4/15.
 */
public class CreateRecipeListViewAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Step> list = new ArrayList<Step>();
    private Context context;


    public CreateRecipeListViewAdapter(ArrayList<Step> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ArrayList<Step> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        //View view;
        //final ViewHolder vh = new ViewHolder();

        if (view == null) {
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //view = inflater.inflate(R.layout.recipe_create_list_layout, null);
            view = LayoutInflater.from(this.context)
                    .inflate(R.layout.recipe_create_list_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.description_list_element);
            viewHolder.textView.setTag(list.get(position));

            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);


            view.setTag(viewHolder);

        } else {

            ((ViewHolder) view.getTag()).textView.setTag(list.get(position).getDescription());
        }

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);
        Button insertBtn = (Button) view.findViewById(R.id.save_recipe_btn);
        //Button imgPickBtn = (Button) view.findViewById(R.id.image_pick_btn);
        Button updateStep = (Button) view.findViewById(R.id.update_btn);


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                //descriptionList.remove(position);
                notifyDataSetChanged();
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Step step = new Step();
                ((RecipeCreationActivity)v.getContext()).openInsertionStepDialog(step, position);
            }


        });

        updateStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Step step = list.get(position);
            System.out.println("Update position:" + position);
            ((RecipeCreationActivity)v.getContext()).openUpdateStepDialog(step, position);
            }
        });

        // kind of setting the title
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        StringBuilder titleSB = new StringBuilder("Step");
        listItemText.setText((titleSB.append(position)).toString());

        // set the text view
        TextView editText = (TextView) view.findViewById(R.id.description_list_element);
        editText.setText(list.get(position).getDescription());

        // set the image view
        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView);
        byte[] imageByteArray = list.get(position).getBytes();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
        imageView2.setImageBitmap(bitmap);
        return view;

    }


    private class ViewHolder {
        private TextView textView;
        private ImageView imageView;
    }



}
