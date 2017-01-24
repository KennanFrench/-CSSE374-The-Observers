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

}
