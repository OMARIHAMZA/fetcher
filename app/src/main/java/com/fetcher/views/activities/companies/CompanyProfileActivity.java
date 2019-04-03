package com.fetcher.views.activities.companies;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import com.fetcher.R;
import com.fetcher.core.controllers.CompanyController;
import com.fetcher.core.models.Rating;
import com.fetcher.core.models.User;
import com.fetcher.core.models.response.MessageResponse;
import com.fetcher.core.models.response.RatingResponse;
import com.fetcher.core.utils.CompanyInfoDialog;
import com.fetcher.core.utils.LoadingDialog;
import com.fetcher.core.utils.UserUtils;
import com.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyProfileActivity extends MasterActivity {

    private Toolbar mToolbar;
    private TextView nameTextView, websiteTextView, emailTextView, mobileTextView, addressTextView;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_company_profile);
        super.onCreate(savedInstanceState);
        getCompanyRating();
    }

    private void getCompanyRating() {
        CompanyController.getCompanyRating(getApplicationContext(), new Callback<RatingResponse>() {
            @Override
            public void onResponse(Call<RatingResponse> call, Response<RatingResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getRatings() == null || response.body().getRatings().isEmpty()) return;
                    int avg = 0;
                    for (Rating rating: response.body().getRatings()){
                        avg += rating.getRating();
                    }
                    avg /= response.body().getRatings().size();
                    nameTextView.setText(nameTextView.getText().toString() + " (" + avg + "/5)");
                }
            }

            @Override
            public void onFailure(Call<RatingResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameTextView = findViewById(R.id.company_name_textView);
        websiteTextView = findViewById(R.id.website_textView4);
        emailTextView = findViewById(R.id.email_textView);
        mobileTextView = findViewById(R.id.mobile_textView);
        addressTextView = findViewById(R.id.address_textView);

        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void assignActions() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                updateCommericalRecord(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    protected void getData() {
        User loggedCompany = UserUtils.getLoggedUser(getApplicationContext());
        nameTextView.setText(loggedCompany.getUsername());
        websiteTextView.setText(loggedCompany.getCompanyWebsite());
        emailTextView.setText(loggedCompany.getEmail());
        mobileTextView.setText(loggedCompany.getMobile());
        addressTextView.setText(loggedCompany.getCompanyAddress());
    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_profile_item) {
            new CompanyInfoDialog(this).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateCommericalRecord(Uri uri) {
        mLoadingDialog.show();
        User user = UserUtils.getLoggedUser(getApplicationContext());
        CompanyController.updateCommericalRecord(getApplicationContext(),
                user.getUsername(), user.getEmail(), user.getCompanyAddress(), user.getCompanyWebsite(), user.getMobile(), uri, new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        mLoadingDialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(CompanyProfileActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CompanyProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        mLoadingDialog.dismiss();
                        Toast.makeText(CompanyProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
