
public class UMLArrow {
	private HeadType headType;
	private LineType lineType;
	private UMLClass start;
	private UMLClass end;
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
	public UMLClass getStart() {
		return start;
	}
	public void setStart(UMLClass start) {
		this.start = start;
	}
	public UMLClass getEnd() {
		return end;
	}
	public void setEnd(UMLClass end) {
		this.end = end;
	}
}
