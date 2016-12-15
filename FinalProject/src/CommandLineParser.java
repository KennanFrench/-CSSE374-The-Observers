import java.util.ArrayList;
import java.util.Arrays;

public class CommandLineParser implements Parser{

	private ArrayList<String> args;
	//private final ArrayList<String> MULTIWORDARGS = new ArrayList<String>();
	//use the above ArrayList if we switch to an argument-by-argument parsing style
	private ArrayList<String> classList;
	private Visibility runVis;
	
	public CommandLineParser(String[] args) {
		this.args = new ArrayList<String>(Arrays.asList(args));
		this.runVis = Visibility.PRIVATE;
	}
	
	@Override
	public void parse() {
		String lastArg = args.get(args.size() - 1);
		if (lastArg.equalsIgnoreCase("public")) {
			this.runVis = Visibility.PUBLIC;
			args.remove(args.size() - 1);
		} else if (lastArg.equalsIgnoreCase("protected")) {
			this.runVis = Visibility.PROTECTED;
			args.remove(args.size() - 1);
		} else if (lastArg.equalsIgnoreCase("private")) {
			this.runVis = Visibility.PRIVATE;
			args.remove(args.size() - 1);
		}
		this.classList = args;
	}

	public ArrayList<String> getClassList() {
		return classList;
	}

	public Visibility getRunVis() {
		return runVis;
	}

}
