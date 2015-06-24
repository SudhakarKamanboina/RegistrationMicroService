package com.vmware.service;

import io.advantageous.qbit.annotation.EventChannel;

public interface MonitorService {

	@EventChannel(RegistrationService.CREATE_CHANNEL)
    void createInstance(String test, String param);
}
