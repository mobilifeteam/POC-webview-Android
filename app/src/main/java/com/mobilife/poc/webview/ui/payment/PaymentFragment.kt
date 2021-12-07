package com.mobilife.poc.webview.ui.payment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobilife.poc.webview.Constants
import com.mobilife.poc.webview.R

class PaymentFragment : Fragment() {

    private lateinit var paymentViewModel: PaymentViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        paymentViewModel =
                ViewModelProvider(this).get(PaymentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_payment, container, false)

        val pageTitleTextView: TextView = root.findViewById(R.id.text_page_title)
        val accountNameTitleTextView: TextView = root.findViewById(R.id.text_account_name_title)
        val accountNameValueTextView: TextView = root.findViewById(R.id.text_account_name_value)
        val accountNumberTitleTextView: TextView = root.findViewById(R.id.text_account_number_title)
        val accountNumberValueTextView: TextView = root.findViewById(R.id.text_account_number_value)
        val targetAccountNameTitleTextView: TextView = root.findViewById(R.id.text_target_account_title)
        val targetAccountNameValueTextView: TextView = root.findViewById(R.id.text_target_account_value)
        val amountTitleTextView: TextView = root.findViewById(R.id.text_amount_title)
        val amountValueTextView: TextView = root.findViewById(R.id.text_amount_value)

        paymentViewModel.pageTitle.observe(viewLifecycleOwner, Observer {
            pageTitleTextView.text = it
        })
        paymentViewModel.accountNameTitle.observe(viewLifecycleOwner, Observer {
            accountNameTitleTextView.text = it
        })
        paymentViewModel.accountNameValue.observe(viewLifecycleOwner, Observer {
            accountNameValueTextView.text = it
        })
        paymentViewModel.accountNumberTitle.observe(viewLifecycleOwner, Observer {
            accountNumberTitleTextView.text = it
        })
        paymentViewModel.accountNumberValue.observe(viewLifecycleOwner, Observer {
            accountNumberValueTextView.text = it
        })
        paymentViewModel.targetAccountNameTitle.observe(viewLifecycleOwner, Observer {
            targetAccountNameTitleTextView.text = it
        })
        paymentViewModel.amountTitle.observe(viewLifecycleOwner, Observer {
            amountTitleTextView.text = it
        })



        Log.d("PaymentFragment", "Try to read from SharedPref")
        val sharedPref = activity?.getSharedPreferences(Constants.deepLinkSharedPrefName, Context.MODE_PRIVATE)
        Log.d("PaymentFragment", "Try to read from SharedPref > sharedPref = " + sharedPref)

        if(sharedPref != null) {
            val savedTargetAccountStr = sharedPref.getString(Constants.targetAccountNameKey, null)
            val savedAmountStr = sharedPref.getString(Constants.amountKey, null)

            Log.d("PaymentFragment", "Try to read from SharedPref > sharedPref.target_account = " +
                    savedTargetAccountStr + " / sharedPref.amount = " + savedAmountStr)

            if((savedTargetAccountStr != null) && (savedAmountStr != null)) {
                targetAccountNameValueTextView.text = savedTargetAccountStr
                amountValueTextView.text = savedAmountStr

                val sharedPrefEditor = sharedPref.edit()
                if (sharedPrefEditor != null) {
                    sharedPrefEditor.clear()
                }
            } else {
                paymentViewModel.targetAccountNameValue.observe(viewLifecycleOwner, Observer {
                    targetAccountNameValueTextView.text = it
                })

                paymentViewModel.amountValue.observe(viewLifecycleOwner, Observer {
                    amountValueTextView.text = it
                })
            }
        }


        return root
    }
}