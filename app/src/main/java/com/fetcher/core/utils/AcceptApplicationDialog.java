package com.fetcher.core.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.view.Display;
import android.widget.Button;
import android.widget.Toast;

import com.fetcher.R;
import com.fetcher.core.controllers.CompanyController;
import com.fetcher.core.models.OfferApplication;
import com.fetcher.core.models.response.MessageResponse;
import com.fetcher.views.adapters.OffersCallback;
import com.rengwuxian.materialedittext.MaterialEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcceptApplicationDialog {

    private Dialog mDialog;
    private Activity mActivity;
    private MaterialEditText messageEditText;
    private Button mButton;

    public AcceptApplicationDialog(Activity mActivity, boolean isTraining, OfferApplication offerApplication, OffersCallback offersCallback) {
        this.mActivity = mActivity;
        this.mDialog = new Dialog(mActivity);
        mDialog.setContentView(R.layout.text_input_dialog);
        messageEditText = mDialog.findViewById(R.id.message_editText);
        mButton = mDialog.findViewById(R.id.button4);
        mButton.setOnClickListener(v -> {
            if (!messageEditText.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(mActivity, "PLEASE WAIT", Toast.LENGTH_SHORT).show();
                disableViews();
                if (isTraining) {
                    CompanyController.acceptTrainingApplication(mActivity,
                            offerApplication.getApplicationId(),
                            messageEditText.getText().toString(),
                            new Callback<MessageResponse>() {
                                @Override
                                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                                    enableViews();
                                    if (response.isSuccessful()) {
                                        offersCallback.refreshOffers();
                                        Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                                        mDialog.dismiss();
                                    } else {
                                        Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<MessageResponse> call, Throwable t) {
                                    enableViews();
                                    Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    CompanyController.acceptJobApplication(mActivity,
                            offerApplication.getApplicationId(),
                            messageEditText.getText().toString(),
                            new Callback<MessageResponse>() {
                                @Override
                                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                                    enableViews();
                                    if (response.isSuccessful()) {
                                        offersCallback.refreshOffers();
                                        Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                                        mDialog.dismiss();
                                    } else {
                                        Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<MessageResponse> call, Throwable t) {
                                    enableViews();
                                    Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            } else {
                messageEditText.setError("");
            }
        });

        mDialog.getWindow().getAttributes().width = (int) ((0.95) * getScreenWidth(mActivity));
        mDialog.show();
    }

    private int getScreenWidth(Activity mActivity) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    private void enableViews() {
        messageEditText.setEnabled(true);
        mButton.setEnabled(true);
    }

    private void disableViews() {
        messageEditText.setEnabled(false);
        mButton.setEnabled(false);
    }
}
