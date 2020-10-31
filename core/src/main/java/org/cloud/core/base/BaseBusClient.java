package org.cloud.core.base;

import org.greenrobot.eventbus.EventBus;

public class BaseBusClient extends EventBus {
    private static volatile BaseBusClient instance;

    public static BaseBusClient getInstance() {
        if (instance == null) {
            synchronized (BaseBusClient.class) {
                if (instance == null) {
                    instance = new BaseBusClient();
                }
            }
        }
        return instance;
    }
}
