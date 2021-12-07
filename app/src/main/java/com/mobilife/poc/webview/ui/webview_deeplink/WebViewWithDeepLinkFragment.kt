package com.mobilife.poc.webview.ui.webview_deeplink

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.mobilife.poc.webview.Constants
import com.mobilife.poc.webview.MainActivity
import com.mobilife.poc.webview.R

class CustomWebViewClient : WebViewClient () {
    public lateinit var navController : NavController
    public var sharedPref : SharedPreferences? = null

    public override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val urlStr: String = request?.url.toString()
        Log.d("Processing", "URL = " + urlStr)
        if(urlStr.startsWith("webviewpoc://payment")) {
            navController.navigate(R.id.navigation_payment)

            // Parsing params from URL
            val urlObj : Uri  = Uri.parse(urlStr)

            val amountStr : String? = urlObj.getQueryParameter(Constants.amountParamName)
            Log.d("Processing", "From URL > amount = " + amountStr)

            val targetAccountStr : String? = urlObj.getQueryParameter(Constants.targetAccountParamName)
            Log.d("Processing", "From URL > target_account = " + targetAccountStr)

            if(sharedPref != null) {
                var sharedPrefEditor = sharedPref?.edit()
                if(sharedPrefEditor != null) {
                    sharedPrefEditor.putString(Constants.targetAccountNameKey, targetAccountStr)
                    sharedPrefEditor.putString(Constants.amountKey, amountStr)
                    sharedPrefEditor.apply()
                }
            }
            return true
        }
        return false
    }
}


class WebViewWithDeepLinkFragment : Fragment() {

    private lateinit var dashboardViewModel: WebViewWithDeepLinkViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(WebViewWithDeepLinkViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_webview_deeplink, container, false)

        val pageTitleTextView: TextView = root.findViewById(R.id.text_page_title)
        val contentWebView: WebView = root.findViewById(R.id.webview_content)

        dashboardViewModel.pageTitle.observe(viewLifecycleOwner, Observer {
            pageTitleTextView.text = it
        })

        contentWebView.settings.javaScriptEnabled = true
        //contentWebView.webViewClient = WebViewClient()
        val customWebViewClient: CustomWebViewClient = CustomWebViewClient()
        customWebViewClient.sharedPref = activity?.getSharedPreferences(Constants.deepLinkSharedPrefName, Context.MODE_PRIVATE)
        customWebViewClient.navController = findNavController()
        contentWebView.webViewClient = customWebViewClient
        contentWebView.loadUrl(Constants.webWithDeepLinkURL)

        return root
    }
}