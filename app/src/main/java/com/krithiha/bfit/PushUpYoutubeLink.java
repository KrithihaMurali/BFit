package com.krithiha.bfit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * Created by Krithiha on 3/29/2018.
 */

public class PushUpYoutubeLink extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pushupyoutubelink);

        WebView webView=(WebView)findViewById(R.id.push_youtube);
        webView.loadUrl("https://www.youtube.com/watch?v=5eSM88TFzAs");
    }
}
