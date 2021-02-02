package com.qsous.webviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWebView = webView
        supportActionBar?.hide()
        supportActionBar?.title = intent.getStringExtra("name") ?: ""

        webView.settings.javaScriptEnabled = true
//        webView.settings.domStorageEnabled = true
//        webView.settings.mediaPlaybackRequiresUserGesture = false
//        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
//        webView.settings.setSupportMultipleWindows(false)
//        webView.settings.javaScriptCanOpenWindowsAutomatically = false
//        webView.settings.loadWithOverviewMode = true
//        webView.settings.useWideViewPort = true
//        webView.settings.loadsImagesAutomatically = true
        webView.settings.setUseWideViewPort(false)
        webView.settings.setDomStorageEnabled(true)
        webView.settings.setSaveFormData(true)
        webView.settings.setLoadWithOverviewMode(true)

        webView.webChromeClient = object : WebChromeClient() {

            override fun onConsoleMessage(message: String, lineNumber: Int, sourceID: String) {
                Log.d("MyApplication", "$message -- From line $lineNumber of $sourceID")
            }
        }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url)
                return false // then it is not handled by default action
            }

        }
        if (savedInstanceState == null) {
            webView.loadUrl("https://www.phsp.net/videos_d/s_riverdale_s5/e02.mp4/index.m3u8")
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mWebView?.saveState(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        mWebView?.restoreState(savedInstanceState)
    }
}