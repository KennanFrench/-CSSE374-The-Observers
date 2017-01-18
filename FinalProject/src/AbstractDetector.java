import java.util.ArrayList;

public abstract class AbstractDetector {
	protected ArrayList<IUMLElement> classList;
	protected ArrayList<IUMLElement> arrowList;
	
	public AbstractDetector(ArrayList<IUMLElement> classes, ArrayList<IUMLElement> arrows) {
		this.classList = classes;
		this.arrowList = arrows;
	}

	public abstract void detectPattern();
	
	public void setClasses(ArrayList<IUMLElement> classes) {
		this.classList = classes;
	}
	
	public void setArrows(ArrayList<IUMLElement> arrows) {
		this.arrowList = arrows;
	}

	public ArrayList<IUMLElement> getClasses() {
		return this.classList;
	}
	public ArrayList<IUMLElement> getArrows() {
		return this.arrowList;
	}
}
