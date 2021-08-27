package com.nastechdesign.maishapaycheckout;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class checkout {
   @SuppressLint("SetJavaScriptEnabled")
   public static void MaishaPay(Context mContext) {
       Dialog dialog = new Dialog(mContext, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
       WebView mWebView = dialog.findViewById(R.id.webView);
       WebSettings webSettings = mWebView.getSettings();
       webSettings.setJavaScriptEnabled(true);
       mWebView.loadUrl("assets/checkout.html");

       dialog.setCancelable(false);
       dialog.show();

   }
}