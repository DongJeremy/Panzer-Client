package org.cloud.core.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.ImageView;

import androidx.exifinterface.media.ExifInterface;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.cloud.core.R;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BaseImageLoader {

    private static volatile BaseImageLoader instance;

    private Context context;
    private int radius;

    public static BaseImageLoader getInstance() {
        if (instance == null) {
            synchronized (BaseImageLoader.class) {
                if (instance == null) {
                    instance = new BaseImageLoader();
                }
            }
        }
        return instance;
    }

    public void init(Context context, int radius) {
        this.context = context;
        this.radius = radius;
    }

    public Bitmap getSmall(String path) {

        int degree = 0;
        ExifInterface exif = null;

        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > 800 || width > 480) {
            int heightRatio = Math.round((float) height / (float) 800);
            int widthRatio = Math.round((float) width / (float) 480);
            inSampleSize = Math.min(heightRatio, widthRatio);
        }

        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

    }

    public Bitmap getLocal(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在imageView里面显示图片
     *
     * @param i         resourceId
     * @param imageView image控件
     */
    public void display(int i, ImageView imageView) {
        Glide.with(this.context)
                .load(i)
                .apply(new RequestOptions())
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView里面显示图片
     *
     * @param url       路径
     * @param imageView image控件
     */
    public void display(String url, ImageView imageView) {
        Glide.with(this.context)
                .load(url)
                .apply(new RequestOptions())
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView里面显示图片
     *
     * @param bitmap    位图
     * @param imageView image控件
     */
    public void display(Bitmap bitmap, ImageView imageView) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        Glide.with(this.context)
                .load(byteArrayOutputStream.toByteArray())
                .dontAnimate()
                .apply(new RequestOptions())
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView里面显示图片
     *
     * @param i         resourceId
     * @param width     宽度
     * @param height    高度
     * @param imageView image控件
     */
    public void display(int i, int width, int height, ImageView imageView) {
        Glide.with(this.context)
                .load(i)
                .apply(new RequestOptions().override(width, height))
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView里面显示图片
     *
     * @param str       路径
     * @param width     宽度
     * @param height    高度
     * @param imageView image控件
     */
    public void display(String str, int width, int height, ImageView imageView) {
        Glide.with(this.context)
                .load(str)
                .apply(new RequestOptions().override(width, height))
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView里面显示图片
     *
     * @param bitmap    位图
     * @param width     宽度
     * @param height    高度
     * @param imageView image控件
     */
    public void display(Bitmap bitmap, int width, int height, ImageView imageView) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        Glide.with(this.context)
                .load(byteArrayOutputStream.toByteArray())
                .apply(new RequestOptions().override(width, height))
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView里面显示圆形图片
     *
     * @param i         resourceId
     * @param imageView image控件
     */
    public void displayCircle(int i, ImageView imageView) {
        Glide.with(this.context)
                .load(i)
                .apply(RequestOptions.circleCropTransform())
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView里面显示圆形图片
     *
     * @param str       路径
     * @param imageView image控件
     */
    public void displayCircle(String str, ImageView imageView) {
        Glide.with(this.context)
                .load(str)
                .apply(RequestOptions.circleCropTransform())
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView里面显示圆形图片
     *
     * @param bitmap    位图
     * @param imageView image控件
     */
    public void displayCircle(Bitmap bitmap, ImageView imageView) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        Glide.with(this.context)
                .load(byteArrayOutputStream.toByteArray())
                .apply(RequestOptions.circleCropTransform())
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView中显示圆角图片
     *
     * @param i         resourceId
     * @param imageView image控件
     */
    public void displayRadius(int i, ImageView imageView) {
        Glide.with(this.context)
                .load(i)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(this.radius)))
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView中显示圆角图片
     *
     * @param i         resourceId
     * @param imageView image控件
     * @param radius    圆角
     */
    public void displayRadius(int i, ImageView imageView, int radius) {
        Glide.with(this.context)
                .load(i)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(radius)))
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView中显示圆角图片
     *
     * @param url       路径
     * @param imageView image控件
     * @param radius    圆角
     */
    public void displayRadius(String url, ImageView imageView, int radius) {
        Glide.with(this.context)
                .load(url)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(radius)))
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView中显示圆角图片
     *
     * @param url       路径
     * @param imageView image控件
     */
    public void displayRadius(String url, ImageView imageView) {
        Glide.with(this.context)
                .load(url)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(this.radius)))
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }

    /**
     * 在imageView中显示圆角图片
     *
     * @param bitmap    位图
     * @param imageView image控件
     */
    public void displayRadius(Bitmap bitmap, ImageView imageView) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        Glide.with(this.context)
                .load(byteArrayOutputStream.toByteArray())
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(this.radius)))
                .dontAnimate()
                .placeholder(R.mipmap.empty)
                .into(imageView);
    }
}
