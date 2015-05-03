package com.anan.anancooking.client.ui.viewadapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.util.DeleteRecipeFromDBCallbackInterface;


/**
 * Created by kuoxin on 4/6/15.
 */
public class HistoryDatabaseCursorAdapter extends SimpleCursorAdapter {
    private DeleteRecipeFromDBCallbackInterface callback;
    public HistoryDatabaseCursorAdapter(DeleteRecipeFromDBCallbackInterface callback, Context context, int layout, Cursor c, String[] from, int[] to, int flag){
        super(context, layout, c, from, to, flag);
        this.callback = callback;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        convertView = super.getView(position, convertView, parent);
        //convertView.setTag(position);

        final View tmpView = convertView;


        (convertView.findViewById(R.id.delete_button)) .setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //parent.getChildAt();
                        //((EditText) view.getRootView().findViewById(R.id.student_id_edit)).getText().toString()
                        callback.deleteRecipe(((TextView)tmpView.findViewById(R.id.hidden_recipe_id)).getText().toString());
                    }
                }
        );


        return convertView;
    }
}
