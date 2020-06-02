package com.coronasurveys.mobile.presentation.survey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.coronasurveys.mobile.data.PreferencesRepo
import com.coronasurveys.mobile.databinding.ActivityWebviewBinding
import com.coronasurveys.mobile.utils.SurveyUtils
import com.coronasurveys.mobile.utils.configure
import com.coronasurveys.mobile.utils.getCountry
import com.coronasurveys.mobile.utils.getLanguage

class WebViewActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView
    lateinit var preferencesRepo: PreferencesRepo

    companion object {
        fun getCallingIntent(ctx : Context) : Intent {
            return Intent(ctx, WebViewActivity::class.java)
        }
        const val HANDLER = "AndroidHandler"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        preferencesRepo = PreferencesRepo(this)
        val binding = ActivityWebviewBinding.inflate(layoutInflater)
        myWebView = setupWebView(binding.surveyWebview, binding)
        setContentView(binding.root)
        myWebView.loadUrl(SurveyUtils.getSurveyUrl(getCountry(), getLanguage()))
    }

    private fun setupWebView(webView: WebView, binding: ActivityWebviewBinding): WebView {
        WebView.setWebContentsDebuggingEnabled(true)
        return webView
            .configure()
            .apply {
            webViewClient = CustomWebViewClient(binding)
            addJavascriptInterface(JavaScriptInterface(), HANDLER)
        }
    }


    private inner class JavaScriptInterface {
        @JavascriptInterface
        fun callback() {
            if (!preferencesRepo.isReminderSet())
                showDialog()
        }
    }

    private fun showDialog() {
        val dialog = ReminderDialog()
        dialog.show(supportFragmentManager, ReminderDialog.TAG)
    }

}