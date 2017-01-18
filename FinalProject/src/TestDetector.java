import java.util.ArrayList;

public class TestDetector extends AbstractDetector {

	public TestDetector(ArrayList<UMLElement> classes, ArrayList<UMLElement> arrows) {
		super(classes, arrows);
	}

	@Override
	public void detectPattern() {
		for (UMLElement currentClass : this.classList) {
			if (((UMLClass) currentClass).getName().contains("a")) {
				((UMLClass) currentClass).setColor("red");
			}
		}	
	}

}
