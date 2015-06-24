package com.vmware.service;

import io.advantageous.qbit.annotation.OnEvent;

import java.io.File;
import java.io.IOException;

public class SpwanMonitorService {
	
	@OnEvent(RegistrationService.CREATE_CHANNEL)
	public void startMonitor(String strUrl, String ipAddress)
	{
		try {
			System.out.println("start Monitor called.......");
		ProcessBuilder pb = new ProcessBuilder(new String[]{"java","-jar","/Users/skarkamanboina/Documents/workspace/Registration/src/test/java/Host/Registration/docker-0.0.1-SNAPSHOT.one-jar.jar", strUrl});
 		File outputFile = new File("/Users/skarkamanboina/Documents/workspace/Registration/src/test/java/Host/Registration/"+ipAddress+"_log.txt");
        File errorFile = new File("/Users/skarkamanboina/Documents/workspace/Registration/src/test/java/Host/Registration/"+ipAddress+"_ErrLog.txt");
        pb.redirectOutput(outputFile);
        pb.redirectError(errorFile);
        
			Process process = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
