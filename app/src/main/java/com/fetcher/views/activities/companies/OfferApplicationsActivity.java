package com.fetcher.views.activities.companies;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import com.fetcher.R;
import com.fetcher.core.controllers.CompanyController;
import com.fetcher.core.models.Offer;
import com.fetcher.core.models.OfferApplication;
import com.fetcher.core.models.response.OfferApplicationsResponse;
import com.fetcher.core.utils.LoadingDialog;
import com.fetcher.views.adapters.OfferApplicationsAdapter;
import com.fetcher.views.adapters.OffersCallback;
import com.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Response;

public class OfferApplicationsActivity extends MasterActivity implements OffersCallback {

    private RecyclerView mRecyclerView;
    private Offer offer;
    private LoadingDialog mLoadingDialog;
    private ArrayList<OfferApplication> applications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_application);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        offer = (Offer) getIntent().getSerializableExtra("offer");
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mLoadingDialog = new LoadingDialog(this);

        Toolbar mToolbar = findViewById(R.id.mToolbar);
        mToolbar.setTitle(offer.getTitle() + " Applications");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void assignActions() {

    }

    @Override
    protected void getData() {
        mLoadingDialog.show();
        if (offer.isTraining()) {
            CompanyController.getTrainingApplications(getApplicationContext(), offer.getTrainingId(), this);
        } else {
            CompanyController.getJobApplications(getApplicationContext(), offer.getJobId(), this);
        }
    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {
        mLoadingDialog.dismiss();
        if (response.isSuccessful()) {
            applications.addAll(((OfferApplicationsResponse) response.body()).getApplications());
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mRecyclerView.setAdapter(new OfferApplicationsAdapter(this, applications, offer.isTraining(), this));
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                    DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(dividerItemDecoration);
        } else {
            Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {
        mLoadingDialog.dismiss();
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshOffers() {
        applications.clear();
        mRecyclerView.setAdapter(new OfferApplicationsAdapter(this, new ArrayList<>(), false, null));
        getData();
    }
}
