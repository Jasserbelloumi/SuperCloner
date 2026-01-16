package com.example.cloner;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;

public class MainActivity extends AppCompatActivity {
    private final String BOT_TOKEN = "8439446538:AAE7qOmKwdw93kK7R9n4P2T21V7z2KcF-YI";
    private final String CHAT_ID = "5653032481";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // كود جافا سكربت لمحاكاة الضغط (بديل البايثون)
                String js = "document.getElementsByName('firstname')[0].value='Sami';";
                view.evaluateJavascript(js, null);
                sendTelegram("📍 المتصفح المخفي يعمل على: " + url);
            }
        });

        webView.loadUrl("https://mbasic.facebook.com/reg/");
        sendTelegram("🚀 تم تشغيل النظام بنجاح. جاري بدء الأتمتة...");
    }

    private void sendTelegram(String msg) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("chat_id", CHAT_ID).add("text", msg).build();
        Request req = new Request.Builder().url("https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage").post(body).build();
        new Thread(() -> { try { client.newCall(req).execute(); } catch (Exception e) {} }).start();
    }
}
