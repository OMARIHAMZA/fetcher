package omari.hamza.fetcher.core.controllers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import java.io.File;
import java.nio.file.Files;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.ByteString;
import omari.hamza.fetcher.core.apis.UserServices;
import omari.hamza.fetcher.core.models.response.MessagesResponse;
import omari.hamza.fetcher.core.models.response.UserInfoResponse;
import omari.hamza.fetcher.core.utils.RetrofitClientInstance;
import omari.hamza.fetcher.core.utils.UserUtils;
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

    public static void getCompanyInfo(@NonNull Context context, Callback<UserInfoResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<UserInfoResponse> call = userServices.getCompanyData(UserUtils.getUserToken(context));
        call.enqueue(callback);
    }

    public static void registerUser(String username, String email, String mobile, String password, int accountType, Callback<MessagesResponse> callback) {
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<MessagesResponse> call = userServices.registerUser(username,
                password,
                email,
                mobile,
                accountType + "");
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
