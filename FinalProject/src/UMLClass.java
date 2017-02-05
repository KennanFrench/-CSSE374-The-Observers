
import java.util.ArrayList;

public class UMLClass implements IUMLElement {
	
	private String name;
	private Category category;
	private ArrayList<UMLField> fields;
	private ArrayList<UMLMethod> methods;
	private String outlineColor;
	private String backgroundColor;
	private String stereotype;
	
	public UMLClass(String name, Category category, ArrayList<UMLField> fields, ArrayList<UMLMethod> methods) {
		super();
		this.name = name;
		this.category = category;
		this.fields = fields;
		this.methods = methods;
		this.stereotype = "";
		this.backgroundColor = "white";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ArrayList<UMLField> getFields() {
		return fields;
	}

	public void setFields(ArrayList<UMLField> fields) {
		this.fields = fields;
	}

	public ArrayList<UMLMethod> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<UMLMethod> methods) {
		this.methods = methods;
	}

	public String getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(String color) {
		this.outlineColor = color;
	}

	public String getStereotype() {
		return stereotype;
	}

	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
