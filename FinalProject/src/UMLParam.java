

public class UMLParam implements IUMLElement {
	private String name;
	private String type;
	
	public UMLParam(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
