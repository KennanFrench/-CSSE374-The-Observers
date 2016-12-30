import java.io.IOException;

public class PictureRunner implements GraphVizRunner {

	@Override
	public void run(DesignConverter c, String filename) {
		Runtime rt = Runtime.getRuntime();
		try {
			Process p = new ProcessBuilder("cmd", "/c", "dot","-Tpng", filename, "-o", "out.png").start();
		} catch (IOException e) {
			System.out.println("Could not create out.png from " + filename);
		}
//		Process pr = rt.exec("cmd /c dot -Tpng test.gv > out.png");
	}
	
}
