package io.worthi.feedScreen.fragments.profile

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.worthi.Constant.BaseFrag
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.controller.Controller
import io.worthi.feedScreen.fragments.profile.response.LogoutResponse
import io.worthi.feedScreen.fragments.profile.response.SendFeedbackResponse
import io.worthi.feedScreen.fragments.profile.response.UserResponse
import io.worthi.loginscreen.LoginScreen
import io.worthi.shareapp.ShareAppScreen
import retrofit2.Response


class ProfileFrag : BaseFrag(), Controller.LogoutAPI, Controller.SendFeedbackAPI,
    Controller.UserAPI {

    private lateinit var shareapp: Button
    private lateinit var logout: Button
    private lateinit var feedback_bt: Button
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var price : TextView
    private lateinit var interest : TextView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View
        view = inflater.inflate(R.layout.fragment_profile, container, false)

        init(view)
        listeners()
        return view
    }

    private fun listeners() {
        shareapp.setOnClickListener {
            startActivity(Intent(context, ShareAppScreen::class.java))
        }

        logout.setOnClickListener {
            if (utility.isConnectingToInternet(context)) {

                pd.show()
                pd.setContentView(R.layout.loading)

                controller.Logout("Bearer " + getStringVal(Constants.TOKEN))
            } else {
                utility.relative_snackbar(
                    activity?.window?.currentFocus,
                    getString(R.string.nointernet),
                    getString(R.string.close_up)
                )
            }
        }

        feedback_bt.setOnClickListener {
            dialog = Dialog(requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.feedback)
            val window = dialog.window
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )

            var feedbacket = dialog.findViewById<EditText>(R.id.feedbacket)
            var submitbt = dialog.findViewById<Button>(R.id.submitbt)

            submitbt.setOnClickListener {
                when {
                    feedbacket.text.toString().isEmpty() -> {
                        feedbacket.requestFocus()
                        feedbacket.setError("Add feedback")
                    }
                    else -> {
                        if (utility.isConnectingToInternet(context)) {

                            pd.show()
                            pd.setContentView(R.layout.loading)

                            controller.SendFeedback(
                                "jwt=" + getStringVal(Constants.TOKEN),
                                "application/json",
                                feedbacket.text.toString()
                            )
                        } else {
                            utility.relative_snackbar(
                                activity?.window?.currentFocus,
                                getString(R.string.nointernet),
                                getString(R.string.close_up)
                            )
                        }
                    }
                }
            }
            dialog.show()

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
        controller.Controller(this, this, this)
        if (utility.isConnectingToInternet(context)) {

            pd.show()
            pd.setContentView(R.layout.loading)

            controller.User("jwt=" + getStringVal(Constants.TOKEN), "application/json")
        } else {
            utility.relative_snackbar(
                activity?.window?.currentFocus,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        shareapp = view.findViewById(R.id.shareapp)
        logout = view.findViewById(R.id.logout)
        feedback_bt = view.findViewById(R.id.feedback_bt)
        name = view.findViewById(R.id.name)
        email = view.findViewById(R.id.email)
        price = view.findViewById(R.id.price)
        interest = view.findViewById(R.id.interest)
    }

    override fun onLogoutSuccess(response: Response<LogoutResponse>) {
        pd.dismiss()
        if (response?.code() == 201) {
            clearStringVal(Constants.TOKEN)
            startActivity(
                Intent(
                    context,
                    LoginScreen::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
            activity?.finish()
        } else {
            utility.relative_snackbar(
                activity?.window?.currentFocus,
                response.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onSendfeedbackAPI(successs: Response<ArrayList<SendFeedbackResponse>>) {
        pd.dismiss()
        if (successs.isSuccessful) {
            utility.relative_snackbar(
                activity?.window?.decorView,
                "Feedback sent",
                getString(R.string.close_up)
            )
//            if (successs.code()==200||successs.code()==201||successs.code()==202)
//            {
//
//            } else {
//                utility.relative_snackbar(
//                    activity?.window?.decorView,
//                    "Bad Request",
//                    getString(R.string.close_up)
//                )
//            }
        } else {
            utility.relative_snackbar(
                activity?.window?.decorView,
                "Bad Request",
                getString(R.string.close_up)
            )
        }
    }

    override fun onUserSuccessAPI(success: Response<UserResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.code() == 200) {
                name.setText(success.body()?.name)
                email.setText(success.body()?.email)
                price.setText(success.body()?.balance.toString()+"$")
               // interest.setText(success.body()?.interests?.interests?.size!!)
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

    override fun onError(error: String) {
        pd.dismiss()
        utility.relative_snackbar(
            activity?.window?.decorView,
            error,
            getString(R.string.close_up)
        )
    }


}