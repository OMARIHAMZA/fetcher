package omari.hamza.fetcher.views.activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.views.fragments.CategoriesFragment;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends MasterActivity {

    private AHBottomNavigation mBottomNavigation;

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
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

    }

    @Override
    protected void assignUIReferences() {
        mBottomNavigation = findViewById(R.id.AHBottomNavigation);
    }

    @Override
    protected void assignActions() {

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
}
