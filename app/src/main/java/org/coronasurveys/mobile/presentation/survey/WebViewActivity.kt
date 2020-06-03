package org.coronasurveys.mobile.presentation.survey

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import org.coronasurveys.mobile.R
import org.coronasurveys.mobile.data.PreferencesRepo
import org.coronasurveys.mobile.databinding.ActivityWebviewBinding
import org.coronasurveys.mobile.utils.SurveyUtils
import org.coronasurveys.mobile.utils.configure
import org.coronasurveys.mobile.utils.getCountry
import org.coronasurveys.mobile.utils.getLanguage

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
        preferencesRepo = PreferencesRepo(this)
        setupActionBar()
        val binding = ActivityWebviewBinding.inflate(layoutInflater)
        myWebView = setupWebView(binding.surveyWebview, binding)
        setContentView(binding.root)
        myWebView.loadUrl(SurveyUtils.getSurveyUrl(getCountry(), getLanguage()))
    }

    private fun setupActionBar() {
        supportActionBar?.title = getString(R.string.survey)
        supportActionBar?.elevation = 4f
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupWebView(webView: WebView, binding: ActivityWebviewBinding): WebView {
        WebView.setWebContentsDebuggingEnabled(true)
        return webView
            .configure()
            .apply {
            webViewClient = CustomWebViewClient(binding)
            addJavascriptInterface(JavaScriptInterface(),
                HANDLER
            )
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