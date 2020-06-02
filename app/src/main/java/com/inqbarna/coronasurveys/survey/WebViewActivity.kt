package com.inqbarna.coronasurveys.survey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.inqbarna.coronasurveys.utils.SurveyUtils
import com.inqbarna.coronasurveys.utils.configure
import com.inqbarna.coronasurveys.utils.getCountry
import com.inqbarna.coronasurveys.utils.getLanguage

class WebViewActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView

    companion object {
        fun getCallingIntent(ctx : Context) : Intent {
            return Intent(ctx, WebViewActivity::class.java)
        }
        const val HANDLER = "handler"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myWebView = setupWebView()
        setContentView(myWebView)
        myWebView.loadUrl(SurveyUtils.getSurveyUrl(getCountry(), getLanguage()))
    }

    private fun setupWebView(): WebView {
        WebView.setWebContentsDebuggingEnabled(true)
        return WebView(this)
            .configure()
            .apply {
            webViewClient = CustomWebViewClient()
            addJavascriptInterface(JavaScriptInterface(), HANDLER)
        }
    }


    private inner class JavaScriptInterface {
        @JavascriptInterface
        fun callback() {
            val dialog = ReminderDialog()
            dialog.show(supportFragmentManager, ReminderDialog.TAG)
        }
    }

}