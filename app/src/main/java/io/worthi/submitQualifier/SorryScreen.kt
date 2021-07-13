package io.worthi.submitQualifier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import io.worthi.R
import io.worthi.feedScreen.FeedScreen

class SorryScreen : AppCompatActivity() {
    private lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorry_screen)

        back = findViewById(R.id.back)

        back.setOnClickListener {
            startActivity(
                Intent(this, FeedScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,FeedScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }
}