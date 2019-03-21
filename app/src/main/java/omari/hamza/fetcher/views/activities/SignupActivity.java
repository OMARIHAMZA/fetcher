package omari.hamza.fetcher.views.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Response;

public class SignupActivity extends MasterActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_signup);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
