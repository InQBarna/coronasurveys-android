package com.inqbarna.coronasurveys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView

    companion object {
        fun getCallingIntent(ctx : Context) : Intent {
            return Intent(ctx, WebViewActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myWebView = setupWebView()
        setContentView(myWebView)
        myWebView.loadUrl(BuildConfig.SURVEY_URL)
    }

    private fun setupWebView(): WebView {
        WebView.setWebContentsDebuggingEnabled(true)
        return WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.databaseEnabled = true
            settings.domStorageEnabled = true
        }
    }

}