package com.example.frenchpastry.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.frenchpastry.AndroidWrapper.ActivityUtils
import com.example.frenchpastry.AndroidWrapper.HideStatus
import com.example.frenchpastry.R
import com.example.frenchpastry.mvp.model.ModelListPastryActivity
import com.example.frenchpastry.mvp.presenter.PresenterListPastryActivity
import com.example.frenchpastry.mvp.view.ViewListPastryActivity

class ListPastryActivity : AppCompatActivity(), ActivityUtils {

    companion object {
        const val ID = "ID"
        const val TYPE = "TYPE"
        const val NEW = "NEW"
        const val SPECIAL_OFFER = "SPECIAL_OFFER"
        const val RATE = "RATE"
    }

    private lateinit var presenter : PresenterListPastryActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val view = ViewListPastryActivity(this, this)
        setContentView(view.binding.root)
        HideStatus().setFullScreen(window)
        val id = intent.getIntExtra(ID, 0)
        val type = intent.getStringExtra(TYPE) ?: ""
        val model = ModelListPastryActivity(id, type)

        presenter = PresenterListPastryActivity(view, model, this)
        presenter.onCreate()

    }

    override fun finished() {
        finish()
    }


}