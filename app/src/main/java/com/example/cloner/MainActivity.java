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
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Mobile Safari/537.36");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // كود الأتمتة: تعبئة الاسم، اللقب، وتوليد كلمة سر عشوائية
                String js = "(function() {" +
                    "var first = document.querySelector('input[name=\"firstname\"]');" +
                    "if(first) { " +
                    "   first.value = 'Sami';" +
                    "   document.querySelector('input[name=\"lastname\"]').value = 'Bakir';" +
                    "   document.querySelector('input[name=\"reg_email__\"]').value = 'jasser' + Math.floor(Math.random() * 10000) + '@telegmail.com';" +
                    "   document.querySelector('input[name=\"reg_passwd__\"]').value = 'P@ss' + Math.floor(Math.random() * 999) + 'Smart';" +
                    "   console.log('Fields Filled');" +
                    "}" +
                    "})();";
                
                view.evaluateJavascript(js, null);
                sendTelegram("⚙️ جاري تنفيذ الأتمتة على الرابط: " + url);
            }
        });

        // البدء بصفحة تسجيل الفيسبوك الأساسية
        webView.loadUrl("https://m.facebook.com/reg/");
        sendTelegram("🚀 بدأت عملية إنشاء الحساب التلقائية...");
    }

    private void sendTelegram(String msg) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("chat_id", CHAT_ID).add("text", msg).build();
        Request req = new Request.Builder().url("https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage").post(body).build();
        new Thread(() -> { try { client.newCall(req).execute(); } catch (Exception e) {} }).start();
    }
}
