package org.cloud.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.cloud.core.R;

import java.io.ByteArrayOutputStream;

/**
* FileName: BaseImageLoader
* Author: Admin
* Date: 2020/11/14 8:40
* Description: 图片加载工具类
*/
public class ImageUtils {

    private static volatile ImageUtils instance;

    private Context context;
    private int radius;

    public static ImageUtils getInstance() {
        if (instance == null) {
            synchronized (ImageUtils.class) {
                if (instance == null) {
                    instance = new ImageUtils();
                }
            }
        }
        return instance;
    }

    public void init(Context context, int radius) {
        this.context = context;
        this.radius = radius;
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
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
                .placeholder(R.mipmap.goods_default_img)
                .into(imageView);
    }
}
