package com.mobilife.poc.webview.ui.webview_button

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewWithButtonViewModel : ViewModel() {

    private val _pageTitle = MutableLiveData<String>().apply {
        value = "WebView with Button"
    }
    val pageTitle: LiveData<String> = _pageTitle
}