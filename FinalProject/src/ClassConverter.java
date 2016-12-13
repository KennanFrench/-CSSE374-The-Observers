
public class ClassConverter implements Converter {

	private UMLClass uClass;
	private StringBuilder gVizRep; 
	private Visibility runViz;
	
	public ClassConverter(UMLClass uClass, Visibility runViz) {
		this.uClass = uClass;
		this.gVizRep = new StringBuilder();
		this.runViz = runViz;
	}
	@Override
	public void convert() {
		// Create node
		this.gVizRep.append(uClass.getName() + " [ shape=\"record\", label=");
		
		// Label
		// Give name and type 
		if (uClass.getCategory().equals(Category.ABSTRACT))
			this.gVizRep.append("\"{\\<\\<abstract\\>\\>\\n" + uClass.getName());
		else if(uClass.getCategory().equals(Category.INTERFACE))
			this.gVizRep.append("\"{\\<\\<interface\\>\\>\\n" + uClass.getName());
		else
			this.gVizRep.append(uClass.getName());
		
		this.gVizRep.append("|");
		
		// Fields
		for(UMLField field : uClass.getFields()) {
			if (field.getVisibility().compareTo(runViz) >= 0)
			{
				this.gVizRep.append(field.getVisibility().getVisibilityCode() + " " + field.getName() + ": " + field.getType() + "\\l");
			}
		}
		// TODO Potentially remove | if no fields/methods 
		this.gVizRep.append("|");

		for(UMLMethod method : uClass.getMethods()) {
			if (method.getVisibility().compareTo(runViz) >= 0)
			{
				StringBuilder paramList = new StringBuilder();
				for(int i = 0; i < method.getParameters().size(); i++)
				{
					UMLParam param = method.getParameters().get(i);
					paramList.append(param.getName() + ": "  + param.getType());
					if(i < method.getParameters().size() - 1)
						paramList.append(", ");
				}
				this.gVizRep.append(method.getVisibility().getVisibilityCode() + " " + method.getName() + "(" + paramList + "): "  +  method.getType());
				if(method.isAbstract())
					this.gVizRep.append("{abstract}");
				this.gVizRep.append("\\l");
			}
		}
		// Ending
		this.gVizRep.append("}\" ];");
		
			

	}
}