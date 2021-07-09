package io.worthi.forgotPassword

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.controller.Controller
import io.worthi.forgotPassword.response.ResetPasswordResponse
import retrofit2.Response
import java.util.ArrayList

class ForgotPasswrod : BaseClass(),Controller.ForgotPassAPI {

    private lateinit var email: EditText
    private lateinit var submit : LinearLayout
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_passwrod)

        init()
        listeners()
    }

    private fun listeners() {
        submit.setOnClickListener {
            when{
                email.text.isEmpty()-> {
                    email.requestFocus()
                    email.setError("Enter Registered email")
                } else -> {
                if (utility.isConnectingToInternet(this)) {

                    pd.show()
                    pd.setContentView(R.layout.loading)

                    controller.Resetpassword("application/json",email.text.toString())
                } else {
                    utility.relative_snackbar(
                        window?.currentFocus,
                        getString(R.string.nointernet),
                        getString(R.string.close_up)
                    )
                }
                }
            }

        }

    }

    fun init()
    {
        email = findViewById(R.id.email)
        submit = findViewById(R.id.submit)

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)

    }

    override fun onForgotSuccessAPI(success: Response<ArrayList<ResetPasswordResponse>>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.code()==201)
            {
                email.setText("")
                utility.relative_snackbar(
                    window?.currentFocus,
                    "Reset Paswor link sent to your email",
                    getString(R.string.close_up)
                )
            }else
            {
                utility.relative_snackbar(
                    window?.currentFocus,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        }else {
            utility.relative_snackbar(
                window?.currentFocus,
                "Bad Request",
                getString(R.string.close_up)
            )
        }
    }

    override fun onError(error: String) {
        pd.dismiss()
        utility.relative_snackbar(
            window?.currentFocus,
            error,
            getString(R.string.close_up)
        )
    }
}