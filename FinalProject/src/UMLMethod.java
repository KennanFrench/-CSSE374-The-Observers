
import java.util.ArrayList;

public class UMLMethod implements IUMLElement {
	private String name;
	private Visibility visibility;
	private boolean isAbstract;
	private String type; 
	private ArrayList<UMLParam> parameters;
	
	public UMLMethod(String name, Visibility vis, boolean isAbstract, String methodType,
			ArrayList<UMLParam> parameters) {
		this.name = name;
		this.visibility = vis;
		this.isAbstract = isAbstract;
		this.type = methodType;
		this.parameters = parameters;
	}
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
	public ArrayList<UMLParam> getParameters() {
		return parameters;
	}
	public void setParameters(ArrayList<UMLParam> parameters) {
		this.parameters = parameters;
	}
	
}
