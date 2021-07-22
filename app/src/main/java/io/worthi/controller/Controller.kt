package io.worthi.controller

import com.google.gson.JsonObject
import io.worthi.SignUp.response.SignUpResponse
import io.worthi.VerifyEmail.response.VerifyResponse
import io.worthi.WebAPI.WebAPI
import io.worthi.chooseInterest.response.AddInterestsResponse
import io.worthi.chooseInterest.response.GetInterestsResponse
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse
import io.worthi.feedScreen.fragments.feeds.response.UserResponse
import io.worthi.feedScreen.fragments.interactions.response.GetInteractionResponse
import io.worthi.feedScreen.fragments.profile.response.LogoutResponse
import io.worthi.feedScreen.fragments.profile.response.SendFeedbackResponse
import io.worthi.forgotPassword.response.ResetPasswordResponse
import io.worthi.loginscreen.response.LoginResponse
import io.worthi.submitQualifier.response.AnswersResponse
import io.worthi.yourInfo.response.YourInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Controller {
    var webAPI: WebAPI? = null
    var loginAPI : LoginAPI?= null
    var logoutAPI : LogoutAPI? = null
    var signUpAPI : SignUpAPI? = null
    var verifyAPI : VerifyEmailAPI? = null
    var yourInfoAPI : YourInfoAPI? = null
    var getInterestAPI : GetInterestAPI? = null
    var addInterestAPI : AddInterestAPI?=null
    var sendFeedbackAPI : SendFeedbackAPI?=null
    var userAPI : UserAPI? = null
    var cashOutAPI:CashOutAPI? = null
    var forgotPassAPI:ForgotPassAPI? = null
    var getCampainsAPI:GetCampainsAPI?=null
    var getInteractionAPI:GetInteractionAPI? = null
    var sendAnswersAPI:SendAnswersAPI? = null


    fun Controller(user: UserAPI)
    {
        userAPI = user
        webAPI = WebAPI()
    }

    fun Controller(login: LoginAPI,user: UserAPI)
    {
        loginAPI = login
        userAPI  =user
        webAPI = WebAPI()
    }

    fun Controller(logout:LogoutAPI,sendFeedback: SendFeedbackAPI,user: UserAPI)
    {
        logoutAPI = logout
        sendFeedbackAPI = sendFeedback
        userAPI = user
        webAPI = WebAPI()
    }

    fun Controller(signUp: SignUpAPI)
    {
        signUpAPI = signUp
        webAPI = WebAPI()
    }

    fun Controller(verifyEmail: VerifyEmailAPI)
    {
        verifyAPI = verifyEmail
        webAPI = WebAPI()
    }

    fun Controller(yourInfo: YourInfoAPI)
    {
        yourInfoAPI = yourInfo
        webAPI = WebAPI()
    }

    fun Controller(getInterest: GetInterestAPI,addInterest: AddInterestAPI)
    {
        getInterestAPI = getInterest
        addInterestAPI = addInterest
        webAPI = WebAPI()
    }

    fun Controller(user: UserAPI,cashOut: CashOutAPI)
    {
        userAPI = user
        cashOutAPI = cashOut
        webAPI = WebAPI()
    }

    fun Controller(forgotPass: ForgotPassAPI)
    {
        forgotPassAPI = forgotPass
        webAPI = WebAPI()
    }

    fun Controller(getCampains: GetCampainsAPI)
    {
        getCampainsAPI = getCampains


        webAPI = WebAPI()
    }

    fun Controller(getCampains: GetCampainsAPI,user: UserAPI)
    {
        getCampainsAPI = getCampains
        userAPI = user

        webAPI = WebAPI()
    }

    fun Controller(getCampains: GetCampainsAPI,sendAnswers: SendAnswersAPI)
    {
        getCampainsAPI = getCampains
        sendAnswersAPI = sendAnswers
        webAPI = WebAPI()
    }

    fun Controller(getInteraction: GetInteractionAPI)
    {
        getInteractionAPI = getInteraction
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

    fun Verify(cookie:String,Accept:String,mobile_number:String,otp:String)
    {
        webAPI?.api?.verify(cookie,Accept,mobile_number,otp)?.enqueue(object :Callback<VerifyResponse>
        {
            override fun onResponse(
                call: Call<VerifyResponse>,
                response: Response<VerifyResponse>
            ) {
                verifyAPI?.onVerifySuccess(response)
            }

            override fun onFailure(call: Call<VerifyResponse>, t: Throwable) {
                verifyAPI?.onError(t?.message!!)
            }

        })
    }

    fun YourInfo(token:String,accept:String,age:String,gender:String,location:String)
    {
        webAPI?.api?.yourInfo(token,accept,age, gender, location)?.enqueue(object :Callback<YourInfoResponse>
        {
            override fun onResponse(
                call: Call<YourInfoResponse>,
                response: Response<YourInfoResponse>
            ) {
                yourInfoAPI?.onYourInfoAPI(response)
            }

            override fun onFailure(call: Call<YourInfoResponse>, t: Throwable) {
               yourInfoAPI?.onError(t.message!!)
            }

        })
    }

    fun GetInterest(token:String,accept:String)
    {
        webAPI?.api?.GetInterests(token,accept)?.enqueue(object :Callback<ArrayList<GetInterestsResponse>>
        {
            override fun onResponse(
                call: Call<ArrayList<GetInterestsResponse>>,
                response: Response<ArrayList<GetInterestsResponse>>
            ) {
                getInterestAPI?.onGetInterestAPI(response)
            }

            override fun onFailure(call: Call<ArrayList<GetInterestsResponse>>, t: Throwable) {
                getInterestAPI?.onError(t?.message!!)
            }

        })
    }

    fun AddInterest(token: String,accept: String,interest:String)
    {
       webAPI?.api?.addInterest(token,accept,interest)?.enqueue(object :Callback<AddInterestsResponse>
       {
           override fun onResponse(
               call: Call<AddInterestsResponse>,
               response: Response<AddInterestsResponse>
           ) {
               addInterestAPI?.onAddInterestAPI(response)
           }

           override fun onFailure(call: Call<AddInterestsResponse>, t: Throwable) {
              addInterestAPI?.onError(t.message!!)
           }

       })
    }

    fun SendFeedback(token: String,feedback:String)
    {
        webAPI?.api?.feedback(token,feedback)?.enqueue(object :Callback<SendFeedbackResponse>
        {
            override fun onResponse(
                call: Call<SendFeedbackResponse>,
                response: Response<SendFeedbackResponse>
            ) {
                sendFeedbackAPI?.onSendfeedbackAPI(response)
            }

            override fun onFailure(call: Call<SendFeedbackResponse>, t: Throwable) {
                sendFeedbackAPI?.onError(t.message!!)
            }

        })
    }

    fun User(token: String,accept: String)
    {
        webAPI?.api?.User(token,accept)?.enqueue(object :Callback<UserResponse>
        {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                userAPI?.onUserSuccessAPI(response)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                userAPI?.onError(t.message!!)
            }

        })
    }

    fun CashOut(token: String,accept: String,cash:String)
    {
        webAPI?.api?.Cashout(token,accept,cash)?.enqueue(object :Callback<UserResponse>
        {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                cashOutAPI?.onCashoutSuccessAPI(response)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                cashOutAPI?.onError(t.message!!)
            }

        })
    }

    fun Resetpassword(accept:String,email: String)
    {
        webAPI?.api?.ForgotPassword(accept,email)?.enqueue(object :Callback<ArrayList<ResetPasswordResponse>>
        {
            override fun onResponse(
                call: Call<ArrayList<ResetPasswordResponse>>,
                response: Response<java.util.ArrayList<ResetPasswordResponse>>
            ) {
                forgotPassAPI?.onForgotSuccessAPI(response)
            }

            override fun onFailure(call: Call<ArrayList<ResetPasswordResponse>>, t: Throwable) {
                forgotPassAPI?.onError(t.message!!)
            }

        })
    }

    fun GetCampains(token: String,accept: String)
    {
        webAPI?.api?.GetCampains(token,accept)?.enqueue(object :Callback<ArrayList<GetCampainsResponse>>
        {
            override fun onResponse(
                call: Call<ArrayList<GetCampainsResponse>>,
                response: Response<ArrayList<GetCampainsResponse>>
            ) {
                getCampainsAPI?.onGetCampainsSuccessAPI(response)
            }

            override fun onFailure(call: Call<ArrayList<GetCampainsResponse>>, t: Throwable) {
                getCampainsAPI?.onError(t.message!!)
            }

        })
    }

    fun GetInteractions(token: String,accept: String)
    {
        webAPI?.api?.GetIntractions(token,accept)?.enqueue(object :Callback<ArrayList<GetInteractionResponse>>
        {
            override fun onResponse(
                call: Call<ArrayList<GetInteractionResponse>>,
                response: Response<ArrayList<GetInteractionResponse>>
            ) {
                getInteractionAPI?.onGetInteractionAPI(response)
            }

            override fun onFailure(call: Call<ArrayList<GetInteractionResponse>>, t: Throwable) {
                getInteractionAPI?.onError(t.message!!)
            }

        })
    }

    fun SendAnswers(token: String,accept: String,campaign_id:JsonObject)
    {
        webAPI?.api?.SubmitAnswers(token,accept, campaign_id)?.enqueue(object :Callback<AnswersResponse>
        {
            override fun onResponse(
                call: Call<AnswersResponse>,
                response: Response<AnswersResponse>
            ) {
                sendAnswersAPI?.onSendAnswersSuccess(response)
            }

            override fun onFailure(call: Call<AnswersResponse>, t: Throwable) {
                sendAnswersAPI?.onError(t.message!!)
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

    interface VerifyEmailAPI {
        fun onVerifySuccess(success:Response<VerifyResponse>)
        fun onError(error: String)
    }

    interface YourInfoAPI {
        fun onYourInfoAPI(success: Response<YourInfoResponse>)
        fun onError(error: String)
    }

    interface GetInterestAPI {
        fun onGetInterestAPI(success:Response<ArrayList<GetInterestsResponse>>)
        fun onError(error: String)
    }
    interface AddInterestAPI {
        fun onAddInterestAPI(successs:Response<AddInterestsResponse>)
        fun onError(error: String)
    }

    interface SendFeedbackAPI {
        fun onSendfeedbackAPI (successs: Response<SendFeedbackResponse>)
        fun onError(error: String)
    }

    interface UserAPI {
        fun onUserSuccessAPI (success:Response<UserResponse>)
        fun onError(error: String)
    }

    interface CashOutAPI {
        fun onCashoutSuccessAPI(successs: Response<UserResponse>)
        fun onError(error: String)
    }

    interface ForgotPassAPI {
        fun onForgotSuccessAPI(success:Response<ArrayList<ResetPasswordResponse>>)
        fun onError(error:String)
    }

    interface GetCampainsAPI {
        fun onGetCampainsSuccessAPI(success:Response<ArrayList<GetCampainsResponse>>)
        fun onError(error: String)
    }

    interface GetInteractionAPI {
        fun onGetInteractionAPI(success:Response<ArrayList<GetInteractionResponse>>)
        fun onError(error: String)
    }

    interface SendAnswersAPI {
        fun onSendAnswersSuccess(success:Response<AnswersResponse>)
        fun onError(error: String)
    }

}