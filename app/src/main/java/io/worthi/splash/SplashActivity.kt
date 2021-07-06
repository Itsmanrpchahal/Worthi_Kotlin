package io.worthi.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import io.worthi.welcomescreens.MainActivity
import io.worthi.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

       // methodRequiresPermission()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()

            finish()
        }, 3000)
    }


//    private fun methodRequiresPermission() = runWithPermissions(
//        android.Manifest.permission.READ_EXTERNAL_STORAGE,
//        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        android.Manifest.permission.CAMERA,
//        android.Manifest.permission.ACCESS_FINE_LOCATION
//    ) {
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(
//                Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            )
//            finish()
//
//            finish()
//        }, 3000)
//
//    }

}