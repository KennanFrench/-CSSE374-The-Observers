

public class UMLField implements UMLElement {
	private Visibility visibility;
	private String name;
	private String type;
	
	public UMLField(Visibility vis, String name, String type)
	{
		this.visibility = vis;
		this.name = name;
		this.type = type;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}


