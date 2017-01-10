

public class UMLArrow implements UMLElement {
	private HeadType headType;
	private LineType lineType;
	private String start;
	private String end;
	private String label;
	
	public UMLArrow(String start, String end, HeadType head, LineType line) {
		this.start = start;
		this.end = end;
		this.headType = head;
		this.lineType = line;
		this.label = "";
	}

	public UMLArrow(String label, String start, String end, HeadType head, LineType line) {
		this.label = label;
		this.start = start;
		this.end = end;
		this.headType = head;
		this.lineType = line;
	}
		
	public HeadType getHeadType() {
		return headType;
	}
	public void setHeadType(HeadType headType) {
		this.headType = headType;
	}
	public LineType getLineType() {
		return lineType;
	}
	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
}
