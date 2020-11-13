package org.cloud.core.widget;

import android.content.Context;
import android.content.Intent;

/**
 * FileName: JavascriptInterface
 * Author: Admin
 * Date: 2020/11/13 13:41
 * Description: JavascriptInterface
 */
public class JavascriptInterface {

    private Context context;

    public JavascriptInterface(Context context) {
        this.context = context;
    }

    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        Intent intent = new Intent();
        intent.putExtra("img", img);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.setClass(context, ImageActivity.class);
        context.startActivity(intent);
    }
}
