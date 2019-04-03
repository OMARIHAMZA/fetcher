package com.fetcher.views.activities.users;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fetcher.R;
import com.fetcher.core.controllers.UserController;
import com.fetcher.core.models.Offer;
import com.fetcher.core.models.response.OffersResponse;
import com.fetcher.core.utils.LoadingDialog;
import com.fetcher.views.adapters.OffersAdapter;
import com.fetcher.views.masters.MasterActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends MasterActivity {

    private Toolbar mToolbar;
    private MaterialEditText searchEditText;
    private RecyclerView mRecyclerView;
    private LoadingDialog mLoadingDialog;
    private RadioButton jobsRadioButton, internshipsRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        mLoadingDialog = new LoadingDialog(this);
        mToolbar = findViewById(R.id.mToolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        searchEditText = findViewById(R.id.query_editText);
        jobsRadioButton = findViewById(R.id.job_radioButton);
        internshipsRadioButton = findViewById(R.id.internship_radioButton);
    }

    @Override
    protected void assignActions() {
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });

        jobsRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> internshipsRadioButton.setChecked(!isChecked));
        internshipsRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> jobsRadioButton.setChecked(!isChecked));
    }

    private void performSearch() {
        mRecyclerView.setAdapter(new OffersAdapter(this, new ArrayList<>()));
        if (jobsRadioButton.isChecked()) {
            mLoadingDialog.show();
            UserController.searchJobs(getApplicationContext(), searchEditText.getText().toString(), new Callback<OffersResponse>() {
                @Override
                public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                    mLoadingDialog.dismiss();
                    if (response.isSuccessful()) {
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        mRecyclerView.setAdapter(new OffersAdapter(SearchActivity.this, response.body().getOffers()));
                    } else {
                        Toast.makeText(SearchActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<OffersResponse> call, Throwable t) {
                    mLoadingDialog.dismiss();
                    Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (internshipsRadioButton.isChecked()) {
            mLoadingDialog.show();
            UserController.searchTrainings(getApplicationContext(), searchEditText.getText().toString(), new Callback<OffersResponse>() {
                @Override
                public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                    mLoadingDialog.dismiss();
                    if (response.isSuccessful()) {
                        for (Offer offer : response.body().getOffers()) {
                            offer.setTraining(true);
                        }
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        mRecyclerView.setAdapter(new OffersAdapter(SearchActivity.this, response.body().getOffers()));
                    } else {
                        Toast.makeText(SearchActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<OffersResponse> call, Throwable t) {
                    mLoadingDialog.dismiss();
                    Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Select Search Category", Toast.LENGTH_SHORT).show();
        }
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
