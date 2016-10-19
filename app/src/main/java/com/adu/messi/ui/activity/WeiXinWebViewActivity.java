package com.adu.messi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.adu.messi.MyApplication;
import com.adu.messi.R;
import com.adu.messi.utils.LogUtils;
import com.adu.messi.utils.ShareUtil;

/**
 * Created by adu on 2016/10/17.
 */

public class WeiXinWebViewActivity extends BaseActivity {

    @BindView(R.id.pb) ProgressBar pb;
    @BindView(R.id.webview) WebView webview;
    private String url;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixinwebview);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        url = intent.getStringExtra("url");
        LogUtils.debug(title+"+++++++++++++++++++++++++++++++++++"+url);
        getSupportActionBar().setTitle(title);
        pb.setMax(100);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setWebChromeClient(new WebViewClient());
        webview.loadUrl(url);

        //本地显示
        webview.setWebViewClient(new android.webkit.WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });
    }
    /**
     * 接口回调
     */
    class WebViewClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            LogUtils.debug("+++++++++++++++++++++++++++++++++++");
            pb.setProgress(newProgress);
            if(newProgress == 100){
                pb.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_main,menu);
        return true;
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_share){
            ShareUtil.share(this,url);
        }
        return super.onOptionsItemSelected(item);
    }
}
