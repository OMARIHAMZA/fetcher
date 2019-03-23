package omari.hamza.fetcher.views.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Response;

public class CompanyHomeActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_company_home);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {

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
