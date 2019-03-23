package omari.hamza.fetcher.views.activities;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.UserController;
import omari.hamza.fetcher.core.models.response.MessagesResponse;
import omari.hamza.fetcher.core.models.Offer;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.views.masters.MasterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferDetailsActivity extends MasterActivity {

    private Toolbar mToolbar;
    private TextView jobTitleTextView, companyNameTextView, locationTextView, typeTextView, descriptionTextView;
    private Button applyButton;
    private Offer offer;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_offer_details);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignUIReferences() {
        mToolbar = findViewById(R.id.mToolbar);


        offer = (Offer) getIntent().getSerializableExtra("offer");
        mToolbar.setTitle(offer.getTitle());
        setSupportActionBar(mToolbar);
        //noinspection all
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        jobTitleTextView = findViewById(R.id.offer_title_textView);
        companyNameTextView = findViewById(R.id.company_textView);
        locationTextView = findViewById(R.id.location_textView);
        typeTextView = findViewById(R.id.offer_type_textView);
        descriptionTextView = findViewById(R.id.offer_description_textView);
        applyButton = findViewById(R.id.button);

        descriptionTextView.setMovementMethod(new ScrollingMovementMethod());

        mLoadingDialog = new LoadingDialog(this);

    }

    @Override
    protected void assignActions() {

        applyButton.setOnClickListener(v -> {

            mLoadingDialog.show();

            if (offer.isTraining()){
                UserController.applyForTraining(getApplicationContext(), offer.getTrainingId(), new Callback<MessagesResponse>() {
                    @Override
                    public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                        mLoadingDialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(OfferDetailsActivity.this, getString(R.string.SUCCESS), Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(OfferDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessagesResponse> call, Throwable t) {
                        mLoadingDialog.dismiss();
                        Toast.makeText(OfferDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                UserController.applyForJob(getApplicationContext(), offer.getJobId(), new Callback<MessagesResponse>() {
                    @Override
                    public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                        mLoadingDialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(OfferDetailsActivity.this, getString(R.string.SUCCESS), Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(OfferDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessagesResponse> call, Throwable t) {
                        mLoadingDialog.dismiss();
                        Toast.makeText(OfferDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    protected void getData() {
        jobTitleTextView.setText(offer.getTitle());
        companyNameTextView.setText(offer.getCompanyName());
        locationTextView.setText(offer.getLocation());
        typeTextView.setText(offer.isTraining() ? getString(R.string.training) : getString(R.string.job_offer));
        //noinspection all
        descriptionTextView.setText("Requirements:\n" + offer.getRequirements()
                + "\n\nSalary: " + offer.getSalary()
                + "\n\nDays: " + offer.getDays()
                + "\n\nCompany's  Website: " + offer.getCompanyWebsite()
                + "\n\nCompany's Email: " + offer.getCompanyEmail()
                + "\n\nCompany's Address: " + offer.getCompanyLocation());
    }

    @Override
    protected void onSuccess(@NonNull Call call, @NonNull Response response) {

    }

    @Override
    protected void onFailed(@NonNull Call call, @NonNull Throwable t) {

    }
}
