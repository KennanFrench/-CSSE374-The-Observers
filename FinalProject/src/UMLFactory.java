import java.util.ArrayList;

public class UMLFactory {

	public ArrayList<UMLClass> createClassList(ArrayList<IUMLElement> in) {
		ArrayList<UMLClass> out = new ArrayList<UMLClass>();
		for (IUMLElement el : in) {
			out.add((UMLClass) el);
		}
		return out;
	}
	
	public ArrayList<UMLArrow> createArrowList(ArrayList<IUMLElement> in) {
		ArrayList<UMLArrow> out = new ArrayList<UMLArrow>();
		for (IUMLElement el : in) {
			out.add((UMLArrow) el);
		}
		return out;
	}
	
	public UMLClass getClassFromName(String name, ArrayList<UMLClass> classList) {
		for (UMLClass current : classList) {
			if (current.getName().equals(name)) {
				return current;
			}
		}
		System.out.println("uh oh");
		return null;
	}

}
