package io.worthi.feedScreen.fragments.profile

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import io.worthi.Constant.BaseFrag
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.controller.Controller
import io.worthi.feedScreen.fragments.profile.response.LogoutResponse
import io.worthi.loginscreen.LoginScreen
import io.worthi.shareapp.ShareAppScreen
import retrofit2.Response


class ProfileFrag : BaseFrag(),Controller.LogoutAPI {

private lateinit var shareapp: Button
private lateinit var logout: Button
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View
        view = inflater.inflate(R.layout.fragment_profile, container, false)

        init(view)
        listeners()
        return  view
    }

    private fun listeners() {
        shareapp.setOnClickListener {
            startActivity(Intent(context,ShareAppScreen::class.java))
        }

        logout.setOnClickListener {
            if (utility.isConnectingToInternet(context)) {

                pd.show()
                pd.setContentView(R.layout.loading)

                controller.Logout("Bearer "+getStringVal(Constants.TOKEN))
            } else {
                utility.relative_snackbar(
                    activity?.window?.currentFocus,
                    getString(R.string.nointernet),
                    getString(R.string.close_up)
                )
            }
        }
    }

    private fun init(view: View) {
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)

        shareapp = view.findViewById(R.id.shareapp)
        logout =view.findViewById(R.id.logout)
    }

    override fun onLogoutSuccess(response: Response<LogoutResponse>) {
        pd.dismiss()
        if (response?.code()==201)
        {
            clearStringVal(Constants.TOKEN)
            startActivity(Intent(context,LoginScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            activity?.finish()
        }else {
            utility.relative_snackbar(
                activity?.window?.currentFocus,
                response.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onError(error: String) {
        pd.dismiss()
        utility.relative_snackbar(
            activity?.window?.currentFocus,
            error,
            getString(R.string.close_up)
        )
    }


}