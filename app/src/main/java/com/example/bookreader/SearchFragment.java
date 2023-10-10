package com.example.bookreader;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private Button btn1;
    private Button btn2;
    private Button btn3;

    WebView webView;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        // setup web view
        View v = inflater.inflate(R.layout.fragment_search, container, false);;
        webView = (WebView) v.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.gclib.go.kr/m/");


        // set the button to load the website
        btn1 = (Button) v.findViewById(R.id.btn_nav1);
        btn2 = (Button) v.findViewById(R.id.btn_nav2);
        btn3 = (Button) v.findViewById(R.id.btn_nav3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.loadUrl("http://www.gclib.go.kr/m/");

                btn1.setTextSize(18);
                btn2.setTextSize(15);
                btn3.setTextSize(15);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.loadUrl("http://www.kwalib.kr/m/");

                btn1.setTextSize(15);
                btn2.setTextSize(20);
                btn3.setTextSize(15);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.loadUrl("http://www.gcja.hs.kr/");

                btn1.setTextSize(15);
                btn2.setTextSize(15);
                btn3.setTextSize(20);

            }
        });

        webView.canGoBack();
        webView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
                return false;
            }
        });


        return v;

    }

}
