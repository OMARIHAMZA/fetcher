package com.fetcher.views.activities.users;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import com.fetcher.R;
import com.fetcher.core.utils.UserUtils;
import com.fetcher.views.activities.LoginActivity;
import com.fetcher.views.fragments.user.CategoriesFragment;
import com.fetcher.views.fragments.user.CompaniesFragment;
import com.fetcher.views.fragments.user.InboxFragment;
import com.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Response;

public class UserHomeActivity extends MasterActivity {

    private CardView profileCardView;
    private AHBottomNavigation mBottomNavigation;
    private ImageButton searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_user);
        super.onCreate(savedInstanceState);
        setupBottomNavigation();
        loadFragment(new CategoriesFragment());
    }

    private void setupBottomNavigation() {
        mBottomNavigation.addItem(new AHBottomNavigationItem(getString(R.string.home), getDrawable(R.drawable.ic_home), Color.parseColor("#AA50AB")));
        mBottomNavigation.addItem(new AHBottomNavigationItem(getString(R.string.inbox), getDrawable(R.drawable.ic_chat), Color.parseColor("#F24675")));
        mBottomNavigation.addItem(new AHBottomNavigationItem(getString(R.string.rating), getDrawable(R.drawable.ic_work_black_24dp), Color.parseColor("#7558D2")));


        // Change colors
        mBottomNavigation.setAccentColor((getResources().getColor(R.color.colorAccent)));

        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        mBottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position) {
                case 0: {
                    ((TextView) findViewById(R.id.title_textView)).setText("Categories");
                    loadFragment(new CategoriesFragment());
                    break;
                }

                case 1: {
                    ((TextView) findViewById(R.id.title_textView)).setText("Inbox");
                    loadFragment(new InboxFragment());
                    break;
                }

                case 2: {
                    ((TextView) findViewById(R.id.title_textView)).setText("Companies");
                    loadFragment(new CompaniesFragment());
                    break;
                }
            }
            return true;
        });

        mBottomNavigation.setColored(true);
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
    }

    @Override
    protected void assignUIReferences() {
        mBottomNavigation = findViewById(R.id.AHBottomNavigation);
        profileCardView = findViewById(R.id.profile_cardView);
        searchButton = findViewById(R.id.search_button);
    }

    @Override
    protected void assignActions() {
        profileCardView.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), UserProfileActivity.class)));
        searchButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout_item) {
            UserUtils.logoutUser(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
