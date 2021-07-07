package io.worthi.chooseInterest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.worthi.R
import io.worthi.feedScreen.FeedScreen

class ChooseInterestScreen : AppCompatActivity() {
    private lateinit var continuefeed: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_interest_screen)

        init()
        listeners()
    }

    private fun listeners() {
        continuefeed.setOnClickListener {
            startActivity(Intent(this,FeedScreen::class.java))
        }
    }

    fun init()
    {
        continuefeed = findViewById(R.id.continuefeed)
    }
}