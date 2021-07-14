package io.worthi.shareapp

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.controller.Controller
import io.worthi.feedScreen.FeedScreen
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse
import retrofit2.Response


class ShareAppScreen : BaseClass(), Controller.GetCampainsAPI {

    private lateinit var backbt: ImageButton
    private lateinit var share:Button
    private lateinit var webview: WebView
    private lateinit var button:String
    private lateinit var url:String
    private lateinit var pos: String
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var backweb:ImageButton
    private lateinit var backtofeed : Button
    private lateinit var layout: LinearLayout
    private lateinit var controller: Controller
    private lateinit var image: ImageView
    private lateinit var getCampains: ArrayList<GetCampainsResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_app_screen)

        init()
        button = intent.getStringExtra("button").toString()
        url=  intent.getStringExtra("url").toString()
        pos = intent.getStringExtra("pos").toString()

        listeners()
    }

    private fun listeners() {
        backbt.setOnClickListener {
            onBackPressed()
        }

        backweb.setOnClickListener {
            webview.visibility = View.GONE
            backtofeed.visibility = View.VISIBLE
            layout.visibility = View.GONE
        }
        share.setText(button)
        share.setOnClickListener {
            webview.visibility = View.VISIBLE
            layout.visibility = View.VISIBLE
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setLoadWithOverviewMode(true);
            webview.getSettings().setUseWideViewPort(true);

            webview.getSettings().setSupportZoom(true);
            webview.getSettings().setBuiltInZoomControls(true);
            webview.getSettings().setDisplayZoomControls(false);
            webview.getSettings().setDomStorageEnabled(true);
            webview.getSettings().setAppCacheEnabled(true);
            webview.getSettings().setLoadsImagesAutomatically(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            webview .setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            webview.setScrollbarFadingEnabled(false);

//            webview.setWebViewClient(object : WebViewClient() {
//                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
//                    pd.show()
//                }
//
//                override fun onPageFinished(view: WebView, url: String) {
//                    pd.dismiss()
//                    /*Intent intent = new Intent(ConnectStripe.this, BookEventActivity.class);
//                intent.putExtra("stripeStatus","1");
//                intent.putExtra("isFrom","stripepage");
//                startActivity(intent);*/
////                finish();
//                }
//            })
            webview.loadUrl(url)
        }

        backtofeed.setOnClickListener {
            webview.visibility = View.GONE
        }
    }

    fun init()
    {
        backbt = findViewById(R.id.backbt)
        share = findViewById(R.id.share)
        webview = findViewById(R.id.webview)
        backweb = findViewById(R.id.backweb)
        backtofeed = findViewById(R.id.backtofeed)
        layout = findViewById(R.id.layout)
        image = findViewById(R.id.image)

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)
//        if (utility.isConnectingToInternet(this)) {
//
//            pd.show()
//            pd.setContentView(R.layout.loading)
//
//            controller.GetCampains("jwt=" + getStringVal(Constants.TOKEN), "application/json")
//        } else {
//            utility.relative_snackbar(
//                window.decorView,
//                getString(R.string.nointernet),
//                getString(R.string.close_up)
//            )
//        }

    }

    override fun onGetCampainsSuccessAPI(success: Response<ArrayList<GetCampainsResponse>>) {
       pd.dismiss()
        if (success.isSuccessful) {
            if (success.code() == 200) {

                getCampains = ArrayList()
                for (i in 0 until success.body()!!.size) {
                    getCampains.add(success.body()?.get(i)!!)
                    //Glide.with(this).load(getCampains.get(pos.toInt()).callToAction.callToActionImage).placeholder(R.drawable.logo).into(image)
                }


            }else {
                utility.relative_snackbar(
                    window.decorView,
                    "Unable to get Image",
                    getString(R.string.close_up)
                )
            }

        }else {
            utility.relative_snackbar(
                window.decorView,
                "Unable to get Image",
                getString(R.string.close_up)
            )
        }
    }

    override fun onError(error: String) {
        pd.dismiss()
        utility.relative_snackbar(
            window.decorView,
            error,
            getString(R.string.close_up)
        )
    }
}