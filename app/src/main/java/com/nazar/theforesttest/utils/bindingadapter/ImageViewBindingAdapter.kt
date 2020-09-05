package com.nazar.theforesttest.utils.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.nazar.theforesttest.R

@BindingAdapter("src")
fun setImage(imageView: ImageView, url:String?){
    if (url == null)
        return
    imageView.load(url){
        placeholder(R.drawable.placeholder)
    }
}