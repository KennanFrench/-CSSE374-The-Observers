import java.util.ArrayList;

public class AdaptorDetector extends AbstractDetector {

	public AdaptorDetector(ArrayList<UMLClass> classes, ArrayList<UMLArrow> arrows) {
		super(classes, arrows);
	}

	@Override
	public void detectPattern() {
		ArrayList<String> children = new ArrayList<>();
		ArrayList<String> parents = new ArrayList<>();
		ArrayList<String> fields = new ArrayList<>();
		ArrayList<UMLArrow> inheritances = new ArrayList<>();
		ArrayList<UMLArrow> associations = new ArrayList<>();

		for (UMLArrow arrow : this.arrowList) {
			if (arrow.getHeadType() == HeadType.CLOSED) {
				inheritances.add(arrow);
				children.add(arrow.getStart());
				parents.add(arrow.getEnd());
			}
		}
		
		int i = 0;
		int size = children.size();
		while (i < size) {
			String child = children.get(i);
			boolean remove = true;
			for (UMLArrow arrow : this.arrowList) {
				if (arrow.getLineType() == LineType.SOLID && arrow.getHeadType() == HeadType.OPEN) {
					if (!arrow.getEnd().equals(arrow.getStart())) { 
						if (arrow.getStart().equals(child)) {
							remove = false;
							associations.add(arrow);
							fields.add(arrow.getEnd());
						}
					}
				}
			}
			if (remove) {
				children.remove(child);
				size--;
				i--;
			}
			i++;
		}
		
		for (UMLArrow arrow : inheritances) {
			int overrides = 0;
			UMLClass child = getClassFromName(arrow.getStart());
			UMLClass parent = getClassFromName(arrow.getEnd());
			for (UMLMethod childMethod : child.getMethods()) {
				for (UMLMethod parentMethod : parent.getMethods()) {
					if (childMethod.getName().equals(parentMethod.getName())) {
						boolean sameParams = true;
						for (UMLParam childParam : childMethod.getParameters()) {
							for (UMLParam parentParam : parentMethod.getParameters()) {
								if (!childParam.getType().equals(parentParam.getType())) {
									sameParams = false;
								}
							}
						}
						
						if (sameParams) {
							overrides++;
						}

					}
				}
			}
			
			if (overrides * 4 < 3 * parent.getMethods().size()) {
				if (children.contains(child.getName())) {
					children.remove(child.getName());
				}
			}
		}
		
		for (UMLArrow arrow : inheritances) {
			boolean removeParent = true;
			if (children.contains(arrow.getStart())) {
				removeParent = false;
			}
			if (removeParent) {
				parents.remove(arrow.getEnd());
			}
		}
		
		i = 0;
		size = associations.size();
		while (i < size) {
			UMLArrow arrow = associations.get(i);
			boolean removeField = true;
			if (children.contains(arrow.getStart())) {
				removeField = false;
			}
			if (removeField) {
				fields.remove(arrow.getEnd());
				associations.remove(i);
				size--;
				i--;
			}
			i++;
		}
		
		for (String name : children) {
			UMLClass child = getClassFromName(name);
			child.setBackgroundColor("red");
			child.setStereotype(child.getStereotype() + "\\n\\<\\<adaptor\\>\\>\\n");
		}
		
		for (String name : parents) {
			UMLClass parent = getClassFromName(name);
			parent.setBackgroundColor("red");
			parent.setStereotype(parent.getStereotype() + "\\n\\<\\<target\\>\\>\\n");
		}
		
		for (String name : fields) {
			UMLClass field = getClassFromName(name);
			field.setBackgroundColor("red");
			field.setStereotype(field.getStereotype() + "\\n\\<\\<adaptee\\>\\>\\n");
		}
		
		for (UMLArrow arrow : associations) {
			arrow.setMiddleLabel("\\n\\<\\<adapts\\>\\>");
		}
	}
	
	private UMLClass getClassFromName(String name) {
		for (UMLClass current : this.classList) {
			if (current.getName().equals(name)) {
				return current;
			}
		}
		System.out.println("uh oh");
		return null;
	}

}
