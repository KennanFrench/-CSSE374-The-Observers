import java.util.ArrayList;

public class UMLClass implements UMLElement {
	
	private String name;
	private Category category;
	private ArrayList<UMLField> fields;
	private ArrayList<UMLMethod> methods;
	
	public UMLClass(String name, Category category, ArrayList<UMLField> fields, ArrayList<UMLMethod> methods) {
		super();
		this.name = name;
		this.category = category;
		this.fields = fields;
		this.methods = methods;
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

}
