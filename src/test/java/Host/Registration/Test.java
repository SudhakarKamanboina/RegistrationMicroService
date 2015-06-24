package Host.Registration;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.Arrays;




public class Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		/*HttpRequest httpRequest = HttpRequest.get("http://192.168.59.103:2375/v1.18/containers/json");//http://192.168.59.103:2375/v1.18/containers/json http://192.168.59.103:2375/v1.18/containers/nodejs/stats
		httpRequest.connectionKeepAlive(true);
		httpRequest.accept("application/octet-stream");
	    HttpResponse response = httpRequest.send();*/

	    //System.out.println(response);
		/*Model model = new Model();
		model.setRead("2015-06-07T17:02:07.59692181Z");
		ObjectMapper mapper = new ObjectMapper();
		try {
			String strJson = mapper.writeValueAsString(model);
			System.out.println(strJson);
			
			try {
				model = mapper.readValue(strJson, Model.class);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
		
		//Process ps=Runtime.getRuntime().exec("");
        //String s = null;
        //BufferedInputStream catOutput= new BufferedInputStream(ps.getInputStream());
        ProcessBuilder pb = new ProcessBuilder(new String[]{"java","-jar","/Users/skarkamanboina/Documents/workspace/Registration/src/test/java/Host/Registration/docker-0.0.1-SNAPSHOT.one-jar.jar", "http://192.168.59.103:2375/v1.18/containers/nodejs/stats"});
        /*pb.redirectOutput(Redirect.INHERIT);
        pb.redirectError(Redirect.INHERIT);
        pb.directory(new File("192.txt"));
        Process p = pb.start();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String s = "";
        while((s = in.readLine()) != null){
            System.out.println(s);
        }
        int status = p.waitFor();
        System.out.println("Exited with status: " + status);*/
        
        System.out.println("Redirect output and error to file");
                File outputFile = new File("/Users/skarkamanboina/Documents/workspace/Registration/src/test/java/Host/Registration/PingLog.txt");
                File errorFile = new File("/Users/skarkamanboina/Documents/workspace/Registration/src/test/java/Host/Registration/PingErrLog.txt");
                pb.redirectOutput(outputFile);
                pb.redirectError(errorFile);
                Process process = pb.start();
                        //process.waitFor();

       
        
	}
}
