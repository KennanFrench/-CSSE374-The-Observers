import java.util.ArrayList;

public class TestDetector extends AbstractDetector {

	public TestDetector(ArrayList<IUMLElement> classes, ArrayList<IUMLElement> arrows) {
		super(classes, arrows);
	}

	@Override
	public void detectPattern() {
		for (IUMLElement currentClass : this.classList) {
			if (((UMLClass) currentClass).getName().contains("a")) {
				((UMLClass) currentClass).setColor("red");
			}
		}	
	}

}
