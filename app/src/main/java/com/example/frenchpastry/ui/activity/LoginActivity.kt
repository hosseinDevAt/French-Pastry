package com.example.frenchpastry.ui.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.frenchpastry.AndroidWrapper.HideStatus
import com.example.frenchpastry.R
import com.example.frenchpastry.mvp.model.ModelLoginActivity
import com.example.frenchpastry.mvp.presenter.PresenterLoginActivity
import com.example.frenchpastry.mvp.view.ViewLoginActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterLoginActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val view = ViewLoginActivity(this)
        presenter = PresenterLoginActivity(view, ModelLoginActivity(this))
        setContentView(view.binding.root)
        HideStatus()
        presenter.onCreate()

        onBack()
    }

    private fun onBack() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    finishAffinity()
                }
            }
        )
    }
}