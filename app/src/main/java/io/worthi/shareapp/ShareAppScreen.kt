package io.worthi.shareapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import io.worthi.BuildConfig
import io.worthi.R


class ShareAppScreen : AppCompatActivity() {

    private lateinit var backbt: ImageButton
    private lateinit var share:Button
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

        share.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Worthi")
                var shareMessage = "\nLet me recommend you this application\n\n"
                shareMessage =
                    """
                    ${shareMessage}https://play.google.com/store/apps/details?id=com.whatsapp&hl=en_IN&gl=US
                    
                    
                    """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }
    }

    fun init()
    {
        backbt = findViewById(R.id.backbt)
        share = findViewById(R.id.share)
    }
}