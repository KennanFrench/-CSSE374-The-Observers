import java.util.ArrayList;
import java.util.HashMap;

public class DependencyInversionDetector extends AbstractDetector {

	public DependencyInversionDetector(ArrayList<UMLClass> classes, ArrayList<UMLArrow> arrows) {
		super(classes, arrows);
	}

	@Override
	public void detectPattern() {
		int associationCount = 0;
		HashMap<String, Integer> individualCounts = new HashMap<String, Integer>();

		for (UMLArrow arrow : this.arrowList) {
			if (arrow.getLineType() == LineType.SOLID && arrow.getHeadType() == HeadType.CLOSED) {
				for (UMLClass current : this.classList) {
					if (current.getName().equals(arrow.getEnd()) && current.getCategory() == Category.CLASS) {
						current.setOutlineColor("purple");
					}
				}
			} else if (arrow.getLineType() == LineType.SOLID && arrow.getHeadType() == HeadType.OPEN) {
				associationCount++;
				String name = arrow.getEnd();
				if (individualCounts.containsKey(name)) {
					individualCounts.put(name, individualCounts.get(name) + 1);
				} else {
					individualCounts.put(name, 1);
				}
			}
		}
		
		for (UMLClass current : this.classList) {
			if (individualCounts.containsKey(current.getName()) && current.getCategory() == Category.CLASS) {
				if (individualCounts.get(current.getName()) * 2 > 3 * associationCount / this.classList.size()) {
					current.setOutlineColor("purple");
				}
			}
		}
	}

}
