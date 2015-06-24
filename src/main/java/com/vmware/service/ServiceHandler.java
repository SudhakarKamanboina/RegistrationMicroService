package com.vmware.service;

import io.advantageous.qbit.annotation.QueueCallback;
import io.advantageous.qbit.annotation.QueueCallbackType;
import static io.advantageous.qbit.service.ServiceProxyUtils.flushServiceProxy;

public class ServiceHandler {
	final MonitorService eventManager;


    public ServiceHandler(final MonitorService eventManager) {
        this.eventManager = eventManager;

    }


    @QueueCallback(QueueCallbackType.EMPTY)
    private void noMoreRequests() {

        flushServiceProxy(eventManager);
    }


    @QueueCallback(QueueCallbackType.LIMIT)
    private void hitLimitOfRequests() {

        flushServiceProxy(eventManager);
    }


    public void createInstance(String url, String ip) {

        eventManager.createInstance(url, ip);

    }
}
