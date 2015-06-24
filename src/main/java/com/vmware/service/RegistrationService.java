package com.vmware.service;


import static spark.Spark.*;

import java.io.File;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonParser;
import jodd.util.StringBand;

import com.vmware.model.LocateContainer;

/**
 * Hello world!
 *
 */
public class RegistrationService 
{
	 public static void main(String[] args) {
	        
		 RegistrationService obj = new RegistrationService();
		 
		 	get("/hello", (req, res) -> "Hello World");
		 	
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
		 		
		 		ProcessBuilder pb = new ProcessBuilder(new String[]{"java","-jar","/Users/skarkamanboina/Documents/workspace/Registration/src/test/java/Host/Registration/docker-0.0.1-SNAPSHOT.one-jar.jar", url.toString()});
		 		File outputFile = new File("/Users/skarkamanboina/Documents/workspace/Registration/src/test/java/Host/Registration/"+cont.getIpAddress()+"_log.txt");
                File errorFile = new File("/Users/skarkamanboina/Documents/workspace/Registration/src/test/java/Host/Registration/"+cont.getIpAddress()+"_ErrLog.txt");
                pb.redirectOutput(outputFile);
                pb.redirectError(errorFile);
                Process process = pb.start();
                        //process.waitFor();
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
