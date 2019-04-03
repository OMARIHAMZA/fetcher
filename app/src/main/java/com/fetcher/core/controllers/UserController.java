package com.fetcher.core.controllers;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import com.fetcher.core.apis.UserServices;
import com.fetcher.core.models.response.CompaniesResponse;
import com.fetcher.core.models.response.MessageResponse;
import com.fetcher.core.models.response.MessagesResponse;
import com.fetcher.core.models.response.OffersResponse;
import com.fetcher.core.models.response.RatingResponse;
import com.fetcher.core.models.response.UserInfoResponse;
import com.fetcher.core.utils.RetrofitClientInstance;
import com.fetcher.core.utils.UserUtils;

import retrofit2.Call;
import retrofit2.Callback;

public class UserController {

    public static void loginUser(String email, String password, Callback<MessagesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<MessagesResponse> call = userServices.loginUser(email, password);
        call.enqueue(callback);
    }

    public static void getUserInfo(@NonNull Context context, Callback<MessagesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<MessagesResponse> call = userServices.getUserInfo(UserUtils.getUserToken(context));
        call.enqueue(callback);
    }

    public static void updateToken(@NonNull Context context, Callback<MessagesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<MessagesResponse> call = userServices.refreshToken(UserUtils.getUserToken(context));
        call.enqueue(callback);
    }

    public static void getCompanyInfo(@NonNull Context context, Callback<UserInfoResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<UserInfoResponse> call = userServices.getCompanyData(UserUtils.getUserToken(context));
        call.enqueue(callback);
    }

    public static void registerUser(@NonNull Context context, String username, String email, String mobile, String password, int accountType, Uri uri, Callback<MessagesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", username)
                .addFormDataPart("password", password)
                .addFormDataPart("email", email)
                .addFormDataPart("mobile", mobile)
                .addFormDataPart("type", accountType + "")
                .addPart(accountType == 2 ? prepareFilePart(context, "commercial_record", uri) : prepareFilePart(context, "id_photo", uri))
                .build();
        Call<MessagesResponse> call = userServices.registerUser(requestBody);
        call.enqueue(callback);
    }

    public static void getInbox(@NonNull Context context, Callback<MessagesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<MessagesResponse> call = userServices.getInbox(UserUtils.getUserToken(context), UserUtils.getLoggedUser(context).getId());
        call.enqueue(callback);
    }

    public static void applyForJob(@NonNull Context context, int jobId, Callback<MessagesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<MessagesResponse> call = userServices.applyForJob(UserUtils.getUserToken(context), jobId, UserUtils.getLoggedUser(context).getId());
        call.enqueue(callback);
    }

    public static void applyForTraining(@NonNull Context context, int trainingId, Callback<MessagesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<MessagesResponse> call = userServices.applyForTraining(UserUtils.getUserToken(context), trainingId, UserUtils.getLoggedUser(context).getId());
        call.enqueue(callback);
    }

    public static void getUserData(@NonNull Context context, Callback<UserInfoResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<UserInfoResponse> call = userServices.getUserData(UserUtils.getUserToken(context));
        call.enqueue(callback);
    }

    public static void updateInfo(@NonNull Context context, Uri idImage, Uri cvImage, Callback<UserInfoResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<UserInfoResponse> call = userServices.updateUserProfile(
                UserUtils.getLoggedUser(context).getId(),
                UserUtils.getUserToken(context),
                "null",
                prepareFilePart(context, "photo", idImage == null ? cvImage : idImage),
                idImage == null ? MultipartBody.Part.create(RequestBody.create(MediaType.parse("image/*"), "")) : prepareFilePart(context, "id_photo", idImage),
                cvImage == null ? MultipartBody.Part.create(RequestBody.create(MediaType.parse("image/*"), "")) : prepareFilePart(context, "cv", cvImage));
        call.enqueue(callback);
    }


    public static void getUserRating(@NonNull Context context, Callback<RatingResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<RatingResponse> call = userServices.getPersonRating(UserUtils.getLoggedUser(context).getId());
        call.enqueue(callback);

    }

    public static void updateUserInfo(@NonNull Context context, String work, String address, Callback<MessageResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<MessageResponse> call = userServices.updateUserInfo(UserUtils.getUserToken(context),
                UserUtils.getLoggedUser(context).getId(),
                address,
                work);
        call.enqueue(callback);
    }

    public static void getEmploymentCompanies(@NonNull Context context, Callback<CompaniesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<CompaniesResponse> call = userServices.getCompaniesByEmployment(
                UserUtils.getUserToken(context),
                UserUtils.getLoggedUser(context).getId()
        );
        call.enqueue(callback);
    }

    public static void getTrainingCompanies(@NonNull Context context, Callback<CompaniesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<CompaniesResponse> call = userServices.getCompaniesByTraining(
                UserUtils.getUserToken(context),
                UserUtils.getLoggedUser(context).getId()
        );
        call.enqueue(callback);
    }

    public static void rateCompany(@NonNull Context context, int rating, int companyId, Callback<MessageResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<MessageResponse> call = userServices.rateCompany(UserUtils.getUserToken(context),
                UserUtils.getLoggedUser(context).getId(), companyId,
                rating);
        call.enqueue(callback);
    }

    public static void searchJobs(@NonNull Context context, String query, Callback<OffersResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<OffersResponse> call = userServices.searchJobs(UserUtils.getUserToken(context), query);
        call.enqueue(callback);
    }

    public static void searchTrainings(@NonNull Context context, String query, Callback<OffersResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<OffersResponse> call = userServices.searchTrainings(UserUtils.getUserToken(context), query);
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
