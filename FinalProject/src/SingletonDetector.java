import java.util.ArrayList;

public class SingletonDetector extends AbstractDetector {

	public SingletonDetector(ArrayList<UMLClass> classes, ArrayList<UMLArrow> arrows) {
		super(classes, arrows);
	}

	@Override
	public void detectPattern() {
		for (UMLClass currentClass : this.classList) {
			
		}
	}

}
