import java.util.ArrayList;

public abstract class AbstractDetector {
	protected ArrayList<UMLElement> classList;
	protected ArrayList<UMLElement> arrowList;
	
	public AbstractDetector(ArrayList<UMLElement> classes, ArrayList<UMLElement> arrows) {
		this.classList = classes;
		this.arrowList = arrows;
	}

	public abstract void detectPattern();
	
	public void setClasses(ArrayList<UMLElement> classes) {
		this.classList = classes;
	}
	
	public void setArrows(ArrayList<UMLElement> arrows) {
		this.arrowList = arrows;
	}

	public ArrayList<UMLElement> getClasses() {
		return this.classList;
	}
	public ArrayList<UMLElement> getArrows() {
		return this.arrowList;
	}
}
