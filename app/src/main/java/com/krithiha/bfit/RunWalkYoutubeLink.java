package com.krithiha.bfit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * Created by Krithiha on 3/21/2018.
 */

public class RunWalkYoutubeLink extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runwalkyoutubelink);

        WebView webView=(WebView)findViewById(R.id.run_walk_youtube);
        webView.loadUrl("https://www.youtube.com/watch?v=1Arc1_4l2_0");
    }
}
