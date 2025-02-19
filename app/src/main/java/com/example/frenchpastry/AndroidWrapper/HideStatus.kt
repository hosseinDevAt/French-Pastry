package com.example.frenchpastry.AndroidWrapper

import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager

class HideStatus{

    fun setFullScreen(window: Window){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){

            val insetController = window.insetsController
            if (insetController != null){
                insetController.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                insetController.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        }else {

            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

        }

    }


}