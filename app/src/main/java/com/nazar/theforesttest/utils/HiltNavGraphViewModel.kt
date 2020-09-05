package com.nazar.theforesttest.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

inline fun <reified T : ViewModel> Fragment.hiltNavGraphViewModel(@IdRes navGraphIdRes: Int) =
    viewModels<T>(
        ownerProducer = { findNavController().getBackStackEntry(navGraphIdRes) },
        factoryProducer = { defaultViewModelProviderFactory }
    )