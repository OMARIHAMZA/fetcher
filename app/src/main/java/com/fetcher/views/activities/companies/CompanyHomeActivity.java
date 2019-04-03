package com.fetcher.views.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import com.fetcher.R;
import com.fetcher.core.utils.UserUtils;
import com.fetcher.views.activities.LoginActivity;
import com.fetcher.views.fragments.company.MyOffersFragment;
import com.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Response;

public class CompanyHomeActivity extends MasterActivity {

    private Toolbar mToolbar;
    private TextView nameTextView;
    private FloatingActionMenu actionMenu;
    private FloatingActionButton createOfferButton, profileButton, refreshButton, logoutButton, staffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_company_home);
        super.onCreate(savedInstanceState);
        setupSpaceNavigationView();
        loadFragment(new MyOffersFragment());
    }

    private void setupSpaceNavigationView() {

    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);
        nameTextView = findViewById(R.id.comany_name_textView);
        createOfferButton = findViewById(R.id.create_offer_fab);
        profileButton = findViewById(R.id.profile_fab);
        refreshButton = findViewById(R.id.refresh_fab);
        logoutButton = findViewById(R.id.logout_fab);
        actionMenu = findViewById(R.id.menu);
        staffButton = findViewById(R.id.staff_fab);
    }

    @Override
    protected void assignActions() {
        createOfferButton.setOnClickListener(v -> {
            actionMenu.close(true);
            startActivity(new Intent(getApplicationContext(), CreateOfferActivity.class));
        });

        refreshButton.setOnClickListener(v -> {
            actionMenu.close(true);
            loadFragment(new MyOffersFragment());
        });

        logoutButton.setOnClickListener(v -> {
            actionMenu.close(false);
            UserUtils.logoutUser(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

        staffButton.setOnClickListener(v -> {
            actionMenu.close(true);
            startActivity(new Intent(getApplicationContext(), StaffActivity.class));
        });

        profileButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CompanyProfileActivity.class)));
    }

    @Override
    protected void getData() {
        nameTextView.setText(getString(R.string.my_offers));
    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }
}
