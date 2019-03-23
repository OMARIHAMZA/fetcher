package omari.hamza.fetcher.core.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import omari.hamza.fetcher.core.apis.UserServices;
import omari.hamza.fetcher.core.models.response.CateogriesResponse;
import omari.hamza.fetcher.core.models.response.OffersResponse;
import omari.hamza.fetcher.core.utils.RetrofitClientInstance;
import omari.hamza.fetcher.core.utils.UserUtils;
import retrofit2.Call;
import retrofit2.Callback;

public class AppController {

    public static void getAllCategories(Callback<CateogriesResponse> callback){
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<CateogriesResponse> call = userServices.getAllCategories();
        call.enqueue(callback);
    }

    public static void getTrainingOffers(@NonNull Context context,  int catId, Callback<OffersResponse> callback){
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<OffersResponse> call = userServices.getTrainingOffers(catId, UserUtils.getUserToken(context));
        call.enqueue(callback);
    }

    public static void getJobOffers(@NonNull Context context,  int catId, Callback<OffersResponse> callback){
        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<OffersResponse> call = userServices.getJobOffers(catId, UserUtils.getUserToken(context));
        call.enqueue(callback);
    }

}
