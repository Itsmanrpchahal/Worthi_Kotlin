package io.worthi.loginscreen

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.SignUp.SignUpScreen
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.controller.Controller
import io.worthi.feedScreen.FeedScreen
import io.worthi.loginscreen.response.LoginResponse
import retrofit2.Response

class LoginScreen : BaseClass() ,Controller.LoginAPI{

    private lateinit var signIn: LinearLayout
    private lateinit var signUptext: TextView
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signInbt: LinearLayout
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        init()
        listeners()
    }

    private fun listeners() {

        signIn.setOnClickListener {
            when {
                email.text.isEmpty() -> {
                    email.requestFocus()
                    email.setError("Enter Email")
                }

                !utility.isValidEmail(email.text.toString()) -> {
                    email.requestFocus()
                    email.setError("Enter valid Email")
                }

                password.text.isEmpty() -> {
                    password.requestFocus()
                    password.setError("Enter Password")
                }

                password.text.length <= 5 -> {
                    password.requestFocus()
                    password.setError("Enter strong password")
                }

                else -> {
                    if (utility.isConnectingToInternet(this)) {
                        hideKeyboard()
                        pd.show()
                        pd.setContentView(R.layout.loading)

                        controller.Login(email.text.toString(),password.text.toString())
                    } else {
                        utility.relative_snackbar(
                            window.currentFocus,
                            getString(R.string.nointernet),
                            getString(R.string.close_up)
                        )
                    }
                }
            }
        }

        signUptext.setOnClickListener {
            startActivity(Intent(this, SignUpScreen::class.java))
        }
    }

    fun init() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)
        signIn = findViewById(R.id.signIn)
        signUptext = findViewById(R.id.signUptext)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signInbt = findViewById(R.id.signInbt)
    }

    private fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

    override fun onLoginSuccess(response: Response<LoginResponse>) {
        pd.dismiss()
       if (response.isSuccessful)
       {
           if (response.code()==201)
           {
               setStringVal(Constants.TOKEN,response.body()?.token)
               startActivity(Intent(this,FeedScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
               finish()
           } else if(response.code()==401) {
               utility.relative_snackbar(
                   window.currentFocus,
                   "Invalid email/password",
                   getString(R.string.close_up)
               )
           }

       }
    }

    override fun onError(error: String) {
      pd.dismiss()
        utility.relative_snackbar(
            window.currentFocus,
            error,
            getString(R.string.close_up)
        )
    }
}