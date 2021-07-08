package io.worthi.VerifyEmail

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.VerifyEmail.response.VerifyResponse
import io.worthi.controller.Controller
import io.worthi.yourInfo.YourInfoScreen
import retrofit2.Response
import java.text.DecimalFormat
import java.text.NumberFormat


class VerifyEmailScreen : BaseClass(), Controller.VerifyEmailAPI {

    private lateinit var verifybt: Button
    private lateinit var timer: TextView
    private lateinit var resendcode: TextView
    private lateinit var firstPinView: PinView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var phonenumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email_screen)

        init()
        listeners()

        object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Used for formatting digit to be in 2 digits only
                val f: NumberFormat = DecimalFormat("00")
                // val hour = millisUntilFinished / 3600000 % 24
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                timer.setText(
                    f.format(min) + ":" + f.format(
                        sec
                    )
                )
                resendcode.isEnabled = false
                resendcode.setTextColor(resources.getColor(R.color.gray))
            }

            // When the task is over it will print 00:00:00 there
            override fun onFinish() {
                timer.setText("00:00")
                resendcode.isEnabled = true
                resendcode.setTextColor(resources.getColor(R.color.themecolor))
            }
        }.start()
    }

    private fun listeners() {
        verifybt.setOnClickListener {

            var code = firstPinView.text
            if (code!!.length < 6) {
                utility.relative_snackbar(
                    window.currentFocus,
                    "Enter valid OTP",
                    getString(R.string.close_up)
                )
            } else {

                if (utility.isConnectingToInternet(this)) {
                    hideKeyboard()
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    Log.d("token",""+getStringVal(Constants.S_TOKEN))
                    controller.Verify("jwt="+getStringVal(Constants.S_TOKEN),"application/json",phonenumber, code.toString())

                } else {
                    utility.relative_snackbar(
                        window.currentFocus,
                        getString(R.string.nointernet),
                        getString(R.string.close_up)
                    )
                }
            }
            Log.d("code", "" + code)
            //  startActivity(Intent(this,YourInfoScreen::class.java))
        }


        resendcode.setOnClickListener {
            Log.d("click", "TEST")
        }
    }

    fun init() {
        phonenumber = intent.getStringExtra("phonenumber").toString()!!
        Log.d("phone",phonenumber)
        timer = findViewById(R.id.timer)
        verifybt = findViewById(R.id.verifybt)
        resendcode = findViewById(R.id.resendcode)
        firstPinView = findViewById(R.id.firstPinView)
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)
    }

    override fun onVerifySuccess(success: Response<VerifyResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.code() == 201) {
                startActivity(
                    Intent(
                        this,
                        YourInfoScreen::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
                finish()
               // finish()
            } else {
                utility.relative_snackbar(
                    window.currentFocus,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility.relative_snackbar(
                window.currentFocus,
                success.message(),
                getString(R.string.close_up)
            )
        }

    }

    override fun onError(error: String) {
        pd.dismiss()
        utility.relative_snackbar(
            window.currentFocus,
            error,
            getString(R.string.close_up)
        )
    }

    private fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }
}