import java.util.ArrayList;

public class ClassNameHandler {
	public static String getNiceFromSlash(String slashName)
	{
		if(slashName == null) {
			//System.out.println("passed getNiceName a null string");
			return null;
		}
		String tempArray[] = slashName.split("/");
		return tempArray[tempArray.length -1];
	}
	
	public static String getNiceFromDot(String dotName)
	{
		if(dotName == null) {
			//System.out.println("passed getNiceName a null string");
			return null;
		}
		String tempArray[] = dotName.split("\\.");
		return tempArray[tempArray.length -1];
	}

	public static String getDotName(String slashName) {
		if(slashName == null) {
			//System.out.println("passed getDotName a null string");
			return null;
		}
		return slashName.replaceAll("/", ".");
	}

	public static String removeStart(String string) {
		if (string.contains("T_"))
			return "";
		if(string == null)
			return null;
		int indexL = string.indexOf("L");
		if (indexL < 0 )
			return "";
		return string.substring(indexL+1);
		/*if(string.startsWith("["))
			string = string.substring(1);
		if(string.startsWith("T")) {
			string = string.substring(1);
		}
		if(string.startsWith("E")) {
			string = string.substring(1);
		}
		
		if(string.startsWith("L")) {
			string = string.substring(1);
		}
		return string;*/
		}
	
	
	public static String removeEnd(String dotName) {
		if (dotName.endsWith(";"))
			return dotName.substring(0, dotName.length()-1);
		return dotName;
	}

	public static String getDescriptor(String original) {
		int index = 0;
		StringBuilder desc = new StringBuilder();
		while(original.charAt(index) == '[') {
			desc.append('[');
			index++;
		}
		desc.append(original.charAt(index));
		
		return desc.toString();
	}
	

	public static String getClassName(String original) {
		int index = 0;
		//StringBuilder desc = new StringBuilder();
		while(original.charAt(index) == '[') {
			//desc.append('[');
			index++;
		}
		index++;
		String temp = ClassNameHandler.removeEnd(original.substring(index));
		return temp;
	}
	
	public static ArrayList<String> getNiceFromDotArray(ArrayList<String> dots) {
		ArrayList<String> niceNames = new ArrayList<String>();
		for (String dot : dots) {
			niceNames.add(ClassNameHandler.getNiceFromDot(dot));
		}
		return niceNames;
	}
	
}
