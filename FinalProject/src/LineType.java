
public enum LineType {
	SOLID, DASHED, DOTTED;
	
	public String getGraphVizRep() {
		if (this.equals(LineType.SOLID))
			return "solid";
		else if (this.equals(LineType.DASHED))
			return "dashed";
		else if (this.equals(LineType.DOTTED))
			return "dotted";
		return "Error";
	}
}
