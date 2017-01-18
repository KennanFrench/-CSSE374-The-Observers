
public class PrintRunner implements IGraphVizRunner {

	@Override
	public void run(DesignConverter c, String filename) {
		System.out.println(c.getGraphVizRep().replaceAll("\\$", ""));
	}	
}
