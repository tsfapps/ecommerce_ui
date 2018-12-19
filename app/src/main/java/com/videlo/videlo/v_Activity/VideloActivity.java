package com.videlo.videlo.v_Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.videlo.videlo.R;

public class VideloActivity extends AppCompatActivity {

    private WebView webView;
    private String videloUrl;// = "https://www.videlo.com.my/";
    private TextView textViewNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videlo);

        videloUrl = getIntent().getExtras().getString("base_url");

        textViewNetwork = findViewById(R.id.tv_webViewUrl);
        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);


        if (isNetworkConnecting(VideloActivity.this)) {
            webView.setWebViewClient(new myWebViewClient());
            webView.loadUrl(videloUrl);
        } else {
            webView.setVisibility(View.GONE);
            textViewNetwork.setText("Check the network...");
            textViewNetwork.setVisibility(View.VISIBLE);
        }


    }

    private boolean isNetworkConnecting(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
            if (networkInfos != null)

                for (int i = 0; i < networkInfos.length; i++)
                    if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {

                        return true;
                    }
        }

        return false;

    }

    private class myWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String videloUrl) {

            view.loadUrl(videloUrl);

            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }
}
