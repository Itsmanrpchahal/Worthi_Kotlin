package io.worthi.welcomescreens

import android.Manifest
import android.os.Bundle
import android.text.Html
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.worthi.R
import io.worthi.welcomescreens.adapter.SliderAdapter


class MainActivity : AppCompatActivity() {

    var layout: RelativeLayout? = null
    var nextbt: Button? = null
    var viewPager: ViewPager? = null
    lateinit var tab_layout : TabLayout
    private val dotsLayout: LinearLayout? = null

    private val MULTIPLE_PERMISSIONS = 10
    var sliderAdapter: SliderAdapter? = null
    var currentPage = 0
    var mBackPressCount:Int = 0
    var permissions = arrayOf<String>(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_CONTACTS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        init()

        sliderAdapter =  SliderAdapter(this)
        viewPager?.setAdapter(sliderAdapter)
        viewPager?.addOnPageChangeListener(viewListener)
        tab_layout.setupWithViewPager(viewPager)
//        TabLayoutMediator(tab_layout, viewPager) { tab, position ->
//            //Some implementation
//        }.attach()
        //addBottomDots(0)

    }

    fun init()
    {
        viewPager =findViewById(R.id.viewPager);
        nextbt = findViewById(R.id.nextbt)
        tab_layout = findViewById(R.id.tab_layout)
    }

//    //add dots at bottom
//    private fun addBottomDots(currentPage: Int) {
//        dots = arrayOfNulls(4)
//        dotsLayout!!.removeAllViews()
//        for (i in 0 until dots.length) {
//            dots[i] = TextView(this)
//            dots[i].text = Html.fromHtml("&#8226;")
//            dots[i].setTextSize(50)
//            dots[i].setTextColor(resources.getColor(R.color.colortextwhite))
//            dotsLayout.addView(dots[i])
//        }
//        if (dots.length > 0) {
//            dots[currentPage].setTextColor(resources.getColor(R.color.colorTheme))
//        }
//    }

    var viewListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
        override fun onPageSelected(i: Int) {

        }

        override fun onPageScrollStateChanged(i: Int) {
            if (currentPage == 0) {
               // mSkip!!.text = "NEXT"
            }
            if (currentPage == 1) {
                //mSkip!!.text = "NEXT"
            }
            if (i == ViewPager.SCROLL_STATE_IDLE) {
                val curr = viewPager!!.currentItem
                val lastReal = viewPager!!.adapter!!.count - 1
                if (curr < lastReal) {
                    currentPage = 0
                } else if (curr == lastReal) {
                    currentPage++
                    if (currentPage == 3) {
                      //  startActivity(Intent(applicationContext, Join_us::class.java))

                        finish()
                    }
                    if (currentPage == 1) {
                        //mSkip!!.text = "Start"
                    }
                }

                nextbt?.setOnClickListener {

                }

            }


        }
    }

}