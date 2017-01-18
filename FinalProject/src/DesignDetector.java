import java.util.ArrayList;

public class DesignDetector {
	private ArrayList<AbstractDetector> detectors;
//	private ArrayList<UMLElement> classes;
//	private ArrayList<UMLElement> arrows;
	
	public DesignDetector(ArrayList<AbstractDetector> detectors, ArrayList<UMLElement> classes, ArrayList<UMLElement> arrows) {
		this.detectors = detectors;
//		this.classes = classes;
//		this.arrows = arrows;
	}
	
	public void runDetectors() {
		for (AbstractDetector detector : this.detectors) {
//			detector.setClasses(this.classes);
//			detector.setArrows(this.arrows);
			detector.detectPattern();
//			this.classes = detector.getClasses();
//			this.arrows = detector.getArrows();
		}
	}
	
//	public ArrayList<UMLElement> getClasses() {
//		return this.classes;
//	}
	
//	public ArrayList<UMLElement> getArrows() {
//		return this.arrows;
//	}
}
