package com.mobilife.poc.webview.ui.webview_button

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mobilife.poc.webview.Constants
import com.mobilife.poc.webview.R

class WebViewWithButtonFragment : Fragment() {

    private lateinit var dashboardViewModel: WebViewWithButtonViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(WebViewWithButtonViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_webview_button, container, false)

        val pageTitleTextView: TextView = root.findViewById(R.id.text_page_title)
        val contentWebView: WebView = root.findViewById(R.id.webview_content)
        val makePaymentButton: Button = root.findViewById(R.id.button_make_payment)

        dashboardViewModel.pageTitle.observe(viewLifecycleOwner, Observer {
            pageTitleTextView.text = it
        })

        contentWebView.settings.javaScriptEnabled = true
        contentWebView.webViewClient = WebViewClient()
        contentWebView.loadUrl(Constants.webWithButtonURL)


        val builder = AlertDialog.Builder(activity)
        //builder.setTitle("Title")
        builder.setMessage("Please finish registration process before making payment.")

        builder.setPositiveButton("OK"){dialogInterface, which ->
            Log.d("Action", "Make Payment POP-UP > OK button clicked")
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        //alertDialog.show()

        makePaymentButton.setOnClickListener {
            Log.d("Action", "Make Payment Button clicked")
            //Toast.makeText(activity, "Trying to make payment", Toast.LENGTH_LONG).show()
            //alertDialog.show()

            contentWebView.evaluateJavascript("(function(){return window.document.body.outerHTML})();", { contentString ->
                //Log.d("Action", "Content String = " + contentString)
                if(contentString.contains("make-payment-button", false) == true){
                    Log.d("Flow", "Go to Payment Page");
                    val navController = findNavController()
                    navController.navigate(R.id.navigation_payment)

                } else {
                    alertDialog.show()
                }

            })


        }


        return root
    }
}