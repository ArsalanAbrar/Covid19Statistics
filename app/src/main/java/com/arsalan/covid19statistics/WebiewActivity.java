package com.arsalan.covid19statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebiewActivity extends AppCompatActivity {

    WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webiew);
        Intent i = getIntent();
        url = i.getStringExtra("url");
        webView = (WebView) findViewById(R.id.wbview);
        if (url.equalsIgnoreCase("who")) {
            webView.loadUrl("https://www.who.int/emergencies/diseases/novel-coronavirus-2019/technical-guidance/infection-prevention-and-control");
        }else if (url.equalsIgnoreCase("google")){
            webView.loadUrl("https://www.google.com/covid19/");
        }else if (url.equalsIgnoreCase("testing")){
            webView.loadUrl("https://www.investindia.gov.in/bip/resources/state-and-central-control-rooms");
        }
        }
}
