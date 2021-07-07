package io.worthi.yourInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.worthi.R
import io.worthi.chooseInterest.ChooseInterestScreen

class YourInfoScreen : AppCompatActivity() {

    private lateinit var continuebt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_info_screen)

        init()
        listeners()
    }

    private fun listeners() {
        continuebt.setOnClickListener {
            startActivity(Intent(this,ChooseInterestScreen::class.java))
        }
    }

    fun init()
    {
        continuebt = findViewById(R.id.continuebt)
    }
}