import org.objectweb.asm.Type;

public class UMLArrow implements UMLElement {
	private HeadType headType;
	private LineType lineType;
	private String start;
	private String end;
	private String headLabel;
	private String tailLabel;
	private boolean bidirectional;
	
	public UMLArrow(String start, String end, HeadType head, LineType line) {
		this.start = start;
		this.end = end;
		this.headType = head;
		this.lineType = line;
		this.headLabel = "";
		this.tailLabel = "";
		this.bidirectional = false;
	}

	public UMLArrow(String label, String start, String end, HeadType head, LineType line) {
		this.headLabel = label;
		this.tailLabel = "";
		this.start = start;
		this.end = end;
		this.headType = head;
		this.lineType = line;
		this.bidirectional = false;
	}
	
	public UMLArrow(String label, String start, String end, HeadType head, LineType line, boolean bidir) {
		this.headLabel = label;
		this.tailLabel = "";
		this.start = start;
		this.end = end;
		this.headType = head;
		this.lineType = line;
		this.bidirectional = bidir;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof UMLArrow) {
			//return true;
			return this.headType.equals(((UMLArrow) o).getHeadType()) && this.lineType.equals(((UMLArrow) o).getLineType()) && this.start.equals(((UMLArrow) o).getStart()) && this.end.equals(((UMLArrow) o).getEnd()) && this.headLabel.equals(((UMLArrow) o).getHeadLabel());
		}
		return false;
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
	public String getHeadLabel() {
		return this.headLabel;
	}
	public void setLabel(String label) {
		this.headLabel = label;
	}
	
	public boolean getBidirectional() {
		return this.bidirectional;
	}
	public void setBidirectional(boolean bidirectional) {
		this.bidirectional = bidirectional;
	}

	public void setTailLabel(String tailLabel) {
		this.tailLabel = tailLabel;
	}

	public String getTailLabel() {
		return this.tailLabel;
	}
}
