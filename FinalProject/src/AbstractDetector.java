import java.util.ArrayList;

public abstract class AbstractDetector {
	protected ArrayList<UMLClass> classList;
	protected ArrayList<UMLArrow> arrowList;
	
	public AbstractDetector(ArrayList<UMLClass> classes, ArrayList<UMLArrow> arrows) {
		this.classList = classes;
		this.arrowList = arrows;
	}

	public abstract void detectPattern();
	
	public void setClasses(ArrayList<UMLClass> classes) {
		this.classList = classes;
	}
	
	public void setArrows(ArrayList<UMLArrow> arrows) {
		this.arrowList = arrows;
	}

	public ArrayList<UMLClass> getClasses() {
		return this.classList;
	}
	public ArrayList<UMLArrow> getArrows() {
		return this.arrowList;
	}
}
