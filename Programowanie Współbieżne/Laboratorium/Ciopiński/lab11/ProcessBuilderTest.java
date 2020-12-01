package lab11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ProcessBuilderTest {
public static void main(String[] args) throws IOException, InterruptedException {
	ProcessBuilder processBuilder=new ProcessBuilder();
	processBuilder.command("net","start");
	
		Process process=processBuilder.start();
		BufferedReader input= new BufferedReader(new InputStreamReader(process.getInputStream()));
		String linia=null;
		while((linia=input.readLine())!=null){
			if (linia.length()>0){
				System.out.println(linia);
			}
		}
		process.waitFor();
	
}
}
