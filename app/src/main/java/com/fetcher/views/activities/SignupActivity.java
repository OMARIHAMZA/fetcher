package com.fetcher.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fetcher.views.activities.users.UserProfileActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import com.fetcher.R;
import com.fetcher.core.controllers.UserController;
import com.fetcher.core.utils.LoadingDialog;
import com.fetcher.views.masters.MasterActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import retrofit2.Call;
import retrofit2.Response;

public class SignupActivity extends MasterActivity {

    private Toolbar mToolbar;
    private MaterialEditText usernameEditText, emailEditText, mobileEditText, passwordEditText;
    private Button signupButton;
    private LoadingDialog mLoadingDialog;
    private Switch aSwitch;
    private boolean isChecked = false;
    private TextView selectPhotoTextView;
    private Uri photoUri;
    private static final int IMAGE_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_signup);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        usernameEditText = findViewById(R.id.username_editText);
        emailEditText = findViewById(R.id.email_editText);
        mobileEditText = findViewById(R.id.mobile_editText);
        passwordEditText = findViewById(R.id.password_ediText);
        signupButton = findViewById(R.id.signup_button);
        mLoadingDialog = new LoadingDialog(this);
        aSwitch = findViewById(R.id.account_type_switch);
        selectPhotoTextView = findViewById(R.id.select_photo_textView);
    }

    @Override
    protected void assignActions() {
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            photoUri = null;
            SignupActivity.this.isChecked = isChecked;
            selectPhotoTextView.setText(isChecked ? Html.fromHtml("<u>SELECT COMMERCIAL RECORD</u>") : Html.fromHtml("<u>SELECT ID PHOTO</u>"));
        });
        signupButton.setOnClickListener(v -> {
            if (checkFields(usernameEditText, emailEditText, mobileEditText, passwordEditText)) {
                if (photoUri == null) {
                    Toast.makeText(this, selectPhotoTextView.getText().toString(), Toast.LENGTH_SHORT).show();
                    return;
                }
                mLoadingDialog.show();
                UserController.registerUser(getApplicationContext(),
                        usernameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        mobileEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        isChecked ? 2 : 3,
                        photoUri,
                        SignupActivity.this);
            }
        });

        selectPhotoTextView.setOnClickListener(v -> {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(SignupActivity.this);
        });
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                photoUri = result.getUri();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {
        if (response.isSuccessful()) {
            Toast.makeText(this, "SUCCESS, ACTIVATION PENDING", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            mLoadingDialog.dismiss();
            Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {
        mLoadingDialog.dismiss();
    }
}
