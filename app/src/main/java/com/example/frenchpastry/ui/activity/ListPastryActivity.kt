package com.example.frenchpastry.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.frenchpastry.R

class ListPastryActivity : AppCompatActivity() {

    companion object {
        const val ID = "ID"
        const val TYPE = "TYPE"
        const val NEW = "NEW"
        const val SPECIAL_OFFER = "SPECIAL_OFFER"
        const val RATE = "RATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_pastry)

    }
}