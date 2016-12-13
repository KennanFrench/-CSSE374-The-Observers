import java.util.ArrayList;
import java.util.Arrays;

public class CommandLineParser implements Parser{

	private ArrayList<String> args;
	//private final ArrayList<String> MULTIWORDARGS = new ArrayList<String>();
	//use the above ArrayList if we switch to an argument-by-argument parsing style
	private ArrayList<String> classList;
	private Visibility renderVis;
	
	public CommandLineParser(String[] args) {
		this.args = new ArrayList<String>(Arrays.asList(args));
		this.renderVis = null;
	}
	
	@Override
	public void parse() {
		String lastArg = args.get(args.size() - 1);
		if (lastArg.equalsIgnoreCase("public")) {
			this.renderVis = Visibility.PUBLIC;
			args.remove(args.size() - 1);
		} else if (lastArg.equalsIgnoreCase("protected")) {
			this.renderVis = Visibility.PROTECTED;
			args.remove(args.size() - 1);
		} else if (lastArg.equalsIgnoreCase("private")) {
			this.renderVis = Visibility.PRIVATE;
			args.remove(args.size() - 1);
		}
		this.classList = args;
	}

	public ArrayList<String> getClassList() {
		return classList;
	}

	public Visibility getRenderVis() {
		return renderVis;
	}

}
