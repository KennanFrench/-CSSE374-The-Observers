public class MethodConverter implements Converter {

	private UMLMethod method;
	private StringBuilder graphVizRep;
	
	public MethodConverter(UMLMethod method) {
		this.method = method;
		this.graphVizRep = new StringBuilder();
	}

	@Override
	public void convert() {
		StringBuilder paramList = new StringBuilder();
		//Parameters
		for(int i = 0; i < this.method.getParameters().size(); i++)
		{
			UMLParam param = this.method.getParameters().get(i);
			paramList.append(param.getName() + ": "  + param.getType());
			if(i < this.method.getParameters().size() - 1)
				paramList.append(", ");
		}
		this.graphVizRep.append(method.getVisibility().getVisibilityCode() + " " + method.getName() + "(" + paramList + "): "  +  method.getType());
		if(method.isAbstract())
			this.graphVizRep.append("\\{abstract\\}");
		this.graphVizRep.append("\\l\n");
	}

	@Override
	public String getGraphVizRep() {
		return this.graphVizRep.toString();
	}

}
