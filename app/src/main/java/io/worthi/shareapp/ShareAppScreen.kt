package io.worthi.shareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import io.worthi.R

class ShareAppScreen : AppCompatActivity() {

    private lateinit var backbt: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_app_screen)

        init()
        listeners()
    }

    private fun listeners() {
        backbt.setOnClickListener {
            onBackPressed()
        }
    }

    fun init()
    {
        backbt = findViewById(R.id.backbt)
    }
}