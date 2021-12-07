package com.mobilife.poc.webview.ui.webview_deeplink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewWithDeepLinkViewModel : ViewModel() {

    private val _pageTitle = MutableLiveData<String>().apply {
        value = "WebView with Deep Link"
    }
    val pageTitle: LiveData<String> = _pageTitle
}