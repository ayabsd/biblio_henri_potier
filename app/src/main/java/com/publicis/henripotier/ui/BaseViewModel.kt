package com.publicis.henripotier.ui

/**
 * Created by Aya Boussaadia on 06,September,2020
 */

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val empty = MutableLiveData<Boolean>().apply { value = false }
    val dataLoading = MutableLiveData<Boolean>().apply { value = false }
    val toastMessage = MutableLiveData<String>()
}