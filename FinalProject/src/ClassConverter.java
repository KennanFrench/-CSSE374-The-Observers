
public class ClassConverter implements Converter {

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
		this.graphVizRep.append(uClass.getName() + " [\n shape=\"record\",\n label=\"{");
		
		// Label
		// Give name and type 
		if (uClass.getCategory().equals(Category.ABSTRACT))
			this.graphVizRep.append("\\<\\<abstract\\>\\>\\n\n" + uClass.getName());
		else if(uClass.getCategory().equals(Category.INTERFACE))
			this.graphVizRep.append("\\<\\<interface\\>\\>\\n\n" + uClass.getName());
		else
			this.graphVizRep.append(uClass.getName());
		
		this.graphVizRep.append(" | ");
		
		// Fields
		for(UMLField field : uClass.getFields()) {
			if (field.getVisibility().compareTo(runViz) >= 0)
			{
				this.graphVizRep.append(field.getVisibility().getVisibilityCode() + " " + field.getName() + " : " + field.getType() + "\\l");
			}
		}
		// TODO Potentially remove | if no fields/methods 
		this.graphVizRep.append("|");

		for(UMLMethod method : uClass.getMethods()) {
			if (method.getVisibility().compareTo(runViz) >= 0
					&& !method.getName().equals("<init>")
					&& !method.getName().equals("<clinit>"))
			{
				StringBuilder paramList = new StringBuilder();
				for(int i = 0; i < method.getParameters().size(); i++)
				{
					UMLParam param = method.getParameters().get(i);
					paramList.append(param.getName() + ": "  + param.getType());
					if(i < method.getParameters().size() - 1)
						paramList.append(", ");
				}
				this.graphVizRep.append(method.getVisibility().getVisibilityCode() + " " + method.getName() + "(" + paramList + "): "  +  method.getType());
				if(method.isAbstract())
					this.graphVizRep.append("\\{abstract\\}");
				this.graphVizRep.append("\\l\n");
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