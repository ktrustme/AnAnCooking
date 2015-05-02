package com.anan.anancooking.client.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.customLayout.SlidingTabLayout;
import com.anan.anancooking.client.ui.listeners.CreateRecipeButtonListener;
import com.anan.anancooking.client.ui.listeners.SearchSubmitButtonListener;
import com.anan.anancooking.client.ui.viewadapters.MainPagePagerChangeListener;
import com.anan.anancooking.client.ui.viewadapters.MainPagerAdapter;

/**
 * Created by kuoxin on 4/3/15.
 */
public class MainPageActivity extends FragmentActivity {
    MainPagerAdapter fpa = null;
    ViewPager mViewPager = null;
    Button createRecipeButton = null;
    SlidingTabLayout mSlidingTabLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        setTabs();
        setButton();
    }

    /**
     * Buttons initialization
     */
    private void setButton() {
        createRecipeButton = (Button) findViewById(R.id.button_create_recipe);
        createRecipeButton.setOnClickListener(new CreateRecipeButtonListener());

    }

    /**
     * Set the action bar tabs.
     */
    private void setTabs() {
        ActionBar actionBar = getActionBar();
        fpa = new MainPagerAdapter(getSupportFragmentManager(), this);

        fpa.addFragment(RecommendationFragment.newInstance(), "Recommend");
        fpa.addFragment(HistoryFragment.newInstance(), "History");
        fpa.addFragment(RecommendationFragment.newInstance(), "Favorite");

        mViewPager = (ViewPager) findViewById(R.id.viewpager_mainpage);
        mViewPager.setAdapter(fpa);
        mViewPager.setOnPageChangeListener(new MainPagePagerChangeListener(this));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    /**
     * Inflate ActionBar's layout
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainpage, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        ((SearchView) searchItem.getActionView()).setSubmitButtonEnabled(true);
        ((SearchView) searchItem.getActionView()).setOnQueryTextListener(new SearchSubmitButtonListener(this));

        return super.onCreateOptionsMenu(menu);

    }

    /**
     * Listeners for action bar activity, currently useless.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * open setting activity page
     */
    private void openSettings() {
        Intent intent = new Intent(this, com.anan.anancooking.client.ui.SettingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }
}
