
public class PrintRunner implements GraphVizRunner {

	@Override
	public void run(DesignConverter c, String filename) {
		System.out.println(c.getGraphVizRep().replaceAll("\\$", ""));
	}	
}
