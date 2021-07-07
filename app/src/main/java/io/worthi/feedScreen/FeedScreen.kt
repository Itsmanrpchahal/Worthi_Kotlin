package io.worthi.feedScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.worthi.R
import io.worthi.feedScreen.fragments.FeedFrag
import io.worthi.feedScreen.fragments.InteractionFrag
import io.worthi.feedScreen.fragments.profile.ProfileFrag
import io.worthi.feedScreen.fragments.RewardsFrag

class FeedScreen : AppCompatActivity() {

    private lateinit var container: FrameLayout
    private lateinit var bottomnavigation: BottomNavigationView
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_screen)

        init()
        listeners()
    }

    private fun listeners() {
        bottomnavigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.feed -> {
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.container,FeedFrag())
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.interaction -> {
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.container,InteractionFrag())
                    //transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.rewards -> {
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.container,RewardsFrag())
                    //transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.profile -> {
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.container, ProfileFrag())
                    //transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun init()
    {
        fragmentManager = supportFragmentManager
        container = findViewById(R.id.container)
        bottomnavigation = findViewById(R.id.bottomnavigation)

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container,FeedFrag())
        transaction.commit()
    }
}