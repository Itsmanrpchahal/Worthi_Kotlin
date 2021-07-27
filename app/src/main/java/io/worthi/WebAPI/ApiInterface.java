package io.worthi.WebAPI;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import io.worthi.SignUp.response.SignUpResponse;
import io.worthi.VerifyEmail.response.ResendOTP;
import io.worthi.VerifyEmail.response.VerifyResponse;
import io.worthi.chooseInterest.response.AddInterestsResponse;
import io.worthi.chooseInterest.response.GetInterestsResponse;
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse;
import io.worthi.feedScreen.fragments.feeds.response.UserResponse;
import io.worthi.feedScreen.fragments.interactions.response.GetInteractionResponse;
import io.worthi.feedScreen.fragments.profile.response.LogoutResponse;
import io.worthi.feedScreen.fragments.profile.response.SendFeedbackResponse;
import io.worthi.forgotPassword.response.ResetPasswordResponse;
import io.worthi.loginscreen.response.LoginResponse;
import io.worthi.submitQualifier.response.AnswersResponse;
import io.worthi.yourInfo.response.YourInfoResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("logout")
    Call<LogoutResponse> logout(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("register")
    Call<SignUpResponse> signUp(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone_number") String phone_number,
            @Field("role_id") String role_id
    );

    @FormUrlEncoded
    @POST("verify-otp")
    Call<VerifyResponse> verify(
            @Header("cookie") String cookie,
            @Header("Accept") String Accept,
            @Field("mobile_number") String mobile_number,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("create-user-profile")
    Call<YourInfoResponse> yourInfo(
            @Header("cookie") String cookie,
            @Header("Accept") String Accept,
            @Field("age") String age,
            @Field("gender") String gender,
            @Field("location") String location
    );

    @GET("get-interests")
    Call<ArrayList<GetInterestsResponse>> GetInterests(
            @Header("cookie") String cookie,
            @Header("Accept") String Accept
    );

    @FormUrlEncoded
    @POST("user-create-interest")
    Call<AddInterestsResponse> addInterest(
            @Header("cookie") String cookie,
            @Header("Accept") String Accept,
            @Field("interests") String interests
    );

    @FormUrlEncoded
    @POST("feedback")
    Call<SendFeedbackResponse> feedback(
            @Header("cookie") String cookie,
            @Field("feedback") String feedback
    );

    @GET("user")
    Call<UserResponse> User(
            @Header("cookie") String cookie,
            @Header("Accept") String Accept
    );

    @FormUrlEncoded
    @POST("cashout")
    Call<UserResponse> Cashout(
            @Header("cookie") String cookie,
            @Header("Accept") String Accept,
            @Field("amount") String amount
    );

    @FormUrlEncoded
    @POST("reset-password")
    Call<ArrayList<ResetPasswordResponse>> ForgotPassword(
            @Header("Accept") String Accept,
            @Field("email") String email
    );

    @GET("get-campaigns")
    Call<ArrayList<GetCampainsResponse>> GetCampains(
            @Header("cookie") String cookie,
            @Header("Accept") String Accept
    );

    @GET("get-interacted-campaigns")
    Call<ArrayList<GetInteractionResponse>> GetIntractions(
            @Header("cookie") String cookie,
            @Header("Accept") String Accept
    );



    @POST("user-interaction-campaigns")
    Call<AnswersResponse> SubmitAnswers (
            @Header("cookie") String cookie,
            @Header("Accept") String Accept,
            @Body JsonObject jsonObject
    );

    @FormUrlEncoded
    @POST("resend-otp")
    Call<ResendOTP> ResendOTP (
            @Header("cookie") String cookie,
            @Field("phone_number") String phone_number
    );

}
