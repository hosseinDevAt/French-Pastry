package com.example.frenchpastry.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.frenchpastry.AndroidWrapper.ActivityUtils
import com.example.frenchpastry.AndroidWrapper.HideStatus
import com.example.frenchpastry.R
import com.example.frenchpastry.mvp.model.ModelMainActivity
import com.example.frenchpastry.mvp.presenter.PresenterMainActivity
import com.example.frenchpastry.mvp.view.ViewMainActivity

class MainActivity : AppCompatActivity(), ActivityUtils {
    private lateinit var presenter : PresenterMainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val view = ViewMainActivity(this, this)
        presenter = PresenterMainActivity(view, ModelMainActivity())
        setContentView(view.binding.root)

        presenter.onCreate()
        HideStatus().setFullScreen(window)
    }

    override fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrameLayout, fragment)
            .commit()
    }

}