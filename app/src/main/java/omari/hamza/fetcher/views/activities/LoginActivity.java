package omari.hamza.fetcher.views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.UserController;
import omari.hamza.fetcher.core.models.User;
import omari.hamza.fetcher.core.models.response.MessagesResponse;
import omari.hamza.fetcher.core.models.response.UserInfoResponse;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.core.utils.UserUtils;
import omari.hamza.fetcher.views.activities.companies.CompanyHomeActivity;
import omari.hamza.fetcher.views.activities.users.UserHomeActivity;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class LoginActivity extends MasterActivity {

    private TextView signupTextView;
    private MaterialEditText emailEditText, passwordEditText;
    private Button loginButton;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        if (UserUtils.isUserLoggedIn(getApplicationContext())) {
            mLoadingDialog.show();
            UserController.updateToken(getApplicationContext(), new Callback<MessagesResponse>() {
                @Override
                public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                    mLoadingDialog.dismiss();
                    if (response.isSuccessful()) {
                        UserUtils.saveUserToken(getApplicationContext(), response.body().getToken());
                    }
                    launchHome();
                }

                @Override
                public void onFailure(Call<MessagesResponse> call, Throwable t) {
                    mLoadingDialog.dismiss();
                    launchHome();
                }
            });
        }
    }

    private void launchHome() {
        if (UserUtils.getLoggedUser(getApplicationContext()).getType().equalsIgnoreCase("2")) {
            startActivity(new Intent(getApplicationContext(), CompanyHomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
            finish();
        }
    }

    @Override
    protected void assignUIReferences() {
        emailEditText = findViewById(R.id.email_editText);
        passwordEditText = findViewById(R.id.password_ediText);
        loginButton = findViewById(R.id.signup_button);
        signupTextView = findViewById(R.id.signup_textView);
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void assignActions() {
        loginButton.setOnClickListener(v -> {

            if (checkFields(emailEditText, passwordEditText)) {
                mLoadingDialog.show();
                UserController.loginUser(emailEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        this);
            }


        });
        signupTextView.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignupActivity.class)));
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
                    if (response.isSuccessful()) {
                        UserUtils.loginUser(getApplicationContext(), response.body().getUser());
                        if (UserUtils.getLoggedUser(getApplicationContext()).getType().equalsIgnoreCase("2")) {
                            getCompanyInfo();
                        } else {
                            getUserInfo();
                        }
                    } else {
                        mLoadingDialog.dismiss();
                        Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessagesResponse> call, Throwable t) {
                    mLoadingDialog.dismiss();
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void getUserInfo() {
        UserController.getUserData(getApplicationContext(), new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()) {
                    User loggedUser = UserUtils.getLoggedUser(getApplicationContext());
                    loggedUser.setId(response.body().getUser().getId());
                    loggedUser.setWorkField(response.body().getUser().getWorkField());
                    UserUtils.loginUser(getApplicationContext(), loggedUser);
                    startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
                    finish();
                } else {
                    UserUtils.logoutUser(getApplicationContext());
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCompanyInfo() {
        UserController.getCompanyInfo(getApplicationContext(), new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()) {
                    User loggedUser = UserUtils.getLoggedUser(getApplicationContext());
                    loggedUser.setId(response.body().getUser().getId());
                    loggedUser.setCompanyAddress(response.body().getUser().getCompanyAddress());
                    loggedUser.setCompanyWebsite(response.body().getUser().getCompanyWebsite());
                    UserUtils.loginUser(getApplicationContext(), loggedUser);
                    startActivity(new Intent(getApplicationContext(), CompanyHomeActivity.class));
                    finish();
                } else {
                    UserUtils.logoutUser(getApplicationContext());
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
