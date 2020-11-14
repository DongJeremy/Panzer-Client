package org.cloud.core.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * FileName: Utils
 * Author: Admin
 * Date: 2020/11/14 10:30
 * Description: Utils初始化相关
 */
public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    public static int getColors(int id) {
        return ContextCompat.getColor(context, id);
    }

    public static Drawable getMipmap(int id) {
        return ContextCompat.getDrawable(context, id);
    }

    public static Drawable getMipmap(int id, int color) {
        Drawable mDrawable = getMipmap(id);
        mDrawable = DrawableCompat.wrap(mDrawable);
        DrawableCompat.setTint(mDrawable, ContextCompat.getColor(context, color));
        return mDrawable;
    }

    public static Drawable getMipmap(Drawable drawable, int color) {
        Drawable mDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(mDrawable, getColors(color));
        return mDrawable;
    }
}
