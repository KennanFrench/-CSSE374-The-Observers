public class ArrowConverter implements IConverter {

	private UMLArrow arrow;
	private Visibility vis;
	private StringBuilder graphVizRep;
	public ArrowConverter(UMLArrow arrow, Visibility runViz) {
		this.arrow = arrow;
		this.vis = runViz;
		this.graphVizRep = new StringBuilder();
	}

	@Override
	public void convert() {
		this.graphVizRep.append(this.arrow.getStart() + " -> " + this.arrow.getEnd() + " ");
		this.graphVizRep.append("[" + "arrowhead=\"" + this.arrow.getHeadType().getGraphVizRep() + "\", style=\"" + this.arrow.getLineType().getGraphVizRep() + "\"");
		if (!this.arrow.getHeadLabel().equals(""))
			this.graphVizRep.append(", headlabel = \"" + this.arrow.getHeadLabel() + "\"");
		
		if (this.arrow.getBidirectional())
			this.graphVizRep.append(", dir = \"both\", arrowtail = \"" +  this.arrow.getHeadType().getGraphVizRep() + "\"");
		
		this.graphVizRep.append(", taillabel = \"" + this.arrow.getTailLabel() + "\"");

		this.graphVizRep.append("];\n");
	}

	@Override
	public String getGraphVizRep() {
		return graphVizRep.toString();
	}
	
	public Visibility getVis() {
		return this.vis;
	}

}
