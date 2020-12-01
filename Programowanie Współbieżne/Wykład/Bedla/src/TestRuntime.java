import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestRuntime {
	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		Process process = runtime.exec("ipconfig /all");
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String s = null;
		while ((s = input.readLine()) != null)
			if (s.length() > 0)
				System.out.println(s);
		input.close();
		process.waitFor();

		Process process2 = runtime.exec("notepad");
		process2.waitFor();
	}
}