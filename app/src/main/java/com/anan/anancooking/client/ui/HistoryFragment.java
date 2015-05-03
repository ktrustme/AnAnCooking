package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.listeners.HistoryPageListViewItemClickListener;
import com.anan.anancooking.client.ui.listeners.MainPageListViewItemClickListener;
import com.anan.anancooking.client.ui.viewadapters.HistoryDatabaseCursorAdapter;
import com.anan.anancooking.client.util.DbUtil;
import com.anan.anancooking.client.util.DeleteRecipeFromDBCallbackInterface;
import com.anan.anancooking.client.util.StorageSchema;


public class HistoryFragment extends Fragment implements DeleteRecipeFromDBCallbackInterface{
    private ListView listView = null;
    private DbUtil dbUtil = null;
    private Cursor cursor = null;
    String[] fromColumns = {StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID,
            StorageSchema.DataEntry.COLUMN_NAME_RECIPE_NAME,
            StorageSchema.DataEntry.COLUMN_NAME_TIME,
            StorageSchema.DataEntry.COLUMN_NAME_INGREDIENTS,
            StorageSchema.DataEntry.COLUMN_NAME_DESCRIPTION,
            StorageSchema.DataEntry.COLUMN_NAME_PREVIEW_IMAGE,
    };

    int[] toViews = {R.id.hidden_recipe_id, R.id.title_recipe, R.id.preview_time, R.id.desc_recipe, R.id.hidden_recipe_description,R.id.icon_recipe};


    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    private void setListView() {
        //DbUtil dbUtil = new DbUtil(this,null);
        listView = (ListView) (getView().findViewById(R.id.listview_history));
        cursor = this.dbUtil.getHistory();
        HistoryDatabaseCursorAdapter adapter = new HistoryDatabaseCursorAdapter(this, getActivity(),
                R.layout.list_item_briefintroduction, cursor, fromColumns, toViews, 0);
        adapter.setViewBinder(new ImageViewBinder());
        listView.setAdapter(adapter);
        /*
        listView.setClickable(true);
        listView.setFocusable(true);
        */
        listView.setOnItemClickListener(new HistoryPageListViewItemClickListener(this.getActivity()));
    }

    private void setDb() {
        this.dbUtil = new DbUtil(getActivity());
    }

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setDb();
        setListView();
        refreshListView();
        //Toast.makeText(getActivity(),"View created...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onResume(){
        super.onResume();
        refreshListView();
    }

    public void deleteRecipe(String recipeId) {
        dbUtil.deleteRecipe(recipeId);
        refreshListView();
        Toast.makeText(getActivity(), "Successfully deleted", Toast.LENGTH_SHORT).show();
    }

    private void refreshListView() {
        cursor = this.dbUtil.getHistory();
        HistoryDatabaseCursorAdapter adapter = new HistoryDatabaseCursorAdapter(this,getActivity(),
                R.layout.list_item_briefintroduction_with_delete_button, cursor, fromColumns, toViews, 0);
        adapter.setViewBinder(new ImageViewBinder());
        listView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



    private class ImageViewBinder implements SimpleCursorAdapter.ViewBinder {
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            if (view instanceof ImageView) {
                //System.out.println("Inside ImageViewBinder");
                byte[] imageBytes = cursor.getBlob(columnIndex);
                ((ImageView) view).setImageBitmap(BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length));
                return true;
            }
            // For others, we simply return false so that the default binding
            // happens.
            return false;
        }
    }
}
