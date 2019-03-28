package omari.hamza.fetcher.core.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.Display;
import android.widget.Button;
import android.widget.Toast;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.CompanyController;
import omari.hamza.fetcher.core.controllers.UserController;
import omari.hamza.fetcher.core.models.Company;
import omari.hamza.fetcher.core.models.Person;
import omari.hamza.fetcher.core.models.response.MessageResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingDialog {

    private Dialog mDialog;
    private Activity mActivity;


    public RatingDialog(Activity mActivity, @Nullable Person person, @Nullable Company company) {
        this.mActivity = mActivity;
        this.mDialog = new Dialog(mActivity);
        mDialog.setContentView(R.layout.rating_dialog);
        AppCompatRatingBar mRatingBar = mDialog.findViewById(R.id.appCompatRatingBar);
        Button mButton = mDialog.findViewById(R.id.rate_button);
        if (person != null) {
            mButton.setOnClickListener(v -> {
                Toast.makeText(mActivity, "PLEASE WAIT", Toast.LENGTH_SHORT).show();
                mButton.setEnabled(false);
                mRatingBar.setEnabled(false);
                CompanyController.ratePerson(mActivity, person.getPersonId(), UserUtils.getLoggedUser(mActivity).getId(),
                        (int) mRatingBar.getRating(),
                        new Callback<MessageResponse>() {
                            @Override
                            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                                if (response.isSuccessful()) {
                                    dismiss();
                                    Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                                    mButton.setEnabled(true);
                                    mRatingBar.setEnabled(true);
                                }
                            }

                            @Override
                            public void onFailure(Call<MessageResponse> call, Throwable t) {
                                Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                                mButton.setEnabled(true);
                                mRatingBar.setEnabled(true);
                            }
                        });
            });
        } else if (company != null) {
            mButton.setOnClickListener(v -> {
                Toast.makeText(mActivity, "PLEASE WAIT", Toast.LENGTH_SHORT).show();
                mButton.setEnabled(false);
                mRatingBar.setEnabled(false);
                UserController.rateCompany(mActivity, (int) mRatingBar.getRating(), company.getId(),
                        new Callback<MessageResponse>() {
                            @Override
                            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                                if (response.isSuccessful()) {
                                    dismiss();
                                    Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                                    mButton.setEnabled(true);
                                    mRatingBar.setEnabled(true);
                                }
                            }

                            @Override
                            public void onFailure(Call<MessageResponse> call, Throwable t) {
                                Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                                mButton.setEnabled(true);
                                mRatingBar.setEnabled(true);
                            }
                        });
            });
        }

        mDialog.getWindow().getAttributes().width = (int) (getScreenWidth(mActivity) * 0.9);
    }


    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    private int getScreenWidth(Activity mActivity) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
}
