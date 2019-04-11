package com.fetcher.views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fetcher.R;
import com.fetcher.core.controllers.UserController;
import com.fetcher.core.models.User;
import com.fetcher.core.models.response.MessagesResponse;
import com.fetcher.core.models.Offer;
import com.fetcher.core.utils.LoadingDialog;
import com.fetcher.core.utils.UserUtils;
import com.fetcher.views.activities.companies.CompanyProfileActivity;
import com.fetcher.views.activities.companies.OfferApplicationsActivity;
import com.fetcher.views.masters.MasterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferDetailsActivity extends MasterActivity {

    private Toolbar mToolbar;
    private TextView jobTitleTextView, companyNameTextView, locationTextView, typeTextView, descriptionTextView;
    private Button applyButton, viewCompanyButton;
    private Offer offer;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_offer_details);
        super.onCreate(savedInstanceState);
        if (UserUtils.getLoggedUser(getApplicationContext()).getType().equalsIgnoreCase("2")) {
            applyButton.setText(getString(R.string.view_applications));
            applyButton.setOnClickListener(v -> {
                Intent mIntent = new Intent(getApplicationContext(), OfferApplicationsActivity.class);
                mIntent.putExtra("offer", offer);
                startActivity(mIntent);
            });
        }
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);


        offer = (Offer) getIntent().getSerializableExtra("offer");
        mToolbar.setTitle(offer.getTitle());
        setSupportActionBar(mToolbar);
        //noinspection all
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        jobTitleTextView = findViewById(R.id.offer_title_textView);
        companyNameTextView = findViewById(R.id.company_textView);
        locationTextView = findViewById(R.id.location_textView);
        typeTextView = findViewById(R.id.offer_type_textView);
        descriptionTextView = findViewById(R.id.offer_description_textView);
        applyButton = findViewById(R.id.button);
        viewCompanyButton = findViewById(R.id.view_company_profile_button);


        descriptionTextView.setMovementMethod(new ScrollingMovementMethod());

        mLoadingDialog = new LoadingDialog(this);

    }

    @Override
    protected void assignActions() {

        applyButton.setOnClickListener(v -> {

            mLoadingDialog.show();

            if (offer.isTraining()) {
                UserController.applyForTraining(getApplicationContext(), offer.getTrainingId() == 0 ? offer.getTrainingId() : offer.getSearchResponseId(), new Callback<MessagesResponse>() {
                    @Override
                    public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                        mLoadingDialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(OfferDetailsActivity.this, getString(R.string.SUCCESS), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(OfferDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessagesResponse> call, Throwable t) {
                        mLoadingDialog.dismiss();
                        Toast.makeText(OfferDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                UserController.applyForJob(getApplicationContext(), offer.getJobId() == 0 ? offer.getSearchResponseId() : offer.getJobId(), new Callback<MessagesResponse>() {
                    @Override
                    public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                        mLoadingDialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(OfferDetailsActivity.this, getString(R.string.SUCCESS), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(OfferDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessagesResponse> call, Throwable t) {
                        mLoadingDialog.dismiss();
                        Toast.makeText(OfferDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        viewCompanyButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CompanyProfileActivity.class);
            User user = new User();
            user.setId(offer.getCompanyId());
            intent.putExtra("company", user);
            startActivity(intent);
        });

    }

    @Override
    protected void getData() {
        jobTitleTextView.setText(offer.getTitle());
        companyNameTextView.setText(offer.getCompanyName());
        locationTextView.setText(offer.getLocation());
        typeTextView.setText(offer.isTraining() ? getString(R.string.training) : getString(R.string.job_offer));
        //noinspection all
        descriptionTextView.setText("Requirements:\n\n" + offer.getRequirements()
                + "\n\nSalary: " + offer.getSalary() + " Perday"
                + "\n\nDays: " + offer.getDays());


    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }
}
