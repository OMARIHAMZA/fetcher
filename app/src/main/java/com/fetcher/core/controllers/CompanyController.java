package com.fetcher.core.controllers;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import com.fetcher.core.apis.CompanyServices;
import com.fetcher.core.models.response.MessageResponse;
import com.fetcher.core.models.response.OfferApplicationsResponse;
import com.fetcher.core.models.response.OffersResponse;
import com.fetcher.core.models.response.PersonInfoResponse;
import com.fetcher.core.models.response.RatingResponse;
import com.fetcher.core.models.response.StaffResponse;
import com.fetcher.core.utils.RetrofitClientInstance;
import com.fetcher.core.utils.UserUtils;

import retrofit2.Call;
import retrofit2.Callback;

public class CompanyController {

    public static void updateCompanyInfo(@NonNull Context context,
                                         String name,
                                         String email,
                                         String address,
                                         String website,
                                         String mobile,
                                         Callback<MessageResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<MessageResponse> call = companyServices.updateCompanyInfo(UserUtils.getUserToken(context),
                UserUtils.getLoggedUser(context).getId(),
                name,
                website,
                email,
                address);
        call.enqueue(callback);
    }

    public static void updateCommericalRecord(@NonNull Context context,
                                              String name,
                                              String email,
                                              String address,
                                              String website,
                                              String mobile, Uri commericalRecord, Callback<MessageResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<MessageResponse> call = companyServices.updateCommericalRecord(UserUtils.getUserToken(context),
                UserUtils.getLoggedUser(context).getId(),
                name,
                website,
                email,
                address,
                prepareFilePart(context, "commercial_record", commericalRecord));
        call.enqueue(callback);
    }


    public static void createTrainingOffer(
            @NonNull Context context,
            int companyId,
            int categoryId,
            String duration,
            String startDate,
            String requirements,
            String numOfTrainees,
            String place,
            String subject,
            int paid,
            String title,
            Callback<MessageResponse> callback
    ) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<MessageResponse> call = companyServices.createTrainingOffer(
                UserUtils.getUserToken(context),
                companyId,
                categoryId,
                duration,
                startDate,
                requirements,
                numOfTrainees,
                place,
                subject,
                paid,
                title
        );
        call.enqueue(callback);
    }

    public static void createJobOffer(
            @NonNull Context context,
            int companyId,
            int categoryId,
            String duration,
            String startDate,
            String endOfSubmission,
            String requirements,
            String numOfEmployees,
            String place,
            String subject,
            String salary,
            String title,
            String days,
            Callback<MessageResponse> callback
    ) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<MessageResponse> call = companyServices.createJobOffer(
                UserUtils.getUserToken(context),
                companyId,
                categoryId,
                duration,
                startDate,
                endOfSubmission,
                requirements,
                numOfEmployees,
                place,
                subject,
                salary,
                title,
                days
        );
        call.enqueue(callback);
    }


    public static void getCompanyJobs(@NonNull Context context, Callback<OffersResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<OffersResponse> call = companyServices.getCompanyJobs(UserUtils.getUserToken(context), UserUtils.getLoggedUser(context).getId());
        call.enqueue(callback);
    }

    public static void getCompanyTrainings(@NonNull Context context, Callback<OffersResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<OffersResponse> call = companyServices.getCompanyTrainings(UserUtils.getUserToken(context), UserUtils.getLoggedUser(context).getId());
        call.enqueue(callback);
    }

    public static void getJobApplications(@NonNull Context context, int jobId, Callback<OfferApplicationsResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<OfferApplicationsResponse> call = companyServices.getApplicaitonsByJobId(
                UserUtils.getUserToken(context), jobId);
        call.enqueue(callback);
    }

    public static void getTrainingApplications(@NonNull Context context, int trainingId, Callback<OfferApplicationsResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<OfferApplicationsResponse> call = companyServices.getApplicaitonsByTrainingId(
                UserUtils.getUserToken(context), trainingId);
        call.enqueue(callback);
    }

    public static void getUserInfo(int userId, Callback<PersonInfoResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<PersonInfoResponse> call = companyServices.getApplicantInfo(userId);
        call.enqueue(callback);
    }

    public static void acceptJobApplication(@NonNull Context context, int applicationId, String message, Callback<MessageResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<MessageResponse> call = companyServices.acceptJobApplication(UserUtils.getUserToken(context), applicationId,
                message);
        call.enqueue(callback);
    }

    public static void rejectJobApplication(@NonNull Context context, int applicationId, Callback<MessageResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<MessageResponse> call = companyServices.rejectJobApplication(UserUtils.getUserToken(context), applicationId);
        call.enqueue(callback);
    }

    public static void acceptTrainingApplication(@NonNull Context context, int trainingId, String message, Callback<MessageResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<MessageResponse> call = companyServices.acceptTrainingApplication(UserUtils.getUserToken(context), trainingId,
                message);
        call.enqueue(callback);
    }

    public static void rejectTrainingApplication(@NonNull Context context, int trainingId, Callback<MessageResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<MessageResponse> call = companyServices.rejectTrainingApplication(UserUtils.getUserToken(context), trainingId);
        call.enqueue(callback);
    }

    public static void getEmployees(@NonNull Context context, Callback<StaffResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<StaffResponse> call = companyServices.getEmployees(UserUtils.getUserToken(context),
                UserUtils.getLoggedUser(context).getId());
        call.enqueue(callback);
    }

    public static void getTrainees(@NonNull Context context, Callback<StaffResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<StaffResponse> call = companyServices.getTrainees(UserUtils.getUserToken(context),
                UserUtils.getLoggedUser(context).getId());
        call.enqueue(callback);
    }

    public static void ratePerson(@NonNull Context context, int personId, int companyId, int rating, Callback<MessageResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<MessageResponse> call = companyServices.ratePerson(UserUtils.getUserToken(context), personId, companyId, rating);
        call.enqueue(callback);
    }

    public static void getCompanyRating(@NonNull Context context, Callback<RatingResponse> callback) {
        CompanyServices companyServices = RetrofitClientInstance.getRetrofitInstance().create(CompanyServices.class);
        Call<RatingResponse> call = companyServices.getCompanyRatings(UserUtils.getLoggedUser(context).getId());
        call.enqueue(callback);
    }


    @NonNull
    private static MultipartBody.Part prepareFilePart(Context context, String partName, Uri fileUri) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = new File(fileUri.getPath());

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

}
