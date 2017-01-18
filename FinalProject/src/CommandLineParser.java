import java.util.ArrayList;
import java.util.Arrays;

public class CommandLineParser implements IParser{

	private ArrayList<String> args;
	//private final ArrayList<String> MULTIWORDARGS = new ArrayList<String>();
	//use the above ArrayList if we switch to an argument-by-argument parsing style
	private ArrayList<String> classList;
	private Visibility runVis;
	private boolean drawRecursive;
	private String settingsPath;
	
	public CommandLineParser(String[] args) {
		this.args = new ArrayList<String>(Arrays.asList(args));
		this.runVis = Visibility.PRIVATE;
		this.drawRecursive = false;
		this.settingsPath = "";
	}
	
	@Override
	public void parse() {
		for (int i = 0; i < args.size(); i++) {
			if (args.get(i).startsWith("--")) {
				this.parseDashDash(args.get(i));
				args.remove(i);
				i--;
			} else if (args.get(i).startsWith("-")) {
				this.parseDash(args.get(i));
				args.remove(i);
				i--;
			}
		}
		this.classList = args;
	}
	
	private void parseDashDash(String arg) {
		if (arg.equalsIgnoreCase("--public")) {
			this.runVis = Visibility.PUBLIC;
		} else if (arg.equalsIgnoreCase("--protected")) {
			this.runVis = Visibility.PROTECTED;
		} else if (arg.equalsIgnoreCase("--private")) {
			this.runVis = Visibility.PRIVATE;
		} else if (arg.startsWith("--settingsfile")) {
			this.settingsPath = arg.substring(arg.indexOf('=') + 1);
		}
	}
	
	private void parseDash(String arg) {
		if (arg.equals("-r")) {
			this.drawRecursive = true;
		}
	}

	public ArrayList<String> getClassList() {
		return classList;
	}

	public Visibility getRunVis() {
		return runVis;
	}

	public boolean getDrawRecursive() {
		return drawRecursive;
	}
	
	public String getSettingsPath() {
		return this.settingsPath;
	}

}
