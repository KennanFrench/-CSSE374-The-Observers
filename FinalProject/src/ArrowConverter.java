public class ArrowConverter implements Converter {

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
		this.graphVizRep.append("[" + "arrowhead=\"" + this.arrow.getHeadType().getGraphVizRep() + "\", style=\"" + this.arrow.getLineType().getGraphVizRep() + "\"];\n");
	}

	@Override
	public String getGraphVizRep() {
		return graphVizRep.toString();
	}
	
	public Visibility getVis() {
		return this.vis;
	}

}
