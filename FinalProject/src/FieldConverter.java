public class FieldConverter implements IConverter {

	private UMLField field;
	private String graphVizRep; 
	
	public FieldConverter(UMLField field) {
		this.field = field;
	}

	@Override
	public void convert() {
		this.graphVizRep = this.field.getVisibility().getVisibilityCode() + " " + this.field.getName() + " : " + this.field.getType() + "\\l";
	}

	@Override
	public String getGraphVizRep() {
		return this.graphVizRep;
	}

}
