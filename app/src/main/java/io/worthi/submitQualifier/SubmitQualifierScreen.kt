package io.worthi.submitQualifier

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.controller.Controller
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse
import io.worthi.feedScreen.fragments.interactions.adapter.IntractionAdapter
import io.worthi.feedScreen.fragments.interactions.response.GetInteractionResponse
import io.worthi.submitQualifier.adapter.QuestionsAdapter
import retrofit2.Response

class SubmitQualifierScreen : BaseClass(),Controller.GetCampainsAPI {

    private lateinit var back:ImageButton
    private lateinit var qualifierrecyler: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var getCampains : ArrayList<GetCampainsResponse>
    private lateinit var getQuestions :ArrayList<GetCampainsResponse.Question>
    private lateinit var pos:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_qualifier_screen)

        init()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener {
            onBackPressed()
        }
    }

    fun init()
    {
        pos = intent.getStringExtra("pos").toString()
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        controller = Controller()
        controller.Controller(this)

        back = findViewById(R.id.back)
        qualifierrecyler = findViewById(R.id.qualifierrecyler)

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

                getCampains = ArrayList()
                getQuestions = ArrayList()
                for ( i in 0 until  success.body()!!.size)
                {
                    getCampains.add(success.body()?.get(i)!!)
                    Log.d("getCamps",""+getQuestions.size)
                }

                for (i1 in 0 until getCampains.get(pos.toInt()).questions.size)
                {
                    getQuestions.add(getCampains.get(pos.toInt()).questions.get(i1))
                }

                val layoutManager = GridLayoutManager(context, 1)
                qualifierrecyler.setLayoutManager(layoutManager)
                val adapter = QuestionsAdapter(this, getQuestions)
                qualifierrecyler.setAdapter(adapter)

                Log.d("questions",""+getQuestions.get(0).qualifierQuestion)
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