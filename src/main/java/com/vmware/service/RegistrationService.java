package com.vmware.service;


import static spark.Spark.*;
import io.advantageous.qbit.QBit;
import io.advantageous.qbit.events.EventBusProxyCreator;
import io.advantageous.qbit.events.EventManager;
import io.advantageous.qbit.events.EventManagerBuilder;
import io.advantageous.qbit.service.ServiceQueue;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonParser;

import com.vmware.model.LocateContainer;

import static io.advantageous.qbit.service.ServiceBuilder.serviceBuilder;
import static io.advantageous.qbit.service.ServiceProxyUtils.flushServiceProxy;


public class RegistrationService 
{
	
	public static final String CREATE_CHANNEL = "create";
	 public static void main(String[] args) {
	        
		 RegistrationService obj = new RegistrationService();
		 

	 	EventManager privateEventBus = EventManagerBuilder.eventManagerBuilder().setName("register").build();
	 	ServiceQueue privateEventBusServiceQueue = serviceBuilder()
                .setServiceObject(privateEventBus)
                .setInvokeDynamic(false).build();

        final EventBusProxyCreator eventBusProxyCreator =
                QBit.factory().eventBusProxyCreator();

        final MonitorService EventManager =
                eventBusProxyCreator.createProxy(privateEventBus, MonitorService.class);
        
        ServiceHandler test = new ServiceHandler(EventManager);
        SpwanMonitorService sms = new SpwanMonitorService();
        
        ServiceQueue testServiceQueue = serviceBuilder()
                .setServiceObject(test)
                .setInvokeDynamic(false).build();

        ServiceQueue execServiceQueue = serviceBuilder()
                .setServiceObject(sms)
                .setInvokeDynamic(false).build();

        privateEventBus.joinService(execServiceQueue);

        privateEventBusServiceQueue.start();
        testServiceQueue.start();
        execServiceQueue.start();

        ServiceClient ServiceClientProxy =
                testServiceQueue.createProxy(ServiceClient.class);

		 	post("/registerContainer", (req, res) -> {
		 		LocateContainer cont = new JsonParser().parse(req.body(), LocateContainer.class);
		 		obj.addContainer(cont);
		 		StringBuilder url = new StringBuilder();
		 		url.append("http://");
		 		url.append(cont.getIpAddress());
		 		url.append(":");
		 		url.append(cont.getPort());
		 		url.append("/v1.18/containers/");
		 		url.append(cont.getContainerName());
		 		url.append("/stats");
		 		
		 		ServiceClientProxy.createInstance(url.toString(), cont.getIpAddress());
		 		flushServiceProxy(ServiceClientProxy);
               return 1;
		 	});
		 	
		 	
	        
	    }
	 
	 int addContainer(LocateContainer cont)
	 {
		 	int status=0;
		 	HttpRequest httpRequest = HttpRequest.post("http://localhost:9090/addContainer");
			httpRequest.header("ipaddress", cont.getIpAddress());
			httpRequest.header("port", cont.getPort());
			httpRequest.header("containername", cont.getContainerName());
			HttpResponse response = httpRequest.send();
			status = response.statusCode();
			if(status != 200)
			{
				System.out.println("status Code: "+status+"message: " +response.statusPhrase());
			}
			return status;
	 }
}
