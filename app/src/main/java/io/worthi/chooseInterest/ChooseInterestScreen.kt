package io.worthi.chooseInterest

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.SparseBooleanArray
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.chooseInterest.adapter.InterestAdapter
import io.worthi.chooseInterest.response.AddInterestsResponse
import io.worthi.chooseInterest.response.GetInterestsResponse
import io.worthi.controller.Controller
import io.worthi.feedScreen.FeedScreen
import retrofit2.Response


class ChooseInterestScreen : BaseClass(), Controller.GetInterestAPI, GetSelectedInterest_IF ,Controller.AddInterestAPI{
    private lateinit var continuefeed: Button
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var listview : RecyclerView
    private lateinit var interets : ArrayList<GetInterestsResponse>
    var sparseBooleanArray: SparseBooleanArray? = null
    private lateinit var interestID : ArrayList<String>
    private lateinit var interestValue:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_interest_screen)

        init()
        interestID = ArrayList()
        getselectedinterestIf = this

        listeners()
    }

    private fun listeners() {
        continuefeed.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {
                hideKeyboard()
                pd.show()
                pd.setContentView(R.layout.loading)

                controller.AddInterest("jwt="+getStringVal(Constants.S_TOKEN),"application/json",interestValue)
            } else {
                utility.relative_snackbar(
                    window.currentFocus,
                    getString(R.string.nointernet),
                    getString(R.string.close_up)
                )
            }
        }
    }

    fun init() {
        continuefeed = findViewById(R.id.continuefeed)
        listview = findViewById(R.id.listview)

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this,this)
        if (utility.isConnectingToInternet(this)) {
            hideKeyboard()
            pd.show()
            pd.setContentView(R.layout.loading)

            controller.GetInterest("jwt="+getStringVal(Constants.S_TOKEN),"application/json")
        } else {
            utility.relative_snackbar(
                window.currentFocus,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

    }


    companion object {
        var getselectedinterestIf : GetSelectedInterest_IF? = null
    }

    private fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

    override fun onGetInterestAPI(success: Response<ArrayList<GetInterestsResponse>>) {
        pd.dismiss()
        if (success.isSuccessful) {

            if (success.code() == 200) {
                interets = ArrayList()
                for ( i in 0 until  success.body()!!.size)
                {
                    interets.add(success.body()?.get(i)!!)
                    Log.d("interests",""+interets.size)
                }
                val layoutManager = GridLayoutManager(this, 1)
                listview.setLayoutManager(layoutManager)
                val adapter = InterestAdapter(this, interets)
                listview.setAdapter(adapter)
            } else {
                utility.relative_snackbar(
                    window.decorView,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility.relative_snackbar(
                window.decorView,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onAddInterestAPI(successs: Response<AddInterestsResponse>) {
        pd.dismiss()
        if (successs.isSuccessful)
        {
            if (successs.code()==201)
            {
                startActivity(Intent(this, FeedScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                finish()
                setStringVal(Constants.TOKEN,getStringVal(Constants.S_TOKEN))
            } else {
                utility.relative_snackbar(
                    window.decorView,
                    successs.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility.relative_snackbar(
                window.decorView,
                successs.message(),
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

    override fun getID(id: String, pos: String) {
        if (pos.equals("0"))
        {
            if (interestID.contains(id))
            {
                interestID.remove(pos)
            }

        } else {
                interestID.add(id.toString())
            interestValue = TextUtils.join(",",interestID)

        }


        if (interestID.size>0)
        {
            continuefeed.isEnabled = true
            continuefeed.setBackgroundColor(resources.getColor(R.color.black))
        } else {
            continuefeed.isEnabled = false
            continuefeed.setBackgroundColor(resources.getColor(R.color.gray))
        }
    }
}