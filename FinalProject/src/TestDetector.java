import java.util.ArrayList;

public class TestDetector extends AbstractDetector {

	public TestDetector(ArrayList<UMLClass> classes, ArrayList<UMLArrow> arrows) {
		super(classes, arrows);
	}

	@Override
	public void detectPattern() {
		for (UMLClass currentClass : this.classList) {
			if (currentClass.getName().contains("a")) {
				currentClass.setColor("red");
			}
		}	
	}

}
