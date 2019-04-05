package com.fetcher.core.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fetcher.R;
import com.fetcher.core.controllers.CompanyController;
import com.fetcher.core.models.RefreshActivityCallback;
import com.fetcher.core.models.response.MessageResponse;
import com.rengwuxian.materialedittext.MaterialEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProjectDialog {

    private Dialog mDialog;
    private Activity mActivity;
    private MaterialEditText titleEditText, descritpionEditText;
    private Button addProjectButton;


    public AddProjectDialog(Activity mActivity, RefreshActivityCallback activityCallback) {
        this.mActivity = mActivity;
        this.mDialog = new Dialog(mActivity);
        mDialog.setContentView(R.layout.add_project_layout);
        titleEditText = mDialog.findViewById(R.id.project_title_editText);
        descritpionEditText = mDialog.findViewById(R.id.project_description_editText);
        addProjectButton = mDialog.findViewById(R.id.add_project_button);
        addProjectButton.setOnClickListener(v -> {
            if (titleEditText.getText().toString().equals("") || descritpionEditText.getText().toString().equals("")) {
                Toast.makeText(mActivity, "Enter valid data", Toast.LENGTH_SHORT).show();
                return;
            }
            disableAllView();
            Toast.makeText(mActivity, "Please Wait", Toast.LENGTH_SHORT).show();
            CompanyController.addProject(mActivity, titleEditText.getText().toString(), descritpionEditText.getText().toString(), new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    enableAllViews();
                    if (response.isSuccessful()) {
                        activityCallback.refresh();
                        mDialog.dismiss();
                    } else {
                        Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    enableAllViews();
                    Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

        mDialog.getWindow().getAttributes().width = ((int) (getScreenWidth(mActivity) * 0.95));
        mDialog.show();
    }


    private void disableAllView() {
        this.titleEditText.setVisibility(View.GONE);
        this.descritpionEditText.setVisibility(View.GONE);
        this.addProjectButton.setVisibility(View.GONE);
    }

    private void enableAllViews() {
        this.titleEditText.setVisibility(View.VISIBLE);
        this.descritpionEditText.setVisibility(View.VISIBLE);
        this.addProjectButton.setVisibility(View.VISIBLE);
    }

    private int getScreenWidth(Activity mActivity) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
}
