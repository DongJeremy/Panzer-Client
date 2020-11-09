package org.cloud.panzer.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.cloud.panzer.App;

/**
 * FileName: WXPayEntryActivity
 * Author: Admin
 * Date: 2020/11/9 14:42
 * Description: WXPayEntryActivity
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    public void onReq(BaseReq baseReq) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        App.getInstance().getIwxapi().handleIntent(getIntent(), this);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        App.getInstance().getIwxapi().handleIntent(intent, this);
    }

    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == 5) {
            int i = baseResp.errCode;
            if (i == -2) {
                App.getInstance().setWxPaySuccess(false);
            } else if (i == -1) {
                App.getInstance().setWxPaySuccess(false);
            } else if (i == 0) {
                App.getInstance().setWxPaySuccess(true);
            }
            finish();
        }
    }
}
