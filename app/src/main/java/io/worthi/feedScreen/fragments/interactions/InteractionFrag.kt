package io.worthi.feedScreen.fragments.interactions

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.worthi.Constant.BaseClass
import io.worthi.Constant.BaseFrag
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.controller.Controller
import io.worthi.feedScreen.fragments.feeds.adapter.GetCampainsAdapter
import io.worthi.feedScreen.fragments.feeds.response.GetCampainsResponse
import io.worthi.feedScreen.fragments.interactions.adapter.IntractionAdapter
import io.worthi.feedScreen.fragments.interactions.response.GetInteractionResponse
import retrofit2.Response


class InteractionFrag : BaseFrag(),Controller.GetInteractionAPI {

    private lateinit var interactionrecycler: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var getInteractions : ArrayList<GetInteractionResponse>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View
        view = inflater.inflate(R.layout.fragment_interaction, container, false)

        init(view)
        return view
    }

    private fun init(view: View) {
        interactionrecycler = view.findViewById(R.id.interactionrecycler)

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

            controller.GetInteractions("jwt="+getStringVal(Constants.TOKEN),"application/json")
        } else {
            utility.relative_snackbar(
                requireActivity().window.decorView,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }


    }

    override fun onGetInteractionAPI(success: Response<ArrayList<GetInteractionResponse>>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.code()==200)
            {

                getInteractions = ArrayList()
                for ( i in 0 until  success.body()!!.size)
                {
                    getInteractions.add(success.body()?.get(i)!!)
                    Log.d("getCamps",""+getInteractions.size)
                }

                val layoutManager = GridLayoutManager(context, 1)
                interactionrecycler.setLayoutManager(layoutManager)
                val adapter = IntractionAdapter(context, getInteractions)
                interactionrecycler.setAdapter(adapter)
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