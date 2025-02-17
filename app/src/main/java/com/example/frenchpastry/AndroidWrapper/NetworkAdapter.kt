package com.example.frenchpastry.AndroidWrapper

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.frenchpastry.databinding.CustomDialogNetworkBinding

object NetworkAdapter {

    fun isNetworkState(context: Context, activityUtils: ActivityUtils){

         if (netInfo(context)){
            activityUtils.activeNetwork(true)
        }else{
            showDialog(context, activityUtils)
             activityUtils.activeNetwork(false)
        }

    }

    private fun internetRefresh(context: Context, activityUtils: ActivityUtils) {

         if (netInfo(context)) {
            activityUtils.activeNetwork(true)
        } else {
            showDialog(context, activityUtils)
            activityUtils.activeNetwork(false)
        }
    }

    private fun netInfo(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNet = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            actNet.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNet.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNet.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }

    }


    private fun showDialog(context: Context, activityUtils: ActivityUtils){

        val view = CustomDialogNetworkBinding.inflate(LayoutInflater.from(context))
        val dialog = Dialog(context)
        dialog.setContentView(view.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        view.buttonRetry.setOnClickListener {
            internetRefresh(context, activityUtils)
        }

        view.buttonExit.setOnClickListener {
            (context as? Activity)?.finishAffinity()
        }


    }


}