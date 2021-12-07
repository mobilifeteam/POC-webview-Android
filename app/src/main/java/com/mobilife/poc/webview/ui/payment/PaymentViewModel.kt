package com.mobilife.poc.webview.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaymentViewModel : ViewModel() {

    private val _pageTitle = MutableLiveData<String>().apply {
        value = "Payment Page"
    }

    private val _accountNameTitle = MutableLiveData<String>().apply {
        value = "Account Name"
    }
    private val _accountNameValue = MutableLiveData<String>().apply {
        value = "User's Account Name"
    }

    private val _accountNumberTitle = MutableLiveData<String>().apply {
        value = "Account Number"
    }
    private val _accountNumberValue = MutableLiveData<String>().apply {
        value = "00-xxxx-xxxx-00"
    }

    private val _targetAccountNameTitle = MutableLiveData<String>().apply {
        value = "Target Account Name"
    }
    private val _targetAccountNameValue = MutableLiveData<String>().apply {
        value = "Insurance Company Account"
    }

    private val _amountTitle = MutableLiveData<String>().apply {
        value = "Total Amount"
    }
    private val _amountValue = MutableLiveData<String>().apply {
        value = "10,000.00 THB"
    }

    val pageTitle: LiveData<String> = _pageTitle
    val accountNameTitle: LiveData<String> = _accountNameTitle
    val accountNameValue: LiveData<String> = _accountNameValue
    val accountNumberTitle: LiveData<String> = _accountNumberTitle
    val accountNumberValue: LiveData<String> = _accountNumberValue
    val targetAccountNameTitle: LiveData<String> = _targetAccountNameTitle
    val targetAccountNameValue: LiveData<String> = _targetAccountNameValue
    val amountTitle: LiveData<String> = _amountTitle
    val amountValue: LiveData<String> = _amountValue

}