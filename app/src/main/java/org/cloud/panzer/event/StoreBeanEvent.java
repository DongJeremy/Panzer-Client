package org.cloud.panzer.event;

import org.cloud.core.base.BaseBean;

/**
 * FileName: StoreBeanEvent
 * Author: Admin
 * Date: 2020/11/11 14:38
 * Description: StoreBeanEvent
 */
public class StoreBeanEvent {

    private BaseBean baseBean;

    public StoreBeanEvent(BaseBean baseBean) {
        this.baseBean = baseBean;
    }

    public BaseBean getBaseBean() {
        return this.baseBean;
    }

    public void setBaseBean(BaseBean baseBean) {
        this.baseBean = baseBean;
    }

}
