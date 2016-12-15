
public enum HeadType {
	OPEN, CLOSED, FILLED, DIAMOND;

	public String getGraphVizRep() {
		if (this.equals(HeadType.OPEN))
			return "onormal";
		else if (this.equals(HeadType.CLOSED))
			return "???";
		else if (this.equals(HeadType.FILLED))
			return "???";
		else if (this.equals(HeadType.DIAMOND))
			return "???";
		return "error";
	}
}


