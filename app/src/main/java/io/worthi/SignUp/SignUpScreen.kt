package io.worthi.SignUp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import io.worthi.R
import io.worthi.Utilities.Utility
import io.worthi.VerifyEmail.VerifyEmailScreen


class SignUpScreen : AppCompatActivity() {
    private lateinit var signup: LinearLayout
    private lateinit var spinner: Spinner
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var retypepassword: EditText
    private lateinit var phonenumber: EditText
    private lateinit var utility: Utility

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)

        init()
        listeners()
    }

    private fun listeners() {
        signup.setOnClickListener {

            when  {
                name.text.isEmpty()-> {
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

                password.text.length<=5 -> {
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

                phonenumber.text.length <=6 -> {
                    phonenumber.requestFocus()
                    phonenumber.setError("Enter valid number")
                }
                 else -> {
                     startActivity(Intent(this,VerifyEmailScreen::class.java))
                 }

        }

        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.countryCodes,
            R.layout.spinnertv
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


    }

    fun init()
    {
        signup = findViewById(R.id.signup)
        spinner = findViewById(R.id.spinner)
        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        retypepassword = findViewById(R.id.retypepassword)
        phonenumber = findViewById(R.id.phonenumber)
        utility = Utility()
    }
}