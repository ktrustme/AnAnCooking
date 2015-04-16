package com.anan.anancooking.client.ui.viewadapters;

import android.content.Context;
import android.content.Intent;
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
import com.anan.anancooking.model.Step;

import java.util.ArrayList;

/**
 * Created by zihsiangsyu on 4/4/15.
 */
public class CreateRecipeListViewAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Step> list = new ArrayList<Step>();
    //private ArrayList<String> descriptionList = new ArrayList<String>();
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
        //return list.get(pos).getId();
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
            viewHolder.editText = (EditText) view.findViewById(R.id.description_list_element);
            viewHolder.editText.setTag(list.get(position));

            view.setTag(viewHolder);

        } else {

            ((ViewHolder) view.getTag()).editText.setTag(list.get(position).getDescription());
            //viewHolder = (ViewHolder) view.getTag();

        }


        //Step element = (Step) vh.editText.getTag();

        //Handle TextView and display string from your list
        //TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        //listItemText.setText("Step" + position);

        //viewHolder = (ViewHolder) view.getTag();

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);
        Button addBtn = (Button) view.findViewById(R.id.add_btn);
        Button imgPickBtn = (Button) view.findViewById(R.id.image_pick_btn);
        Button saveStep = (Button) view.findViewById(R.id.save_step);





        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                //descriptionList.remove(position);
                notifyDataSetChanged();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Step step = new Step();
                list.add(position, step);
                //descriptionList.add(editText.getText().toString());
                notifyDataSetChanged();
            }


        });

        imgPickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ImagePickerActivity.class);
                context.startActivity(intent);
                return;
            }
        });

        saveStep.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                EditText et = (EditText) v.getRootView().findViewById(R.id.description_list_element);

                String temp = et.getText().toString();
                list.get(position).setDescription(temp);
            }
        });

            /*Step item = (Step) getItem(position);
            if(item!=null){
                viewHolder.editText.setText(list.get(position).getDescription());

            }*/

        //viewHolder = (ViewHolder) view.getTag();
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        int dif = getCount() - position;
        listItemText.setText("Step" + dif);

        EditText editText = (EditText) view.findViewById(R.id.description_list_element);
        editText.setText(list.get(position).getDescription());

        //vh.editText.setText(list.get(position).getDescription());
        //vh.imageView.setImageBitmap(list.get(position).getImageView());
        //view.setTag(vh);
        //view.setTag(viewHolder);
        return view;

    }


    private class ViewHolder {
        private EditText editText;
        private ImageView imageView;
    }


}
