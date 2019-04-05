package com.fetcher.views.activities.companies;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fetcher.core.models.Branch;
import com.fetcher.core.models.CompanyInfo;
import com.fetcher.core.models.RefreshActivityCallback;
import com.fetcher.core.utils.AddBranchDialog;
import com.fetcher.core.utils.AddProjectDialog;
import com.fetcher.views.adapters.CompanyPhotosAdapter;
import com.fetcher.views.adapters.ProjectsAdapter;
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
import com.theartofdev.edmodo.cropper.CropImageActivity;
import com.theartofdev.edmodo.cropper.CropImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyProfileActivity extends MasterActivity implements RefreshActivityCallback {

    private Toolbar mToolbar;
    private TextView nameTextView, websiteTextView, emailTextView, mobileTextView, addressTextView;
    private LoadingDialog mLoadingDialog;
    private CardView projectCardView;
    private RecyclerView projectsRecyclerView;
    private TextView addProjectTextView;
    private CardView branchesCardView;
    private TextView branchesTextView, addBranchTextView;
    private CardView photosCardView;
    private RecyclerView photosRecyclerView;
    private TextView addPhotoTextView;
    private boolean uploadingCompanyPhoto = false;

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
                if (response.isSuccessful()) {
                    if (response.body().getRatings() != null && !response.body().getRatings().isEmpty()) {
                        int avg = 0;
                        for (Rating rating : response.body().getRatings()) {
                            avg += rating.getRating();
                        }
                        avg /= response.body().getRatings().size();
                        nameTextView.setText(nameTextView.getText().toString() + " (" + avg + "/5)");
                    }
                }
                getCompanyInfo();
            }

            @Override
            public void onFailure(Call<RatingResponse> call, Throwable t) {
                getCompanyInfo();
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

        projectCardView = findViewById(R.id.cardView5);
        projectsRecyclerView = findViewById(R.id.projects_recyclerView);
        addProjectTextView = findViewById(R.id.add_new_project_textView);

        branchesCardView = findViewById(R.id.branches_cardView);
        branchesTextView = findViewById(R.id.branches_textView);
        addBranchTextView = findViewById(R.id.add_new_branch_textView);

        photosCardView = findViewById(R.id.photos_cardView);
        photosRecyclerView = findViewById(R.id.photos_recyclerView);
        addPhotoTextView = findViewById(R.id.add_photo_textView);

        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void assignActions() {
        addProjectTextView.setOnClickListener(v -> new AddProjectDialog(CompanyProfileActivity.this, CompanyProfileActivity.this));
        addBranchTextView.setOnClickListener(v -> new AddBranchDialog(CompanyProfileActivity.this, CompanyProfileActivity.this));
        addPhotoTextView.setOnClickListener(v -> {
            uploadingCompanyPhoto = true;
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(CompanyProfileActivity.this);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                if (uploadingCompanyPhoto) {
                    uploadingCompanyPhoto = false;
                    uploadCompanyPhoto(resultUri);
                } else {
                    updateCommericalRecord(resultUri);
                }
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

    private void getCompanyInfo() {
        mLoadingDialog.show();
        CompanyController.getCompanyInfo(getApplicationContext(), new Callback<CompanyInfo>() {
            @Override
            public void onResponse(@NonNull Call<CompanyInfo> call, @NonNull Response<CompanyInfo> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()) {
                    projectsRecyclerView.setAdapter(new ProjectsAdapter(CompanyProfileActivity.this, response.body().getProjects()));
                    projectsRecyclerView.setLayoutManager(new LinearLayoutManager(CompanyProfileActivity.this));
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(projectsRecyclerView.getContext(),
                            DividerItemDecoration.VERTICAL);
                    projectsRecyclerView.addItemDecoration(dividerItemDecoration);


                    if (response.body().getBranches().size() > 0) {
                        for (Branch branch : response.body().getBranches()) {
                            if (branchesTextView.getText().toString().isEmpty()) {
                                branchesTextView.setText(branch.getAddress());
                            } else {
                                branchesTextView.setText(branchesTextView.getText().toString() + ", " + branch.getAddress());
                            }
                        }
                    }

                    if (response.body().getCompanyPhotos().size() > 0) {
                        photosRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        photosRecyclerView.setAdapter(new CompanyPhotosAdapter(response.body().getCompanyPhotos(), CompanyProfileActivity.this));
                    }
                }
            }

            @Override
            public void onFailure(Call<CompanyInfo> call, Throwable t) {
                mLoadingDialog.dismiss();
            }
        });
    }

    private void uploadCompanyPhoto(Uri resultUri) {
        mLoadingDialog.show();
        CompanyController.addPhoto(getApplicationContext(), resultUri, new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(CompanyProfileActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                    recreate();
                } else {
                    Toast.makeText(CompanyProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(CompanyProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void refresh() {
        recreate();
    }
}
