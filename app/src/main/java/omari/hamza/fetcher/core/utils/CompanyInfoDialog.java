package omari.hamza.fetcher.core.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.CompanyController;
import omari.hamza.fetcher.core.models.User;
import omari.hamza.fetcher.core.models.response.MessageResponse;
import omari.hamza.fetcher.views.activities.users.UserProfileActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyInfoDialog {

    private Dialog mDialog;
    private Activity mActivity;
    private MaterialEditText nameEditText, websiteEditText, emailEditText, mobileEditText, addressEditText;
    private Button saveChangesButton;
    private TextView updateCommercialRecordTextView;


    public CompanyInfoDialog(Activity mActivity) {
        this.mActivity = mActivity;
        mDialog = new Dialog(mActivity);
        mDialog.setContentView(R.layout.company_info_dialog);
        findViewsById(mDialog);
        displayData();
        updateCommercialRecordTextView.setOnClickListener(v -> {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(mActivity);
            mDialog.dismiss();
        });
        saveChangesButton.setOnClickListener(v -> updateInfo());
        mDialog.getWindow().getAttributes().width = (int) (getScreenWidth(mActivity) * 0.9);
    }

    private void updateInfo() {
        if (checkFields(nameEditText, websiteEditText, emailEditText, mobileEditText, addressEditText)) {
            Toast.makeText(mActivity, "PLEASE WAIT", Toast.LENGTH_SHORT).show();
            disableAllViews();
            CompanyController.updateCompanyInfo(mActivity,
                    nameEditText.getText().toString(),
                    emailEditText.getText().toString(),
                    addressEditText.getText().toString(),
                    websiteEditText.getText().toString(),
                    mobileEditText.getText().toString(),
                    new Callback<MessageResponse>() {
                        @Override
                        public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                            enableAllViews();
                            if (response.isSuccessful()) {
                                User loggedCompany = UserUtils.getLoggedUser(mActivity);
                                loggedCompany.setUsername(nameEditText.getText().toString());
                                loggedCompany.setEmail(emailEditText.getText().toString());
                                loggedCompany.setCompanyWebsite(websiteEditText.getText().toString());
                                loggedCompany.setCompanyAddress(addressEditText.getText().toString());
                                UserUtils.loginUser(mActivity, loggedCompany);
                                Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                                dismiss();
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
        }
    }

    private void disableAllViews() {
        nameEditText.setEnabled(false);
        websiteEditText.setEnabled(false);
        emailEditText.setEnabled(false);
        mobileEditText.setEnabled(false);
        addressEditText.setEnabled(false);
        updateCommercialRecordTextView.setEnabled(false);
        saveChangesButton.setEnabled(false);
    }

    private void enableAllViews() {
        nameEditText.setEnabled(true);
        websiteEditText.setEnabled(true);
        emailEditText.setEnabled(true);
        mobileEditText.setEnabled(true);
        addressEditText.setEnabled(true);
        updateCommercialRecordTextView.setEnabled(true);
        saveChangesButton.setEnabled(true);
    }

    private void displayData() {
        User loggedCompany = UserUtils.getLoggedUser(mActivity);
        nameEditText.setText(loggedCompany.getUsername());
        websiteEditText.setText(loggedCompany.getCompanyWebsite());
        emailEditText.setText(loggedCompany.getCompanyEmail());
        mobileEditText.setText(loggedCompany.getMobile());
        addressEditText.setText(loggedCompany.getCompanyAddress());
    }

    private void findViewsById(Dialog mDialog) {
        nameEditText = mDialog.findViewById(R.id.company_name_editText);
        websiteEditText = mDialog.findViewById(R.id.website_editText);
        emailEditText = mDialog.findViewById(R.id.email_editText);
        mobileEditText = mDialog.findViewById(R.id.mobile_editText);
        addressEditText = mDialog.findViewById(R.id.address_editText);
        saveChangesButton = mDialog.findViewById(R.id.button3);
        updateCommercialRecordTextView = mDialog.findViewById(R.id.update_medical_record_textView);
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
        mActivity.recreate();
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
