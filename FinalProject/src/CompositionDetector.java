import java.util.ArrayList;

public class CompositionDetector extends AbstractDetector {

	public CompositionDetector(ArrayList<UMLClass> classes, ArrayList<UMLArrow> arrows) {
		super(classes, arrows);
	}

	@Override
	public void detectPattern() {
		ArrayList<UMLArrow> arrowsToColor = new ArrayList<UMLArrow>();

		for (UMLArrow arrow : this.arrowList) {
			if (arrow.getHeadType().equals(HeadType.CLOSED) && arrow.getLineType().equals(LineType.SOLID)) {
				arrowsToColor.add(arrow);
			}
		}

		for (UMLArrow arrow : arrowsToColor) {
			UMLClass start = null;
			UMLClass end = null;
			for (UMLClass currentClass : this.classList) {
				if (arrow.getStart().equals(currentClass.getName())) {
					start = currentClass;
				}
				if (arrow.getEnd().equals(currentClass.getName())) {
					end = currentClass;
				}
			}
			if (start != null && end != null) {
				if (start.getMethods().size() * 2 > end.getMethods().size()) {
					arrow.setColor("orange");
					start.setOutlineColor("orange");
					end.setOutlineColor("orange");
				}
			}
		}
	}

}
