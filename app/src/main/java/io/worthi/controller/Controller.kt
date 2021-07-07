package io.worthi.controller

import io.worthi.SignUp.response.SignUpResponse
import io.worthi.WebAPI.WebAPI
import io.worthi.feedScreen.fragments.profile.response.LogoutResponse
import io.worthi.loginscreen.LoginScreen
import io.worthi.loginscreen.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Controller {
    var webAPI: WebAPI? = null
    var loginAPI : LoginAPI?= null
    var logoutAPI : LogoutAPI? = null
    var signUpAPI : SignUpAPI? = null

    fun Controller(login: LoginAPI)
    {
        loginAPI = login
        webAPI = WebAPI()
    }

    fun Controller(logout:LogoutAPI)
    {
        logoutAPI = logout
        webAPI = WebAPI()
    }

    fun Controller(signUp: SignUpAPI)
    {
        signUpAPI = signUp
        webAPI = WebAPI()
    }

    fun Login(email:String,password:String)
    {
        webAPI?.api?.login(email, password)?.enqueue(object :Callback<LoginResponse>
        {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginAPI?.onLoginSuccess(response)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginAPI?.onError(t.message!!)
            }

        })
    }

    fun Logout(token:String)
    {
        webAPI?.api?.logout(token)?.enqueue(object :Callback<LogoutResponse>
        {
            override fun onResponse(
                call: Call<LogoutResponse>,
                response: Response<LogoutResponse>
            ) {
                logoutAPI?.onLogoutSuccess(response)
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
               logoutAPI?.onError(t.message!!)
            }

        })
    }

    fun SignUp(name:String,email:String,password:String,phone_number:String,role_id:String)
    {
        webAPI?.api?.signUp(name, email, password, phone_number, role_id)?.enqueue(object :Callback<SignUpResponse>
        {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                signUpAPI?.onSignUpSuccess(response)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                signUpAPI?.onError(t?.message!!)
            }

        })
    }

    interface LoginAPI {
        fun onLoginSuccess(response: Response<LoginResponse>)
        fun onError(error:String)
    }

    interface LogoutAPI {
        fun onLogoutSuccess(response: Response<LogoutResponse>)
        fun onError(error: String)
    }

    interface SignUpAPI {
        fun onSignUpSuccess(response: Response<SignUpResponse>)
        fun onError(error: String)
    }
}