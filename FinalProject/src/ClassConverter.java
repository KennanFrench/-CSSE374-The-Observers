public class ClassConverter implements IConverter {

	private UMLClass uClass;
	private StringBuilder graphVizRep; 
	private Visibility runViz;
	
	public ClassConverter(UMLClass uClass, Visibility runViz) {
		this.uClass = uClass;
		this.graphVizRep = new StringBuilder();
		this.runViz = runViz;
	}
	@Override
	public void convert() {
		// Create node
		this.graphVizRep.append(uClass.getName() + " [\n shape=\"record\",\n color=\"" + uClass.getOutlineColor() + "\",\n style=\"filled\",\n fillcolor=\"" + uClass.getBackgroundColor() + "\"\n label=\"{");
		
		// Label
		// Give name and type 
		if (uClass.getCategory().equals(Category.ABSTRACT))
			this.graphVizRep.append("\\<\\<abstract\\>\\>\\n\n" + uClass.getName());
		else if(uClass.getCategory().equals(Category.INTERFACE))
			this.graphVizRep.append("\\<\\<interface\\>\\>\\n\n" + uClass.getName());
		else
			this.graphVizRep.append(uClass.getName());
		this.graphVizRep.append(uClass.getStereotype());
		
		this.graphVizRep.append(" | ");
		
		// Fields
		FieldConverter currFC;
		for(UMLField field : uClass.getFields()) {
			if(field != null) {
				if (field.getVisibility().compareTo(runViz) >= 0)
				{
					currFC = new FieldConverter(field);
					currFC.convert();
					this.graphVizRep.append(currFC.getGraphVizRep());
				}
			}

		}
		// TODO Potentially remove | if no fields/methods 
		this.graphVizRep.append("|");

		// Methods
		MethodConverter currMC;
		for(UMLMethod method : uClass.getMethods()) {
			if (method.getVisibility().compareTo(runViz) >= 0
					&& !method.getName().equals("<init>")
					&& !method.getName().equals("<clinit>"))
			{
				currMC = new MethodConverter(method);
				currMC.convert();
				this.graphVizRep.append(currMC.getGraphVizRep());
			}
		}
		// Ending
		this.graphVizRep.append("}\" \n];");
		
	}
	@Override
	public String getGraphVizRep() {
		return this.graphVizRep.toString();
	}
}