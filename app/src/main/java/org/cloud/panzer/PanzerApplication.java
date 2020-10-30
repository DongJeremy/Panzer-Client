package org.cloud.panzer;

import android.app.Activity;
import android.content.Intent;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseConstant;
import org.cloud.panzer.activity.GoodsActivity;

public class PanzerApplication extends BaseApplication {
    public void startGoods(Activity activity, String goodsId) {

        Intent intent = new Intent(activity, GoodsActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, goodsId);
        start(activity, intent);

    }
}
