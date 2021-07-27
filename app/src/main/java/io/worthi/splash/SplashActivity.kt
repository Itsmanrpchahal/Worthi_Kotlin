package io.worthi.splash

import android.Manifest
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import io.worthi.Constant.BaseClass
import io.worthi.R
import io.worthi.Utilities.Constants
import io.worthi.Utilities.Utility
import io.worthi.VerifyEmail.VerifyEmailScreen
import io.worthi.chooseInterest.ChooseInterestScreen
import io.worthi.controller.Controller
import io.worthi.feedScreen.FeedScreen
import io.worthi.feedScreen.fragments.feeds.response.UserResponse
import io.worthi.loginscreen.LoginScreen
import io.worthi.welcomescreens.MainActivity
import io.worthi.yourInfo.YourInfoScreen
import retrofit2.Response


class SplashActivity : BaseClass() ,Controller.UserAPI{

    var permissions = arrayOf<String>(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_CONTACTS
    )
    var perclear = false
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

       // methodRequiresPermission()
        init()

        if (getStringVal(Constants.WELCOMECHECK).equals("1"))
        {
            if (!getStringVal(Constants.TOKEN).equals(""))
            {
                if (utility.isConnectingToInternet(this@SplashActivity)) {
                    controller.User("jwt="+getStringVal(Constants.TOKEN),"application/json")
                } else {
                    utility.relative_snackbar(
                        window.currentFocus,
                        getString(R.string.nointernet),
                        getString(R.string.close_up)
                    )
                }
            }else {
                startActivity(Intent(this@SplashActivity,LoginScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                finish()
            }
        }else {
            startActivity(Intent(this@SplashActivity,MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            finish()
        }
//        Handler(Looper.getMainLooper()).postDelayed({
//
////requestStoragePermission()
//
//
//        }, 3000)
    }

    fun init()
    {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this)
    }

    private fun requestStoragePermission() {
        Dexter.withActivity(this)
            .withPermissions(

            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        perclear = true




                        // Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (getStringVal(Constants.WELCOMECHECK).equals("1"))
                        {
                            if (!getStringVal(Constants.TOKEN).equals(""))
                            {
                                if (utility.isConnectingToInternet(this@SplashActivity)) {
                                    controller.User("jwt="+getStringVal(Constants.TOKEN),"application/json")
                                } else {
                                    utility.relative_snackbar(
                                        window.currentFocus,
                                        getString(R.string.nointernet),
                                        getString(R.string.close_up)
                                    )
                                }
                            }else {
                                startActivity(Intent(this@SplashActivity,LoginScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                                finish()
                            }
                        }else {
                            startActivity(Intent(this@SplashActivity,MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                            finish()
                        }
                    }

                    // check for permanent denial of any permission

                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener { error ->
                Toast.makeText(
                    applicationContext,
                    "Error occurred! $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .onSameThread()
            .check()
    }

    private fun showSettingsDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setCancelable(false)
        builder.setPositiveButton("GOTO SETTINGS",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
                openSettings()
            })
        //        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
        builder.show()
    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    override fun onUserSuccessAPI(success: Response<UserResponse>) {
        pd.dismiss()
        if (success.isSuccessful)

        {
            if (success.body()?.isVerified==false)
            {
                startActivity(Intent(this@SplashActivity,LoginScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                finish()
            } else if (success.body()?.profile==null || success.body()?.profile!!.equals(null))
            {
                startActivity(Intent(this@SplashActivity,YourInfoScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                finish()
            } else if (success.body()?.interests==null || success.body()?.interests!!.equals(null))
            {
                startActivity(Intent(this@SplashActivity, ChooseInterestScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity,FeedScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                finish()
            }

        } else {

        }
    }

    override fun onError(error: String) {
        TODO("Not yet implemented")
    }

}