package omari.hamza.fetcher.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import omari.hamza.fetcher.R;
import omari.hamza.fetcher.core.controllers.CompanyController;
import omari.hamza.fetcher.core.controllers.UserController;
import omari.hamza.fetcher.core.models.Offer;
import omari.hamza.fetcher.core.models.OfferApplication;
import omari.hamza.fetcher.core.models.User;
import omari.hamza.fetcher.core.models.response.MessageResponse;
import omari.hamza.fetcher.core.models.response.PersonInfoResponse;
import omari.hamza.fetcher.core.utils.LoadingDialog;
import omari.hamza.fetcher.views.activities.companies.OfferApplicationsActivity;
import omari.hamza.fetcher.views.activities.users.UserProfileActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferApplicationsAdapter extends RecyclerView.Adapter<OfferApplicationsAdapter.MyViewHolder> {

    private LoadingDialog mLoadingDialog;
    private Activity mActivity;
    private ArrayList<OfferApplication> offerApplications;
    private boolean isTraining;
    private OffersCallback offersCallback;

    public OfferApplicationsAdapter(Activity mActivity, ArrayList<OfferApplication> offerApplications, boolean isTraining, OffersCallback offersCallback) {
        this.mActivity = mActivity;
        this.offerApplications = offerApplications;
        this.isTraining = isTraining;
        this.offersCallback = offersCallback;
        mLoadingDialog = new LoadingDialog(mActivity);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_application_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        OfferApplication currentApplication = offerApplications.get(i);
        myViewHolder.itemView.setOnClickListener(v -> viewUserProfile(currentApplication));
        myViewHolder.nameTextView.setText(currentApplication.getApplicantName());
        myViewHolder.emailTextView.setText(currentApplication.getApplicantEmail());
        myViewHolder.acceptCardView.setOnClickListener(v -> acceptApplication(currentApplication));
        myViewHolder.rejectCardView.setOnClickListener(v -> rejectApplication(currentApplication));
    }

    @Override
    public int getItemCount() {
        return offerApplications.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView nameTextView, emailTextView;
        CardView acceptCardView, rejectCardView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.nameTextView = itemView.findViewById(R.id.name_textView);
            this.emailTextView = itemView.findViewById(R.id.email_textView);
            this.acceptCardView = itemView.findViewById(R.id.accept_cardView);
            this.rejectCardView = itemView.findViewById(R.id.reject_cardView);
        }
    }


    private void viewUserProfile(OfferApplication application) {
        User user = new User();
        user.setUsername(application.getApplicantName());
        user.setMobile(application.getApplicantMobile());
        user.setEmail(application.getApplicantEmail());
        mLoadingDialog.show();
        CompanyController.getUserInfo(application.getPersonId(), new Callback<PersonInfoResponse>() {
            @Override
            public void onResponse(Call<PersonInfoResponse> call, Response<PersonInfoResponse> response) {
                mLoadingDialog.dismiss();
                if (response.isSuccessful()) {
                    user.setIdPhoto(response.body().getUser().getIdPhoto());
                    user.setCv(response.body().getUser().getCv());
                    Intent mIntent = new Intent(mActivity, UserProfileActivity.class);
                    mIntent.putExtra("user", user);
                    mActivity.startActivity(mIntent);
                } else {
                    Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonInfoResponse> call, Throwable t) {
                mLoadingDialog.dismiss();
                Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void acceptApplication(OfferApplication application) {
        mLoadingDialog.show();
        if (isTraining) {
            CompanyController.acceptTrainingApplication(mActivity, application.getApplicationId(), new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    mLoadingDialog.dismiss();
                    if (response.isSuccessful()) {
                        offersCallback.refreshOffers();
                        Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    mLoadingDialog.dismiss();
                    Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            CompanyController.acceptJobApplication(mActivity, application.getApplicationId(), new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    mLoadingDialog.dismiss();
                    if (response.isSuccessful()) {
                        offersCallback.refreshOffers();
                        Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    mLoadingDialog.dismiss();
                    Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void rejectApplication(OfferApplication application) {
        mLoadingDialog.show();
        if (isTraining) {
            CompanyController.rejectTrainingApplication(mActivity, application.getApplicationId(), new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    mLoadingDialog.dismiss();
                    if (response.isSuccessful()) {
                        offersCallback.refreshOffers();
                        Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    mLoadingDialog.dismiss();
                    Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            CompanyController.rejectJobApplication(mActivity, application.getApplicationId(), new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    mLoadingDialog.dismiss();
                    if (response.isSuccessful()) {
                        offersCallback.refreshOffers();
                        Toast.makeText(mActivity, "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    mLoadingDialog.dismiss();
                    Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
