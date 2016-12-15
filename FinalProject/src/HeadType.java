
public enum HeadType {
	OPEN, CLOSED, FILLED, DIAMOND;

	public String getGraphVizRep() {
		if (this.equals(HeadType.OPEN))
			return "vee";
		else if (this.equals(HeadType.CLOSED))
			return "onormal";
		else if (this.equals(HeadType.FILLED))
			return "normal";
		else if (this.equals(HeadType.DIAMOND))
			return "odiamond";
		return "error";
	}
}


