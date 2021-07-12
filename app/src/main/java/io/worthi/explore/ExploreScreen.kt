package io.worthi.explore

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.controller.Controller
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse
import io.worthi.submitQualifier.IF.GetQandA_IF
import io.worthi.submitQualifier.SubmitQualifierScreen
import io.worthi.submitQualifier.model.qAndA
import retrofit2.Response
import java.io.FileOutputStream


class ExploreScreen : BaseClass(),Controller.GetCampainsAPI {


    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var getCamps : ArrayList<GetCampainsResponse>
    private lateinit var back: ImageButton
    private lateinit var pos :String
    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var subtitle: TextView
    private lateinit var questiontv : TextView
    private lateinit var takequalifier: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_screen)

        init()

        getCamps = ArrayList()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener {
            onBackPressed()
        }

        takequalifier.setOnClickListener {
            startActivity(Intent(this,SubmitQualifierScreen::class.java).putExtra("pos",pos).putExtra("campainID",getCamps.get(pos.toInt()).id.toString()))
        }
    }

    companion object {
        var getqandaIf : GetQandA_IF? = null
    }

    fun init()
    {
        pos = intent.getStringExtra("pos").toString()
        back = findViewById(R.id.back)
        image = findViewById(R.id.image)
        title = findViewById(R.id.title)
        subtitle = findViewById(R.id.subtitle)
        questiontv = findViewById(R.id.questiontv)
        takequalifier = findViewById(R.id.takequalifier)

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)
        if (utility.isConnectingToInternet(this)) {

            pd.show()
            pd.setContentView(R.layout.loading)

            controller.GetCampains("jwt="+getStringVal(Constants.TOKEN),"application/json")
        } else {
            utility.relative_snackbar(
                window.decorView,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    override fun onGetCampainsSuccessAPI(success: Response<ArrayList<GetCampainsResponse>>) {
       pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.code()==200)
            {
                for ( i in 0 until  success.body()!!.size)
                {
                    getCamps.add(success.body()?.get(i)!!)
                    Log.d("getCamps",""+getCamps.size)
                }

//                var bmp : Bitmap? =null
//                try {
//                    val out = FileOutputStream(success.body()?.get(pos.toInt())?.addDisplay?.addDisplayImage)
//                    bmp?.compress(Bitmap.CompressFormat.PNG, 100, out) //100-best quality
//                    out.close()
//                    Glide.with(this).load(bmp).placeholder(R.drawable.logo).into(image)
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
                Picasso.get().load(success.body()?.get(pos.toInt())?.addDisplay?.addDisplayImage).into(image);
                title.setText(success.body()?.get(pos.toInt())!!.name)
                subtitle.setText(success.body()?.get(pos.toInt())!!.description)
                questiontv.setText(success.body()?.get(pos.toInt())!!.questions.size.toString()+" of "+success.body()?.get(pos.toInt())!!.questions.size.toString()+" Questions")
            }else {
                utility.relative_snackbar(
                    window.decorView,
                    "Bad Request",
                    getString(R.string.close_up)
                )
            }

        }else {
            utility.relative_snackbar(
                window.decorView,
                "Bad Request",
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