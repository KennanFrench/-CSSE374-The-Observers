import java.io.IOException;
import java.io.PrintWriter;

public class FileRunner implements GraphVizRunner {

	@Override
	public void run(DesignConverter c, String filename) {
		try{
		    PrintWriter writer = new PrintWriter(filename);
		    writer.print(c.getGraphVizRep().replaceAll("\\$", ""));
		    writer.close();
		} catch (IOException e) {
		   System.out.println("Could not write to file " + filename);
		}
	}
}