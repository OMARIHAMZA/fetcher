package omari.hamza.fetcher.views.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.views.adapters.OffersAdapter;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Response;

public class OffersActivity extends MasterActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_offers);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);
        mRecyclerView = findViewById(R.id.mRecyclerView);
    }

    @Override
    protected void assignActions() {

    }

    @Override
    protected void getData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(new OffersAdapter(this));
    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }
}
