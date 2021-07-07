package io.worthi.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import io.worthi.R
import io.worthi.VerifyEmail.VerifyEmailScreen

class SignUpScreen : AppCompatActivity() {
    private lateinit var signup: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)

        init()
        listeners()
    }

    private fun listeners() {
        signup.setOnClickListener {
            startActivity(Intent(this,VerifyEmailScreen::class.java))
        }
    }

    fun init()
    {
        signup = findViewById(R.id.signup)
    }
}