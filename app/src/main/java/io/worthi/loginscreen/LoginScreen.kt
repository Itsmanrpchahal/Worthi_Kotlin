package io.worthi.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import io.worthi.R
import io.worthi.SignUp.SignUpScreen

class LoginScreen : AppCompatActivity() {

    private lateinit var signIn:LinearLayout
    private lateinit var signUptext: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        init()
        listeners()
    }

    private fun listeners() {
        signUptext.setOnClickListener {
            startActivity(Intent(this,SignUpScreen::class.java))
        }
    }

    fun init()
    {
        signIn = findViewById(R.id.signIn)
        signUptext = findViewById(R.id.signUptext)
    }
}