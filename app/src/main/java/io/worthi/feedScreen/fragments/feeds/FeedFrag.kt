package io.worthi.feedScreen.fragments.feeds

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.worthi.Constant.BaseFrag
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.chooseInterest.adapter.InterestAdapter
import io.worthi.controller.Controller
import io.worthi.feedScreen.fragments.feeds.adapter.GetCampainsAdapter
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse
import retrofit2.Response


class FeedFrag : BaseFrag() ,Controller.GetCampainsAPI{


    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var getcampainsrecycler: RecyclerView
    private lateinit var balancetv : TextView
    private lateinit var getCamps : ArrayList<GetCampainsResponse>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var  view:View
        view = inflater.inflate(R.layout.fragment_feed, container, false)
        getCamps = ArrayList()
        init(view)
        return view
    }

    private fun init(view: View) {
        getcampainsrecycler = view.findViewById(R.id.getcampainsrecycler)
        balancetv = view.findViewById(R.id.balancetv)
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)
        if (utility.isConnectingToInternet(context)) {

            pd.show()
            pd.setContentView(R.layout.loading)

            controller.GetCampains("jwt="+getStringVal(Constants.TOKEN),"application/json")
        } else {
            utility.relative_snackbar(
                requireActivity().window.decorView,
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
                if (success.body()!!.size>0)
                {
                    balancetv.setText(success.body()!!.get(0).user.balance.toString())
                }

                val layoutManager = GridLayoutManager(context, 1)
                getcampainsrecycler.setLayoutManager(layoutManager)
                val adapter = GetCampainsAdapter(context, getCamps)
                getcampainsrecycler.setAdapter(adapter)
            }else {
                utility.relative_snackbar(
                    requireActivity().window.decorView,
                    "Bad Request",
                    getString(R.string.close_up)
                )
            }

        }else {
            utility.relative_snackbar(
                requireActivity().window.decorView,
                "Bad Request",
                getString(R.string.close_up)
            )
        }


    }

    override fun onError(error: String) {
        pd.dismiss()
        utility.relative_snackbar(
            requireActivity().window.decorView,
            error,
            getString(R.string.close_up)
        )
    }


}