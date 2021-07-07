package io.worthi.VerifyEmail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.worthi.R
import io.worthi.yourInfo.YourInfoScreen

class VerifyEmailScreen : AppCompatActivity() {

    private lateinit var verifybt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email_screen)

        init()
        listeners()
    }

    private fun listeners() {
        verifybt.setOnClickListener {

                startActivity(Intent(this,YourInfoScreen::class.java))
            }

    }

    fun init()
    {
        verifybt = findViewById(R.id.verifybt)
    }
}