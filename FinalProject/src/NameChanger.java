
public class NameChanger {
	public static String getNiceName(String slashName)
	{
		if(slashName == null)
			return null;
		String tempArray[] = slashName.split("/"); 
		return tempArray[tempArray.length -1];
	}

	public static String getDotName(String slashName) {
		if(slashName == null)
			return null;
		return slashName.replaceAll("/", ".");
	}

public static String removeStart(String string) {
	if(string == null)
		return null;
	if(string.startsWith("["))
		return string.substring(2);
	return string.substring(1);
	}

public static String removeEnd(String dotName) {
	if (dotName.endsWith(";"))
		return dotName.substring(0, dotName.length()-1);
	return dotName;
}
}
