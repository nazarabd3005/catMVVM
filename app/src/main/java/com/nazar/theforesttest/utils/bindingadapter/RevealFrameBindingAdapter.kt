package com.nazar.theforesttest.utils.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.jem.easyreveal.layouts.EasyRevealFrameLayout

@BindingAdapter("android:visibility")
fun setTriggerRevealFrameLayout(revealFrameLayout: EasyRevealFrameLayout,visibility : Int){
    if (visibility == View.GONE){
        revealFrameLayout.hide()
    }
}