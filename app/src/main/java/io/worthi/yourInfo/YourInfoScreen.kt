package io.worthi.yourInfo

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.chooseInterest.ChooseInterestScreen
import io.worthi.controller.Controller
import io.worthi.yourInfo.response.YourInfoResponse
import retrofit2.Response

class YourInfoScreen : BaseClass(), Controller.YourInfoAPI {

    private lateinit var continuebt: Button
    private lateinit var age: Spinner
    private lateinit var gender: Spinner
    private lateinit var ageTV: String
    private lateinit var genderTV: String
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var location: EditText
    private lateinit var utility: Utility

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_info_screen)

        init()
        listeners()
    }

    private fun listeners() {
        continuebt.setOnClickListener {

            when {
                ageTV.equals("Select Age") -> {
                    utility.relative_snackbar(
                        window.currentFocus,
                        "Select Age",
                        getString(R.string.close_up)
                    )

                }

                genderTV.equals("Select Gender") -> {
                    utility.relative_snackbar(
                        window.currentFocus,
                        "Select Gender",
                        getString(R.string.close_up)
                    )
                }

                location.text.isEmpty() -> {
                    location.requestFocus()
                    location.setError("Enter Location")
                }
                else -> {

                    if (utility.isConnectingToInternet(this)) {
                        hideKeyboard()
                        pd.show()
                        pd.setContentView(R.layout.loading)

                        controller.YourInfo("jwt="+getStringVal(Constants.TOKEN),"application/json",ageTV, genderTV, location.text.toString())
                    } else {
                        utility.relative_snackbar(
                            window.currentFocus,
                            getString(R.string.nointernet),
                            getString(R.string.close_up)
                        )
                    }

                }
            }

        }

        val languages = resources.getStringArray(R.array.ages)
        val adapter = ArrayAdapter(
            this,
            R.layout.spinnertv, languages
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        age.adapter = adapter

        age.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                // your code here
                Log.d("soinner", "" + languages[position])
                ageTV = languages[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })

        val languages1 = resources.getStringArray(R.array.gender)
        val adapter1 = ArrayAdapter(
            this,
            R.layout.spinnertv, languages1
        )

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gender.adapter = adapter1

        gender.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                // your code here
                Log.d("soinner", "" + languages[position])
                genderTV = languages1[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })
    }

    fun init() {
        continuebt = findViewById(R.id.continuebt)
        age = findViewById(R.id.age)
        gender = findViewById(R.id.gender)
        location = findViewById(R.id.location)

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)
    }

    private fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

    override fun onYourInfoAPI(success: Response<YourInfoResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.code() == 201) {
                startActivity(
                    Intent(
                        this,
                        ChooseInterestScreen::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
                finish()
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
}