package com.fetcher.core.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.view.Display;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import com.fetcher.R;
import com.fetcher.core.controllers.UserController;
import com.fetcher.core.models.User;
import com.fetcher.core.models.response.MessageResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoDialog {

    private Dialog mDialog;
    private Activity mActivity;
    private MaterialEditText workEditText, addressEditText;
    private Button saveChangesButton;

    public UserInfoDialog(Activity mActivity) {
        this.mActivity = mActivity;
        this.mDialog = new Dialog(mActivity);
        this.mDialog.setContentView(R.layout.edit_user_info_dialog);
        findViewsById();
        assignActions();

        User loggedUser = UserUtils.getLoggedUser(mActivity);
        workEditText.setText(loggedUser.getWorkField());

        this.mDialog.getWindow().getAttributes().width = (int) (getScreenWidth(mActivity) * 0.95);
    }

    private void assignActions() {
        saveChangesButton.setOnClickListener(v -> {
            if (checkFields(workEditText, addressEditText)) {
                disableAllViews();
                UserController.updateUserInfo(mActivity, workEditText.getText().toString(),
                        addressEditText.getText().toString(),
                        new Callback<MessageResponse>() {
                            @Override
                            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                                enableAllViews();
                                User user = UserUtils.getLoggedUser(mActivity);
                                user.setWorkField(workEditText.getText().toString());
                                UserUtils.loginUser(mActivity, user);
                                Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                                dismiss();
                            }

                            @Override
                            public void onFailure(Call<MessageResponse> call, Throwable t) {
                                enableAllViews();
                                Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });
    }

    private void disableAllViews() {
        workEditText.setEnabled(false);
        addressEditText.setEnabled(false);
        saveChangesButton.setEnabled(false);
    }

    private void enableAllViews() {
        workEditText.setEnabled(true);
        addressEditText.setEnabled(true);
        saveChangesButton.setEnabled(true);
    }

    public void dismiss() {
        mDialog.dismiss();
        mActivity.recreate();
    }

    public void show() {
        mDialog.show();
    }

    private void findViewsById() {
        workEditText = mDialog.findViewById(R.id.work_field_editText);
        addressEditText = mDialog.findViewById(R.id.address_editText);
        saveChangesButton = mDialog.findViewById(R.id.save_button);
    }

    private boolean checkFields(EditText... editTexts) {
        boolean allFieldsFilled = true;
        for (EditText editText : editTexts) {
            if (editText.getText().toString().equals("")) {
                allFieldsFilled = false;
                editText.setError(mActivity.getString(R.string.required_field));
            }
        }
        return allFieldsFilled;
    }


    private int getScreenWidth(Activity mActivity) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
}
