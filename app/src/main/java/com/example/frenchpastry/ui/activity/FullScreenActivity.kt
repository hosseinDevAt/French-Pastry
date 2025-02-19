package com.example.frenchpastry.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.frenchpastry.AndroidWrapper.HideStatus
import com.example.frenchpastry.data.local.pref.SecureSharedPref
import com.example.frenchpastry.data.local.pref.SharedPrefKeys
import com.example.frenchpastry.databinding.ActivityFullScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FullScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        HideStatus().setFullScreen(window)

        lifecycleScope.launch {

            delay(1300)

            val isLogin = SecureSharedPref.getSharedPref(this@FullScreenActivity)
                .getBoolean(SharedPrefKeys.LOGIN_STATE_KEY, false)

            Intent(
                this@FullScreenActivity,
                if (isLogin) MainActivity::class.java else LoginActivity::class.java
            ).apply {
                startActivity(this)
                finish()
            }

        }

    }
}