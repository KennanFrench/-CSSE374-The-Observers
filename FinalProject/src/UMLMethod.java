import java.util.ArrayList;

public class UMLMethod {
	private String name;
	private Visibility visibility;
	private boolean isAbstract;
	private String type; 
	private ArrayList<UMLField> parameters;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	public boolean isAbstract() {
		return isAbstract;
	}
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<UMLField> getParameters() {
		return parameters;
	}
	public void setParameters(ArrayList<UMLField> parameters) {
		this.parameters = parameters;
	}
	
}
