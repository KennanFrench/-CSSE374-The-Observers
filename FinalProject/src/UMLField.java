

public class UMLField implements IUMLElement {
	private Visibility visibility;
	private String name;
	private String type;
	private boolean isStatic;
	
	public UMLField(Visibility vis, String name, String type, boolean isStatic)
	{
		this.visibility = vis;
		this.name = name;
		this.type = type;
		this.isStatic = isStatic;
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

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
}


