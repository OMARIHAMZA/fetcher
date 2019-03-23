package omari.hamza.fetcher.views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.utils.UserUtils;
import omari.hamza.fetcher.views.fragments.CategoriesFragment;
import omari.hamza.fetcher.views.fragments.InboxFragment;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Response;

public class UserHomeActivity extends MasterActivity {

    private CardView profileCardView;
    private AHBottomNavigation mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_user);
        super.onCreate(savedInstanceState);
        setupBottomNavigation();
        loadFragment(new CategoriesFragment());
    }

    private void setupBottomNavigation() {
        mBottomNavigation.addItem(new AHBottomNavigationItem(getString(R.string.home), getDrawable(R.drawable.ic_home)));
        mBottomNavigation.addItem(new AHBottomNavigationItem(getString(R.string.inbox), getDrawable(R.drawable.ic_chat)));


        // Change colors
        mBottomNavigation.setAccentColor((getResources().getColor(R.color.colorAccent)));

        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        mBottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position){
                case 0:{
                    loadFragment(new CategoriesFragment());
                    break;
                }

                case 1:{
                    loadFragment(new InboxFragment());
                    break;
                }
            }
            return true;
        });

    }

    @Override
    protected void assignUIReferences() {
        mBottomNavigation = findViewById(R.id.AHBottomNavigation);
        profileCardView = findViewById(R.id.profile_cardView);
    }

    @Override
    protected void assignActions() {
        profileCardView.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ProfileActivity.class)));
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
