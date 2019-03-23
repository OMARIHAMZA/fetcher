package omari.hamza.fetcher.views.activities;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.AppController;
import omari.hamza.fetcher.core.models.Category;
import omari.hamza.fetcher.core.models.Offer;
import omari.hamza.fetcher.core.models.response.OffersResponse;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.views.adapters.OffersAdapter;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffersActivity extends MasterActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private Category category;
    private LoadingDialog mLoadingDialog;
    private ArrayList<Offer> offers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_offers);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        category = (Category) getIntent().getSerializableExtra("category");
        mToolbar.setTitle(category.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void assignActions() {

    }

    @Override
    protected void getData() {
        mLoadingDialog.show();
        AppController.getJobOffers(getApplicationContext(), category.getId(), new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                if (response.isSuccessful()){
                    for (Offer offer: response.body().getOffers()){
                        offer.setTraining(false);
                    }
                    offers.addAll(response.body().getOffers());
                    AppController.getTrainingOffers(getApplicationContext(), category.getId(), new Callback<OffersResponse>() {
                        @Override
                        public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                            mLoadingDialog.dismiss();
                            if (response.isSuccessful()){
                                for (Offer offer: response.body().getOffers()){
                                    offer.setTraining(true);
                                }
                                offers.addAll(response.body().getOffers());
                                mRecyclerView.setAdapter(new OffersAdapter(OffersActivity.this, offers));
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            }else{
                                Toast.makeText(OffersActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<OffersResponse> call, Throwable t) {
                            mLoadingDialog.dismiss();
                            Toast.makeText(OffersActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    mLoadingDialog.dismiss();
                    Toast.makeText(OffersActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OffersResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(OffersActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }
}
