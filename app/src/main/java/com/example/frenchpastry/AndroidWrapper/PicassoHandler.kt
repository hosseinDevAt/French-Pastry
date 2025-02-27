package com.example.frenchpastry.AndroidWrapper

import android.widget.ImageView
import com.example.frenchpastry.R
import com.squareup.picasso.Picasso

object PicassoHandler {

    fun setImage(img: ImageView, url: String){

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.progress_animated)
            .error(R.drawable.img_place_holder)
            .fit()
            .into(img)

    }

    fun setBanner(img: ImageView, url: String){

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.img_banner_place_holder)
            .error(R.drawable.img_banner_place_holder)
            .fit()
            .into(img)

    }

    fun setCatsImage(img: ImageView, url: String){

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.progress_animated)
            .error(R.drawable.ic_pastry_place_holder)
            .fit()
            .into(img)

    }
}