package org.cloud.core.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileName: BaseFileClient
 * Author: Admin
 * Date: 2020/11/14 14:29
 * Description:
 */
public class BaseFileClient {
    private static volatile BaseFileClient instance;
    private String packName;

    public static BaseFileClient getInstance() {
        if (instance == null) {
            synchronized (BaseFileClient.class) {
                if (instance == null) {
                    instance = new BaseFileClient();
                }
            }
        }
        return instance;
    }

    public void init(String str) {
        this.packName = str;
        createCachePath();
        createImagePath();
        createDownPath();
    }

    public boolean hasSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public String getRootPath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        }
        return Environment.getDataDirectory().getAbsolutePath() + "/data/";
    }

    public String getDataPath() {
        return getRootPath() + "/Android/data/" + this.packName + "/";
    }

    public String getDownPath() {
        return getDataPath() + "down/";
    }

    public String getCachePath() {
        return getDataPath() + "cache/";
    }

    public String getImagePath() {
        return getDataPath() + "image/";
    }

    public void createImagePath() {
        File file = new File(getImagePath());
        if (!file.exists()) {
            Log.e("TAG", "create images path");
            file.mkdirs();
        }
    }

    public void createCachePath() {
        File file = new File(getCachePath());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void createDownPath() {
        File file = new File(getDownPath());
        if (!file.exists()) {
            Log.e("TAG", "create Down path" + getDownPath());
            file.mkdirs();
        }
    }

    public String getSuffix(String str) {
        return str.contains(".") ? str.substring(str.lastIndexOf(".") + 1, str.length()) : "";
    }

    public File createImage(String str, Bitmap bitmap) {
        try {
            File file = new File(getImagePath() + str + ".jpg");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public File saveImage2DCIM(Activity activity, String str, Bitmap bitmap) {
        try {
            File file = new File(getRootPath() + "DCIM/" + str + ".jpg");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file));
            activity.sendBroadcast(intent);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
