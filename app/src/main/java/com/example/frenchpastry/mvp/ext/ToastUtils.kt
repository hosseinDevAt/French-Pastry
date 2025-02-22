package com.example.frenchpastry.mvp.ext

import android.content.Context
import android.widget.Toast

object ToastUtils {

    fun setNetToast(context: Context){
        toast(context, "ارتباط با سرور امکان پذیر نمیباشد.")
    }

    fun toast(context: Context,text:String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}