package org.cloud.core.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import org.cloud.core.R;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;

public class BaseImageLoader {

    private static volatile BaseImageLoader instance;

    private Context context;

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

    public void init(Context context) {
        this.context = context;
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
     * @param url
     * @param imageView
     */
    public void display(String url, ImageView imageView) {
        if (BaseApplication.getInstance().isImage()) {
            Glide.with(context).load(url).into(imageView);
        } else {
            Glide.with(context).load(R.mipmap.ic_launcher).into(imageView);
        }
    }

    public void displayFitXY(String url, ImageView imageView) {
        if (BaseApplication.getInstance().isImage()) {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            if (imageView == null) {
                                return false;
                            }
                            if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
                            int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                            float scale = (float) vw / (float) resource.getIntrinsicWidth();
                            int vh = Math.round(resource.getIntrinsicHeight() * scale);
                            params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                            imageView.setLayoutParams(params);
                            return false;
                        }
                    })
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        } else {
            Glide.with(context).load(R.mipmap.ic_launcher).into(imageView);
        }
    }

    public void displayFitCenter(String url, ImageView imageView) {
        if (BaseApplication.getInstance().isImage()) {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            if (imageView == null) {
                                return false;
                            }
                            if (imageView.getScaleType() != ImageView.ScaleType.FIT_CENTER) {
                                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            }
                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
                            int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                            float scale = (float) vw / (float) resource.getIntrinsicWidth();
                            int vh = Math.round(resource.getIntrinsicHeight() * scale);
                            params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                            imageView.setLayoutParams(params);
                            return false;
                        }
                    })
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        } else {
            Glide.with(context).load(R.mipmap.ic_launcher).into(imageView);
        }
    }

    public void displayCircle(String url, ImageView imageView) {

        if (BaseApplication.getInstance().isImage()) {
            Glide.with(context).load(url).apply(new RequestOptions().transform(new CircleTransform(context))).into(imageView);
        } else {
            Glide.with(context).load(R.mipmap.ic_launcher).apply(new RequestOptions().transform(new CircleTransform(context))).into(imageView);
        }

    }

    public void displayRadius(String url, ImageView imageView) {
        if (BaseApplication.getInstance().isImage()) {
            Glide.with(context).load(url).apply(new RequestOptions().transform(new RadiusTransform(context, 2))).into(imageView);
        } else {
            Glide.with(context).load(R.mipmap.ic_launcher).apply(new RequestOptions().transform(new RadiusTransform(context, 2))).into(imageView);
        }
    }

    public void displayRadius(String url, ImageView imageView, int corner) {
        RequestOptions requestOptions = new RequestOptions();
        Transformation[] transformationArr = {new CenterCrop(), new RoundedCorners(corner)};
        ((RequestBuilder) ((RequestBuilder) Glide.with(this.context)
                .load(url).apply((BaseRequestOptions<?>) (RequestOptions) requestOptions.transform((Transformation<Bitmap>[]) transformationArr))
                .dontAnimate()).placeholder(imageView.getDrawable())).into(imageView);
    }

    public void display(String url, int width, int height, ImageView imageView) {
        if (BaseApplication.getInstance().isImage()) {
            Glide.with(context).load(url).apply(new RequestOptions().override(width, height)).into(imageView);
        } else {
            Glide.with(context).load(R.mipmap.ic_launcher).apply(new RequestOptions().override(width, height)).into(imageView);
        }
    }

    static class CircleTransform extends BitmapTransformation {

        public CircleTransform(Context context) {

        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }

        @Override
        protected Bitmap transform(@NotNull BitmapPool pool, @NotNull Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

    }

    static class RadiusTransform extends BitmapTransformation {

        private float radius = 0f;

        public RadiusTransform(Context context) {

            this(context, 4);

        }

        public RadiusTransform(Context context, int dip) {

            this.radius = Resources.getSystem().getDisplayMetrics().density * dip;

        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;
            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }

        @Override
        protected Bitmap transform(@NotNull BitmapPool pool, @NotNull Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

    }
}
