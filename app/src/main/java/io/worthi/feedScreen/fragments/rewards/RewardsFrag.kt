package io.worthi.feedScreen.fragments.rewards

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import io.worthi.Constant.BaseFrag
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.controller.Controller
import io.worthi.feedScreen.fragments.profile.response.UserResponse
import retrofit2.Response


class RewardsFrag : BaseFrag(),Controller.UserAPI,Controller.CashOutAPI {

    private lateinit var reward:TextView
    private lateinit var cashoutbt: Button
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private  var bal : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View
        view = inflater.inflate(R.layout.fragment_rewards, container, false)

        init(view)
        lisetners()
        return view
    }

    private fun lisetners() {
        cashoutbt.setOnClickListener {

            if (bal>5)
            {
                if (utility.isConnectingToInternet(context)) {

                    pd.show()
                    pd.setContentView(R.layout.loading)

                    controller.CashOut("jwt=" + getStringVal(Constants.TOKEN), "application/json",reward.text.toString())
                } else {
                    utility.relative_snackbar(
                        activity?.window?.decorView,
                        getString(R.string.nointernet),
                        getString(R.string.close_up)
                    )
                }
            }else {
                utility.relative_snackbar(
                    activity?.window?.decorView,
                    "You can withdraw $5 or more than $5 amount only",
                    getString(R.string.close_up)
                )
            }

        }
    }

    private fun init(view: View?) {
        reward = view?.findViewById(R.id.reward)!!
        cashoutbt = view?.findViewById(R.id.cashoutbt)

        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this, this)
        if (utility.isConnectingToInternet(context)) {

            pd.show()
            pd.setContentView(R.layout.loading)

            controller.User("jwt=" + getStringVal(Constants.TOKEN), "application/json")
        } else {
            utility.relative_snackbar(
                activity?.window?.decorView,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    override fun onUserSuccessAPI(success: Response<UserResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.code()==200)
            {
                reward.setText(success.body()?.balance.toString()!!)
                bal = success.body()?.balance?.toInt()!!
            } else {
                utility.relative_snackbar(
                    activity?.window?.decorView,
                    "Bad Request",
                    getString(R.string.close_up)
                )
            }
        } else {
            utility.relative_snackbar(
                activity?.window?.decorView,
                "Bad Request",
                getString(R.string.close_up)
            )
        }
    }

    override fun onCashoutSuccessAPI(successs: Response<UserResponse>) {
        pd.dismiss()
        if (successs.isSuccessful)
        {
            if (successs.code()==401)
            {
                utility.relative_snackbar(
                    activity?.window?.decorView,
                    "You can withdraw $5 or more than $5 amount only",
                    getString(R.string.close_up)
                )
            }else {
                utility.relative_snackbar(
                    activity?.window?.decorView,
                    successs.message(),
                    getString(R.string.close_up)
                )
            }
        }else{
            utility.relative_snackbar(
                activity?.window?.decorView,
                "Bad Request",
                getString(R.string.close_up)
            )
        }
    }

    override fun onError(error: String) {
        pd.dismiss()
        utility.relative_snackbar(
            activity?.window?.decorView,
            error,
            getString(R.string.close_up)
        )
    }
}