package com.qtt.jinrong.ui.activity.web;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qtt.jinrong.R;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * H5 webview
 * Created by yanxin on 2015/6/23.
 */
@EActivity(R.layout.activity_web)
public class WebViewActivity extends BaseActivity {
	
	public static final String PARAM_URL = "PARAM_URL";
	public static final String PARAM_TITLE = "PARAM_TITLE";
	public static final String PARAM_SHOW_TOOLBAR = "PARAM_SHOW_TOOLBAR";

    @ViewById(R.id.titleBar)
	CommonTitleBar mTitleBar;
    @ViewById(R.id.webview)
	WebView mWebView;
    @ViewById(R.id.wap_toolbar)
	View mWapToolBar;
    @ViewById(R.id.loading)
    View mLoading;


	private String mUrl;
	private String mTitle;
	private boolean mShowToolbar;


	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		initData();
	}

	@Override
	public void onDestroy() {
		if(mWebView != null) {
			mWebView.onPause();
			mWebView.destroy();
		}
		super.onDestroy();
	}

	private void initData() {

		Bundle bundle = getIntent().getExtras();
		mUrl = bundle.getString(PARAM_URL);
		mTitle = bundle.getString(PARAM_TITLE);
		mShowToolbar = bundle.getBoolean(PARAM_SHOW_TOOLBAR, false);

	}

    @AfterViews
	void initViews() {

		mTitleBar.setTitle(TextUtils.isEmpty(mTitle)? "":mTitle);
		mTitleBar.setActivity(this);

        if (mShowToolbar) {
            mWapToolBar.setVisibility(View.VISIBLE);
        } else {
            mWapToolBar.setVisibility(View.GONE);
        }
		
		mWebView.clearCache(true);
		final WebSettings webSettings = mWebView.getSettings();
		webSettings.setBlockNetworkImage(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		
		//chrom 调试webview
		/*if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {  
			mWebView.setWebContentsDebuggingEnabled(true);  
		} */
		
		mWebView.setWebChromeClient(new WebChromeClient() {
        	
			public void onProgressChanged(WebView view, int newProgress) {
			}
		});
		
        mWebView.setWebViewClient(new WebViewClient() {
        	
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon) {
        		super.onPageStarted(view, url,favicon);
        		//titleBar.setRightProgressVisible(View.VISIBLE);
        	}
        	
        	@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
                mLoading.setVisibility(View.GONE);
			}
        	
        	@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				//*/ 解决webview加载锚点失效问题
				if(failingUrl.contains("#")) {
					String[] temp;
					temp = failingUrl.split("#");
					view.loadUrl(temp[0]); // load page without internal
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					view.goBack();
					view.goBack();
				}
			}
		});
        
		mWebView.loadUrl(mUrl);
	}
	
	public void showToolBar() {
		if (mWapToolBar != null) mWapToolBar.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(paramInt, paramKeyEvent);
	}

    @Click(R.id.wap_back)
    void wap_back() {
        if (mWebView.canGoBack()) mWebView.goBack();
    }

    @Click(R.id.wap_forward)
    void wap_forwardk() {
        if (mWebView.canGoForward()) mWebView.goForward();
    }

    @Click(R.id.wap_refresh)
    void wap_refresh() {
        mWebView.loadUrl(mUrl);
    }

}