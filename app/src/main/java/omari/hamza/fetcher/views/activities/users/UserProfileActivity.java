package omari.hamza.fetcher.views.activities.users;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.nio.file.Path;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.UserController;
import omari.hamza.fetcher.core.models.User;
import omari.hamza.fetcher.core.models.response.UserInfoResponse;
import omari.hamza.fetcher.core.utils.ImageDialog;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.core.utils.UserUtils;
import omari.hamza.fetcher.views.activities.LoginActivity;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends MasterActivity {

    private Toolbar mToolbar;
    private TextView usernameTextView, fieldTextView, emailTextView, phoneTextView;
    private TextView uploadCVTextView, uploadIDTextView;
    private TextView logoutTextView;
    private LoadingDialog mLoadingDialog;
    @Nullable
    private User user;

    private int currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLoadingDialog = new LoadingDialog(this);
        usernameTextView = findViewById(R.id.username_textView);
        fieldTextView = findViewById(R.id.field_textView);
        emailTextView = findViewById(R.id.email_textView);
        phoneTextView = findViewById(R.id.phone_textView);
        uploadCVTextView = findViewById(R.id.cv_textView);
        uploadIDTextView = findViewById(R.id.id_image_textView);
        logoutTextView = findViewById(R.id.logout_textView);

        user = (User) getIntent().getSerializableExtra("user");
    }

    @Override
    protected void assignActions() {
        if (user == null) {
            uploadCVTextView.setOnClickListener(v -> {
                currentImage = 1;
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(UserProfileActivity.this);
            });

            uploadIDTextView.setOnClickListener(v -> {
                currentImage = 2;
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(UserProfileActivity.this);
            });
        } else {
            fieldTextView.setCompoundDrawablesRelative(null, null, null, null);
            fieldTextView.setPadding(0, 0, 0, 0);

            uploadCVTextView.setText("CLICK HERE TO VIEW CV");
            uploadCVTextView.setOnClickListener(v -> new ImageDialog(this, user.getCv()).show());

            uploadIDTextView.setText("CLICK HERE TO VIEW ID");
            uploadIDTextView.setOnClickListener(v -> new ImageDialog(this, user.getIdPhoto()).show());

            logoutTextView.setVisibility(View.GONE);
        }

        logoutTextView.setOnClickListener(v -> {
            UserUtils.logoutUser(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

    }

    @Override
    protected void getData() {
        if (user != null) {
            usernameTextView.setText(user.getUsername());
            emailTextView.setText(user.getEmail());
            phoneTextView.setText(user.getMobile());
        } else {
            User user = UserUtils.getLoggedUser(getApplicationContext());
            usernameTextView.setText(user.getUsername());
            emailTextView.setText(user.getEmail());
            phoneTextView.setText(user.getMobile());
        }
    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                if (currentImage == 1) {
                    uploadCVImage(resultUri);
                } else if (currentImage == 2) {
                    uploadIdImage(resultUri);
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void uploadCVImage(Uri uri) {
        mLoadingDialog.show();
        UserController.updateInfo(getApplicationContext(), null, uri, new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(UserProfileActivity.this, getString(R.string.SUCCESS), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(UserProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void uploadIdImage(Uri uri) {
        mLoadingDialog.show();
        UserController.updateInfo(getApplicationContext(), uri, null, new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(UserProfileActivity.this, getString(R.string.SUCCESS), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(UserProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
