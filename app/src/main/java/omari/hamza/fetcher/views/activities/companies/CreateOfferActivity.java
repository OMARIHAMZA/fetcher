package omari.hamza.fetcher.views.activities.companies;

import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONObject;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.AppController;
import omari.hamza.fetcher.core.controllers.CompanyController;
import omari.hamza.fetcher.core.controllers.UserController;
import omari.hamza.fetcher.core.models.Category;
import omari.hamza.fetcher.core.models.response.CateogriesResponse;
import omari.hamza.fetcher.core.models.response.MessageResponse;
import omari.hamza.fetcher.core.models.response.UserInfoResponse;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.core.utils.UserUtils;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateOfferActivity extends MasterActivity {

    private Toolbar mToolbar;
    private MaterialEditText subjectEditText, titleEditText, daysEditText, requirementsEditText, salaryEditText,
            locationEditText, seatsEditText, durationEditText, startDateEditText, endOfSubmissionEditText;
    private Switch trainingSwitch, paidSwitch;
    private Button createOfferButton;
    private LoadingDialog mLoadingDialog;
    private MaterialSpinner categorySpinner;
    private ArrayList<Category> categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_create_offer);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLoadingDialog = new LoadingDialog(this);

        subjectEditText = findViewById(R.id.subject_editText);
        titleEditText = findViewById(R.id.title_editText);
        salaryEditText = findViewById(R.id.salary_editText);
        daysEditText = findViewById(R.id.days_editText);
        requirementsEditText = findViewById(R.id.requirements_editText);
        trainingSwitch = findViewById(R.id.training_switch);
        paidSwitch = findViewById(R.id.paid_switch);
        locationEditText = findViewById(R.id.place_editText);
        seatsEditText = findViewById(R.id.seats_editText);
        createOfferButton = findViewById(R.id.button2);
        categorySpinner = findViewById(R.id.categories_spinner);
        durationEditText = findViewById(R.id.duration_editText);
        startDateEditText = findViewById(R.id.start_date_editText);
        endOfSubmissionEditText = findViewById(R.id.end_of_submission_editText);

    }

    @Override
    protected void assignActions() {
        trainingSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            paidSwitch.setEnabled(isChecked);
            salaryEditText.setEnabled(!isChecked);
            endOfSubmissionEditText.setEnabled(!isChecked);
        });

        createOfferButton.setOnClickListener(v -> {

            if (checkFields(daysEditText, startDateEditText, durationEditText, subjectEditText, titleEditText, locationEditText, seatsEditText, requirementsEditText)) {

                if (categorySpinner.getSelectedIndex() == -1) {
                    categorySpinner.setError("Required");
                    return;
                }

                if (!trainingSwitch.isChecked() && (salaryEditText.getText().toString().equalsIgnoreCase("") || daysEditText.getText().toString().equalsIgnoreCase("") || endOfSubmissionEditText.getText().toString().equalsIgnoreCase(""))) {
                    Toast.makeText(this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (trainingSwitch.isChecked()) {
                    createTrainingOffer();
                } else {
                    createJobOffer();
                }

            }

        });
    }

    private void createJobOffer() {
        mLoadingDialog.show();
        CompanyController.createJobOffer(getApplicationContext(),
                UserUtils.getLoggedUser(getApplicationContext()).getId(),
                categories.get(categorySpinner.getSelectedIndex()).getId(),
                durationEditText.getText().toString(),
                startDateEditText.getText().toString(),
                endOfSubmissionEditText.getText().toString(),
                requirementsEditText.getText().toString(),
                seatsEditText.getText().toString(),
                locationEditText.getText().toString(),
                subjectEditText.getText().toString(),
                salaryEditText.getText().toString(),
                titleEditText.getText().toString(),
                daysEditText.getText().toString(),
                new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        mLoadingDialog.dismiss();
                        if (response.body().isSuccess()) {
                            Toast.makeText(CreateOfferActivity.this, getString(R.string.SUCCESS), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CreateOfferActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        mLoadingDialog.dismiss();
                        Toast.makeText(CreateOfferActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void createTrainingOffer() {
        mLoadingDialog.show();
        CompanyController.createTrainingOffer(getApplicationContext(),
                UserUtils.getLoggedUser(getApplicationContext()).getId(),
                categories.get(categorySpinner.getSelectedIndex()).getId(),
                durationEditText.getText().toString(),
                startDateEditText.getText().toString(),
                requirementsEditText.getText().toString(),
                seatsEditText.getText().toString(),
                locationEditText.getText().toString(),
                subjectEditText.getText().toString(),
                paidSwitch.isChecked() ? 1 : 0,
                titleEditText.getText().toString(),
                new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        mLoadingDialog.dismiss();
                        if (response.body().isSuccess()) {
                            Toast.makeText(CreateOfferActivity.this, getString(R.string.SUCCESS), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CreateOfferActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        mLoadingDialog.dismiss();
                        Toast.makeText(CreateOfferActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    protected void getData() {
        mLoadingDialog.show();
        AppController.getAllCategories(new Callback<CateogriesResponse>() {
            @Override
            public void onResponse(Call<CateogriesResponse> call, Response<CateogriesResponse> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()) {
                    categories = response.body().getCategories();
                    categorySpinner.setItems(categoriesToStrings(categories));
                } else {
                    Toast.makeText(CreateOfferActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<CateogriesResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(CreateOfferActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }

    private ArrayList<String> categoriesToStrings(ArrayList<Category> categories) {
        ArrayList<String> result = new ArrayList<>();
        for (Category category : categories) {
            result.add(category.getName());
        }
        return result;
    }
}
