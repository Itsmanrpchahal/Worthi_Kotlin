package io.worthi.WebAPI;

import io.worthi.SignUp.response.SignUpResponse;
import io.worthi.feedScreen.fragments.profile.response.LogoutResponse;
import io.worthi.loginscreen.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

}
