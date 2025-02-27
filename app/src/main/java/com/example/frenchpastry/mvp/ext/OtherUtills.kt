package com.example.frenchpastry.mvp.ext

import android.icu.text.DecimalFormat

class OtherUtills {

    fun changePrice(price: Int) : String {

        return DecimalFormat("#,###").format(price)

    }

}