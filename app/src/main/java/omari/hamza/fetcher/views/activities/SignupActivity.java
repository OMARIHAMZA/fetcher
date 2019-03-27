package omari.hamza.fetcher.views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.UserController;
import omari.hamza.fetcher.core.models.response.MessagesResponse;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.core.utils.UserUtils;
import omari.hamza.fetcher.views.activities.users.UserHomeActivity;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends MasterActivity {

    private Toolbar mToolbar;
    private MaterialEditText usernameEditText, emailEditText, mobileEditText, passwordEditText;
    private Button signupButton;
    private LoadingDialog mLoadingDialog;
    private Switch aSwitch;
    private boolean isChecked = false;

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
    }

    @Override
    protected void assignActions() {
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> SignupActivity.this.isChecked = isChecked);
        signupButton.setOnClickListener(v -> {
            if (checkFields(usernameEditText, emailEditText, mobileEditText, passwordEditText)) {
                mLoadingDialog.show();
                UserController.registerUser(usernameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        mobileEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        isChecked ? 2 : 3,
                        SignupActivity.this);
            }
        });
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {
        if (response.isSuccessful()) {
            UserUtils.saveUserToken(getApplicationContext(), ((MessagesResponse) response.body()).getToken());
            UserController.getUserInfo(getApplicationContext(), new Callback<MessagesResponse>() {
                @Override
                public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                    mLoadingDialog.dismiss();
                    if (response.isSuccessful()) {
                        UserUtils.loginUser(getApplicationContext(), response.body().getUser());
                        startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessagesResponse> call, Throwable t) {
                    mLoadingDialog.dismiss();
                    Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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
