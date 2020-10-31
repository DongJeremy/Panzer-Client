package org.cloud.panzer.bean;

import java.io.Serializable;

public class BaseBean<T> implements Serializable {
    public int code = 0;
    public T datas;
}
