
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
}
