package io.worthi.submitQualifier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import io.worthi.R
import io.worthi.feedScreen.FeedScreen
import io.worthi.shareapp.ShareAppScreen

class CongratulationScreen : AppCompatActivity() {

    private lateinit var back: ImageButton
    private lateinit var url : String
    private lateinit var button:String
    private lateinit var pos : String
    private lateinit var link: Button
    private lateinit var webview : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulation_screen)
        back = findViewById(R.id.back)
        link = findViewById(R.id.link)

        url = intent.getStringExtra("url").toString()
        button = intent.getStringExtra("button").toString()
        pos = intent.getStringExtra("pos").toString()

        back.setOnClickListener {
            startActivity(Intent(this,FeedScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }

        link.setText(button)
        link.setOnClickListener {
            startActivity(Intent(this, ShareAppScreen::class.java).putExtra("url",url).putExtra("button",button).putExtra("pos",pos))
           //webview.loadUrl(url)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,FeedScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }
}