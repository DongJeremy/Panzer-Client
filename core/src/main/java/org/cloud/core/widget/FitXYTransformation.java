package org.cloud.core.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import org.cloud.core.base.BaseApplication;

import java.security.MessageDigest;

/**
 * FileName: MaskTransformation
 * Author: Admin
 * Date: 2020/11/13 9:44
 * Description: MaskTransformation
 */
public class FitXYTransformation extends BitmapTransformation {

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();
        int toWidth = BaseApplication.getInstance().getWidth();
        int toHeight = toWidth * height / width;

        Bitmap result = pool.get(toWidth, toHeight, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(toWidth, toHeight, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);

        setCanvasBitmapDensity(toTransform, result);
        canvas.drawBitmap(toTransform, 0, 0, paint);
        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

    void setCanvasBitmapDensity(@NonNull Bitmap toTransform, @NonNull Bitmap canvasBitmap) {
        canvasBitmap.setDensity(toTransform.getDensity());
    }
}
