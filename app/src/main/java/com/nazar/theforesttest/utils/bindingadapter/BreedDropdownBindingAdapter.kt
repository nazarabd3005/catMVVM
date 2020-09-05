package com.nazar.theforesttest.utils.bindingadapter

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import com.nazar.theforesttest.R
import com.nazar.theforesttest.core.model.Breed
import com.nazar.theforesttest.core.utils.Status
import com.nazar.theforesttest.ui.breedlist.listName


@BindingAdapter(value = ["status","bindAutoComplete"], requireAll = false)
fun breedData(autoCompleteTextView: AutoCompleteTextView, status: Status?, data: ArrayList<Breed>?) {
    if (data == null || status == null){
        return
    }
    autoCompleteTextView.setAdapter(
        ArrayAdapter(
            autoCompleteTextView.context,
            R.layout.dropdown_menu_popup_item,
            data.listName()
        )
    )
}