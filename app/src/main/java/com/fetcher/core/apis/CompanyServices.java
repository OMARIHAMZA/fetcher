package com.fetcher.core.apis;

import okhttp3.MultipartBody;
import com.fetcher.core.models.response.MessageResponse;
import com.fetcher.core.models.response.OfferApplicationsResponse;
import com.fetcher.core.models.response.OffersResponse;
import com.fetcher.core.models.response.PersonInfoResponse;
import com.fetcher.core.models.response.RatingResponse;
import com.fetcher.core.models.response.StaffResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CompanyServices {

    //CompanyInfo
    @FormUrlEncoded
    @POST("api/companies/update/{company_id}")
    Call<MessageResponse> updateCompanyInfo(
            @Header("Authorization") String token,
            @Path("company_id") int companyId,
            @Field("name") String name,
            @Field("website") String website,
            @Field("official_email") String email,
            @Field("main_address") String address);

    @Multipart
    @POST("api/companies/update/{company_id}")
    Call<MessageResponse> updateCommericalRecord(
            @Header("Authorization") String token,
            @Path("company_id") int companyId,
            @Part("name") String name,
            @Part("website") String website,
            @Part("official_email") String email,
            @Part("main_address") String address,
            @Part MultipartBody.Part commercialRecord);


    //Offers
    @FormUrlEncoded
    @POST("api/trainingOpportunities/add")
    Call<MessageResponse> createTrainingOffer(@Header("Authorization") String token,
                                              @Field("company_id") int companyId,
                                              @Field("category_id") int categoryId,
                                              @Field("duration") String duration,
                                              @Field("start") String startDate,
                                              @Field("requirements") String requirements,
                                              @Field("number_of_trainees") String numOfTrainees,
                                              @Field("place") String place,
                                              @Field("subject") String subject,
                                              @Field("paid") int paid,
                                              @Field("title") String title);

    @FormUrlEncoded
    @POST("api/jobOpportunities/add")
    Call<MessageResponse> createJobOffer(@Header("Authorization") String token,
                                         @Field("company_id") int companyId,
                                         @Field("category_id") int categoryId,
                                         @Field("duration") String duration,
                                         @Field("start") String startDate,
                                         @Field("end_of_submission") String endOfSubmission,
                                         @Field("requirements") String requirements,
                                         @Field("number_of_employees") String numOfEmployees,
                                         @Field("place") String place,
                                         @Field("subject") String subject,
                                         @Field("salary") String salary,
                                         @Field("title") String title,
                                         @Field("days") String days);

    //Offers
    @GET("api/jobOpportunities/getByCompany/{company_id}")
    Call<OffersResponse> getCompanyJobs(@Header("Authorization") String token, @Path("company_id") int companyId);

    @GET("api/trainingOpportunities/getByCompany/{company_id}")
    Call<OffersResponse> getCompanyTrainings(@Header("Authorization") String token, @Path("company_id") int companyId);

    //Applications
    @GET("api/opportunityApplications/getJobApplicationsByOpportunityId/{job_id}")
    Call<OfferApplicationsResponse> getApplicaitonsByJobId(@Header("Authorization") String token, @Path("job_id") int jobId);

    @GET("api/opportunityApplications/getTrainingApplicationsByOpportunityId/{training_id}")
    Call<OfferApplicationsResponse> getApplicaitonsByTrainingId(@Header("Authorization") String token, @Path("training_id") int jobId);

    @GET("api/people/info/{user_id}")
    Call<PersonInfoResponse> getApplicantInfo(@Path("user_id") int userId);

    @FormUrlEncoded
    @POST("api/opportunityApplications/acceptJobApplication")
    Call<MessageResponse> acceptJobApplication(@Header("Authorization") String token, @Field("person_job_application_id") int applicationId, @Field("message") String message);

    @FormUrlEncoded
    @POST("api/opportunityApplications/rejectJobApplication")
    Call<MessageResponse> rejectJobApplication(@Header("Authorization") String token, @Field("person_job_application_id") int applicationId);

    @FormUrlEncoded
    @POST("api/opportunityApplications/acceptTrainingApplication")
    Call<MessageResponse> acceptTrainingApplication(@Header("Authorization") String token, @Field("person_training_application_id") int applicationId, @Field("message") String message);

    @FormUrlEncoded
    @POST("api/opportunityApplications/acceptJobApplication")
    Call<MessageResponse> rejectTrainingApplication(@Header("Authorization") String token, @Field("person_training_application_id") int applicationId);

    @GET("/api/companies/getTrainees/{company_id}")
    Call<StaffResponse> getTrainees(@Header("Authorization") String token, @Path("company_id") int companyId);

    @GET("/api/companies/getEmployees/{company_id}")
    Call<StaffResponse> getEmployees(@Header("Authorization") String token, @Path("company_id") int companyId);

    //Rating
    @FormUrlEncoded
    @POST("api/evaluations/evaluatePerson")
    Call<MessageResponse> ratePerson(@Header("Authorization") String token,
                                     @Field("person_id") int personId,
                                     @Field("company_id") int companyId,
                                     @Field("rating") int rating);

    @GET("/api/evaluations/getCompanyEvaluations/{company_id}")
    Call<RatingResponse> getCompanyRatings(@Path("company_id") int companyId);
}
