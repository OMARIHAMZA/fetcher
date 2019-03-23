package omari.hamza.fetcher.core.apis;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omari.hamza.fetcher.core.models.response.CateogriesResponse;
import omari.hamza.fetcher.core.models.response.MessagesResponse;
import omari.hamza.fetcher.core.models.response.OffersResponse;
import omari.hamza.fetcher.core.models.response.UserInfoResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface UserServices {

    @FormUrlEncoded
    @POST("api/auth/login")
    Call<MessagesResponse> loginUser(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/auth/register")
    Call<MessagesResponse> registerUser(@Field("name") String name,
                                        @Field("password") String password,
                                        @Field("email") String email,
                                        @Field("mobile") String mobile,
                                        @Field("type") String type);

    @POST("api/auth/me")
    Call<MessagesResponse> getUserInfo(@Header("Authorization") String token);


    //Person
    @GET("api/people/currentPerson")
    Call<UserInfoResponse> getUserData(@Header("Authorization") String token);


    //Company
    @GET("api/companies/currentCompany")
    Call<UserInfoResponse> getCompanyData(@Header("Authorization") String token);


    //Inbox
    @GET("api/people/getMessages/{user_id}")
    Call<MessagesResponse> getInbox(@Header("Authorization") String token, @Path("user_id") int userId);

    //Categories
    @GET("api/categories/get")
    Call<CateogriesResponse> getAllCategories();

    //Offers
    @GET("api/trainingOpportunities/getByCategory/{category_id}")
    Call<OffersResponse> getTrainingOffers(@Path("category_id") int catId, @Header("Authorization") String token);

    @GET("api/jobOpportunities/getByCategory/{category_id}")
    Call<OffersResponse> getJobOffers(@Path("category_id") int catId, @Header("Authorization") String token);


    @FormUrlEncoded
    @POST("api/opportunityApplications/applyForJob")
    Call<MessagesResponse> applyForJob(@Header("Authorization") String token, @Field("job_opportunity_id") int jobId, @Field("person_id") int personId);

    @FormUrlEncoded
    @POST("api/opportunityApplications/applyForTraining")
    Call<MessagesResponse> applyForTraining(@Header("Authorization") String token, @Field("training_opportunity_id") int trainingId, @Field("person_id") int personId);

    //Update Profile
    @Multipart
    @POST("api/people/edit/{user_id}")
    Call<UserInfoResponse> updateUserProfile(@Path("user_id") int userId, @Header("Authorization") String token, @Part("address") String address, @Part MultipartBody.Part photo, @Part MultipartBody.Part idPhoto, @Part MultipartBody.Part cv);

}
