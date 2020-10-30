package org.cloud.core.base;

import android.content.SharedPreferences;

public class BaseShared {

    private static BaseShared instance;

    private SharedPreferences sharedPreferences;

    public static BaseShared getInstance() {
        if (instance == null) {
            synchronized (BaseImageLoader.class) {
                if (instance == null) {
                    instance = new BaseShared();
                }
            }
        }

        return instance;
    }

    public void init(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * 读取boolean标识从sharedPreferences中
     *
     * @param key   存储节点名称
     * @param value 没有此节点的默认值
     * @return 默认值或者此节点读取到的结果
     */
    public boolean getBoolean(String key, boolean value) {
        return sharedPreferences.getBoolean(key, value);
    }

    /**
     * 从sharedPreferences中读取String标识
     *
     * @param key 存储节点名称
     * @return 返回默认值或者此节点读取到的结果
     */
    public String getString(String key) {
        return this.sharedPreferences.getString(key, "");
    }

    /**
     * 写入Boolean变量至sharedPreferences中
     *
     * @param key   存储节点名称
     * @param value 存储节点的值
     */
    public void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * 写入String变量至sharedPreferences中
     *
     * @param key   存储节点名称
     * @param value 存储节点的值String
     */
    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }
}
