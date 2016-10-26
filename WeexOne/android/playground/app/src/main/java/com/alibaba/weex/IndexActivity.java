package com.alibaba.weex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.weex.commons.AbsWeexActivity;
import com.alibaba.weex.commons.util.AppConfig;
import com.alibaba.weex.constants.Constants;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXRenderErrorCode;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.utils.WXSoInstallMgrSdk;

public class IndexActivity extends AbsWeexActivity {

    private static final String TAG = "IndexActivity";
    private static final String DEFAULT_IP = "your_current_IP";
    private static String sCurrentIp = DEFAULT_IP;//"127.0.0.1"; // your_current_IP

    private ProgressBar mProgressBar;
    private TextView mTipView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        setContainer((ViewGroup) findViewById(R.id.index_container));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = (ProgressBar) findViewById(R.id.index_progressBar);
        mTipView = (TextView) findViewById(R.id.index_tip);
        mProgressBar.setVisibility(View.VISIBLE);
        mTipView.setVisibility(View.VISIBLE);


        if (!WXSoInstallMgrSdk.isCPUSupport()) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mTipView.setText(R.string.cpu_not_support_tip);
            return;
        }

        loadUrl(isLocalPage() ? AppConfig.getLocalUrl() : AppConfig.getLaunchUrl());
    }

    @Override
    protected boolean isLocalPage() {
        // return TextUtils.equals(sCurrentIp, DEFAULT_IP);
        return AppConfig.isLaunchLocally();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(isLocalPage() ? R.menu.main_scan : R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void preRenderPage() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                createWeexInstance();
                renderPage();
                break;
            case R.id.action_scan:
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan a barcode");
                //integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(true);
                integrator.setOrientationLocked(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                //scanQrCode();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        mProgressBar.setVisibility(View.GONE);
        mTipView.setVisibility(View.GONE);
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        mProgressBar.setVisibility(View.GONE);
        mTipView.setVisibility(View.VISIBLE);
        if (TextUtils.equals(errCode, WXRenderErrorCode.WX_NETWORK_ERROR)) {
            mTipView.setText(R.string.index_tip);
        } else {
            mTipView.setText("render error:" + msg);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                handleDecodeInternally(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    // Put up our own UI for how to handle the decoded contents.
    private void handleDecodeInternally(String code) {

        if (!TextUtils.isEmpty(code)) {
            Uri uri = Uri.parse(code);
            if (uri.getQueryParameterNames().contains("bundle")) {
                WXEnvironment.sDynamicMode = uri.getBooleanQueryParameter("debug", false);
                WXEnvironment.sDynamicUrl = uri.getQueryParameter("bundle");
                String tip = WXEnvironment.sDynamicMode ? "Has switched to Dynamic Mode" : "Has switched to Normal Mode";
                Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
                finish();
                return;
            } else if (uri.getQueryParameterNames().contains("_wx_devtool")) {
                WXEnvironment.sRemoteDebugProxyUrl = uri.getQueryParameter("_wx_devtool");
                WXSDKEngine.reload();
                Toast.makeText(this, "devtool", Toast.LENGTH_SHORT).show();
                finish();
                return;
            } else if (code.contains("_wx_debug")) {
                uri = Uri.parse(code);
                String debug_url = uri.getQueryParameter("_wx_debug");
                WXSDKEngine.switchDebugModel(true, debug_url);
                finish();
            } else {
                Toast.makeText(this, code, Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(Constants.ACTION_OPEN_URL);
                intent.setPackage(getPackageName());
                intent.setData(Uri.parse(code));
                startActivity(intent);
            }
        }
    }
}

