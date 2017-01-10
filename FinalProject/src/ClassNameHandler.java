
public class ClassNameHandler {
	public static String getNiceName(String slashName)
	{
		if(slashName == null) {
			System.out.println("passed getNiceName a null string");
			return null;
		}
		String tempArray[] = slashName.split("/");
		return tempArray[tempArray.length -1];
	}

	public static String getDotName(String slashName) {
		if(slashName == null) {
			System.out.println("passed getDotName a null string");
			return null;
		}
		return slashName.replaceAll("/", ".");
	}

	public static String removeStart(String string) {
		if(string == null)
			return null;
		if(string.startsWith("["))
			return string.substring(2);
		//if(string.startsWith("L")) {
			return string.substring(1);
		//}
		//return string;
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
	
}
