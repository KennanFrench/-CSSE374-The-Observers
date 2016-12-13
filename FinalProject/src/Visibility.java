
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
}



