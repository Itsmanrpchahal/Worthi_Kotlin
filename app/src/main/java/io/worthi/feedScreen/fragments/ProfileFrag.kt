package io.worthi.feedScreen.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.worthi.R
import io.worthi.shareapp.ShareAppScreen


class ProfileFrag : Fragment() {

private lateinit var shareapp: Button


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
    }

    private fun init(view: View) {
        shareapp = view.findViewById(R.id.shareapp)
    }


}