package com.ebills.alphamind.ebills;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by anmol on 16/3/18.
 */

public class ShowingBillActivity extends AppCompatActivity {

    private WebView webView;
    private String link;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.showing_bill_activity);
        if(getIntent().getExtras().containsKey("pdf"))
            link = getIntent().getExtras().getString("pdf");
        else
            link = getIntent().getExtras().getString("html");
        webView = findViewById(R.id.WebViewShowingBill);

        // Get html as string from server
//        String htmlAsString = " ";

        // Will Show the html string in webview
//        webView.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);
        webView.loadUrl(link);

    }
}
