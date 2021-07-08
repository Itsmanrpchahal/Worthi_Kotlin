package io.worthi.SignUp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.SignUp.response.SignUpResponse
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.VerifyEmail.VerifyEmailScreen
import io.worthi.controller.Controller
import retrofit2.Response
import java.text.DecimalFormat
import java.text.NumberFormat


class SignUpScreen : BaseClass(), Controller.SignUpAPI {
    private lateinit var signup: LinearLayout
    private lateinit var spinner: Spinner
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var retypepassword: EditText
    private lateinit var phonenumber: EditText
    private lateinit var utility: Utility
    private lateinit var countrycode: String
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var signuptv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)

        init()
        listeners()
    }

    private fun listeners() {
        signup.setOnClickListener {

            when {
                name.text.isEmpty() -> {
                    name.requestFocus()
                    name.setError("Enter name")
                }

                email.text.isEmpty() -> {
                    email.requestFocus()
                    email.setError("Enter email")
                }

                !utility.isValidEmail(email.text.toString()) -> {
                    email.requestFocus()
                    email.setError("Enter valid email")
                }

                password.text.isEmpty() -> {
                    password.requestFocus()
                    password.setError("Enter password")
                }

                password.text.length <= 5 -> {
                    password.requestFocus()
                    password.setError("Enter strong password")
                }

                retypepassword.text.isEmpty() -> {
                    retypepassword.requestFocus()
                    retypepassword.setError("Enter confirm password")
                }

                !password.text.toString().equals(retypepassword.text.toString()) -> {
                    retypepassword.requestFocus()
                    retypepassword.setError("Password not matched")
                }

                phonenumber.text.isEmpty() -> {
                    phonenumber.requestFocus()
                    phonenumber.setError("Enter phone number")
                }

                phonenumber.text.length <= 6 -> {
                    phonenumber.requestFocus()
                    phonenumber.setError("Enter valid number")
                }
                else -> {
                    if (utility.isConnectingToInternet(this)) {
                        hideKeyboard()
                        pd.show()
                        pd.setContentView(R.layout.loading)

                        controller.SignUp(
                            name.text.toString(),
                            email.text.toString(),
                            retypepassword.text.toString(),
                            countrycode + phonenumber.text.toString(),
                            "3"
                        )
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
        val languages = resources.getStringArray(R.array.countryCodes)
        val adapter = ArrayAdapter(
            this,
            R.layout.spinnertv, languages
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                // your code here
                Log.d("soinner", "" + languages[position])
                val name: String = languages[position]
                val parts: List<String> = name.split(",")
                val Fname = arrayOf(name.split(",").toString())
                countrycode = "+" + parts.get(0)
                Log.d("countrycode", "" + countrycode)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })
    }

    fun init() {
        signup = findViewById(R.id.signup)
        spinner = findViewById(R.id.spinner)
        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        retypepassword = findViewById(R.id.retypepassword)
        phonenumber = findViewById(R.id.phonenumber)
        signuptv = findViewById(R.id.signuptv)
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)


    }

    private fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

    override fun onSignUpSuccess(response: Response<SignUpResponse>) {
        pd.dismiss()
        if (response.isSuccessful)
        {
            if (response.code() == 201) {
                startActivity(
                    Intent(
                        this,
                        VerifyEmailScreen::class.java
                    ).putExtra("phonenumber",countrycode+phonenumber.text.toString())
                )
                setStringVal(Constants.S_TOKEN,response.body()?.token)

            } else {
                utility.relative_snackbar(
                    window.currentFocus,
                    "User already exist",
                    getString(R.string.close_up)
                )
            }
        }else {
            utility.relative_snackbar(
                window.currentFocus,
                response.message(),
                getString(R.string.close_up)
            )
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