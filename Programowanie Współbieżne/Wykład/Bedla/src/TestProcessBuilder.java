import java.io.File;
import java.io.IOException;

public class TestProcessBuilder {
	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\WINDOWS\\system32\\notepad.exe");
		processBuilder.directory(new File("C:\\"));
		Process process = processBuilder.start();
		process.waitFor();
	}
}