
public enum Visibility {
	 PRIVATE, PROTECTED, PUBLIC;
	
	
	public String getVisibilityCode() {
		if (this.equals(Visibility.PRIVATE))
			return "-";
		else if (this.equals(Visibility.PROTECTED))
			return "#";
		else
			return "+";
		// Potentially add default code
		
	}
	
	public static Visibility parseVisibility(String vis) {
		if (vis.equalsIgnoreCase("protected")) {
			return Visibility.PROTECTED;
		} else if (vis.equalsIgnoreCase("public")) {
			return Visibility.PUBLIC;
		}
		return Visibility.PRIVATE;
	}
}



