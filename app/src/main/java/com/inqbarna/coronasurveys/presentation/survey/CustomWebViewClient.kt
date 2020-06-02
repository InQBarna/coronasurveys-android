package com.inqbarna.coronasurveys.presentation.survey

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.inqbarna.coronasurveys.databinding.ActivityWebviewBinding

class CustomWebViewClient (private val binding: ActivityWebviewBinding): WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        binding.surveyLoading.visibility = View.GONE
    }
}