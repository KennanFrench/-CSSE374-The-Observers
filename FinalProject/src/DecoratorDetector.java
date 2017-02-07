import java.util.ArrayList;
import java.util.HashMap;

public class DecoratorDetector extends AbstractDetector {

	public DecoratorDetector(ArrayList<UMLClass> classes, ArrayList<UMLArrow> arrows) {
		super(classes, arrows);
	}

	@Override
	public void detectPattern() {
		ArrayList<UMLClass> abstractClasses = new ArrayList<>();
		HashMap<String, ArrayList<UMLClass>> parents = new HashMap<String, ArrayList<UMLClass>>();
		HashMap<String, ArrayList<UMLClass>> children = new HashMap<String, ArrayList<UMLClass>>();
		
		UMLFactory uFact = new UMLFactory();

		for (UMLClass current : this.classList) {
//			if (current.getCategory() == Category.ABSTRACT) {
				abstractClasses.add(current);
//			}
		}
		
		for (UMLArrow arrow : this.arrowList) {
			if (arrow.getLineType() == LineType.SOLID && arrow.getHeadType() == HeadType.CLOSED) {
				for (UMLClass current : abstractClasses) {
					if (current.getName().equals(arrow.getEnd())) {
						if (!children.containsKey(arrow.getEnd())) {
							ArrayList<UMLClass> specificChildren = new ArrayList<>();
							UMLClass child = uFact.getClassFromName(arrow.getStart(), this.classList);
							specificChildren.add(child);
							children.put(arrow.getEnd(), specificChildren);
						} else {
							ArrayList<UMLClass> specificChildren = children.get(arrow.getEnd());
							UMLClass child = uFact.getClassFromName(arrow.getStart(), this.classList);
							specificChildren.add(child);
							children.put(arrow.getEnd(), specificChildren);
						}
					}
				}
			}
		}
		
		for (UMLArrow arrow : this.arrowList) {
			if (arrow.getHeadType() == HeadType.CLOSED && arrow.getLineType() != LineType.DOTTED) {
				for (UMLClass abstractClass : abstractClasses) {
					if (arrow.getStart().equals(abstractClass.getName())) {
						UMLClass parent = uFact.getClassFromName(arrow.getEnd(), this.classList);
						if (!parents.containsKey(abstractClass.getName())) {
							ArrayList<UMLClass> specificParents = new ArrayList<>();
							specificParents.add(parent);
							parents.put(arrow.getStart(), specificParents);
						}  else {
							ArrayList<UMLClass> specificParents = parents.get(arrow.getStart());
							specificParents.add(parent);
							parents.put(arrow.getStart(), specificParents);
						}
					}
				}
			}
		}
		
		int i = 0;
		int size = abstractClasses.size();
		while (i < size) {
			if (!children.containsKey(abstractClasses.get(i).getName()) || !parents.containsKey(abstractClasses.get(i).getName())) {
				abstractClasses.remove(i);
				i--;
				size--;
			}
			i++;
		}
		
		for (UMLClass abstractClass : abstractClasses) {
			ArrayList<String> supers = getSupersWithFields(abstractClass, parents.get(abstractClass.getName()));
			ArrayList<ArrayList<String>> childrenAndSupers = getChildrenWithFields(children.get(abstractClass.getName()), parents.get(abstractClass.getName()));
			if (!supers.isEmpty()) {
				abstractClass.setBackgroundColor("green");
				abstractClass.setStereotype(abstractClass.getStereotype() + "\\n\\<\\<decorator\\>\\>\\n");
				for (String superName : supers) {
					UMLClass parent = uFact.getClassFromName(superName, this.classList);
					parent.setBackgroundColor("green");
					parent.setStereotype(parent.getStereotype() + "\\n\\<\\<component\\>\\>\\n");
					if (parent.getCategory() != Category.INTERFACE) {
						ArrayList<UMLMethod> totalMethods = new ArrayList<>();
						totalMethods.addAll(abstractClass.getMethods());
						ArrayList<UMLClass> specificChildren = children.get(abstractClass.getName());
						for (UMLClass child : specificChildren) {
							for (UMLMethod childMethod : child.getMethods()) {
								boolean sameName = false;
								i = 0;
								size = totalMethods.size();
								while (i < size) {
									if (childMethod.getName().equals(totalMethods.get(i).getName())) {
										sameName = true;
										boolean sameParams = true;
										int sameTypeCounter = 0;
										for (UMLParam abstractParam : totalMethods.get(i).getParameters()) {
											for (UMLParam childParam : childMethod.getParameters()) {
												if (abstractParam.getType().equals(childParam.getType())) {
													sameTypeCounter++;
												}
											}
										}
										sameParams = (sameTypeCounter == totalMethods.get(i).getParameters().size());
										if (!sameParams) {
											totalMethods.add(childMethod);
											size++;
										}
									}
									i++;
								}
								if (!sameName) {
									totalMethods.add(childMethod);
								}
							}
						}
						
						int overrides = 0;
						for (UMLMethod method : totalMethods) {
							for (UMLMethod parentMethod : parent.getMethods()) {
								if (method.getName().equals(parentMethod.getName())) {
									boolean sameParams = true;
									for (UMLParam childParam : method.getParameters()) {
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
						if (overrides < parent.getMethods().size()) {
							abstractClass.setStereotype(abstractClass.getStereotype() + "\\<\\<bad\\>\\>\\n");
						}
					}
				}
				for (UMLClass child : children.get(abstractClass.getName())) {
					child.setBackgroundColor("green");
					if (!child.getStereotype().contains("decorator")) {
						child.setStereotype(child.getStereotype() + "\\n\\<\\<decorator\\>\\>\\n");
					}
				}
				for (UMLArrow arrow : this.arrowList) {
					if (arrow.getStart().equals(abstractClass.getName()) && supers.contains(arrow.getEnd())) {
						if (arrow.getLineType() == LineType.SOLID && arrow.getHeadType() == HeadType.OPEN) {
							arrow.setMiddleLabel("\\<\\<decorates\\>\\>");
						}
					}
				}
			}
			if (!childrenAndSupers.get(0).isEmpty()) {
				abstractClass.setBackgroundColor("green");
				if (!abstractClass.getStereotype().contains("decorator")) {
					abstractClass.setStereotype(abstractClass.getStereotype() + "\\n\\<\\<decorator\\>\\>\\n");
				}
				for (String child : childrenAndSupers.get(0)) {
					UMLClass childClass = uFact.getClassFromName(child, this.classList);
					childClass.setBackgroundColor("green");
					if (!childClass.getStereotype().contains("decorator")) {
						childClass.setStereotype(childClass.getStereotype() + "\\n\\<\\<decorator\\>\\>\\n");
					}
				}
				for (String parent : childrenAndSupers.get(1)) {
					UMLClass parentClass = uFact.getClassFromName(parent, this.classList);
					if (parentClass.getCategory() != Category.INTERFACE) {
						ArrayList<UMLMethod> totalMethods = new ArrayList<>();
						totalMethods.addAll(abstractClass.getMethods());
						ArrayList<UMLClass> specificChildren = children.get(abstractClass.getName());
						for (UMLClass child : specificChildren) {
							for (UMLMethod childMethod : child.getMethods()) {
								boolean sameName = false;
								i = 0;
								size = totalMethods.size();
								while (i < size) {
									if (childMethod.getName().equals(totalMethods.get(i).getName())) {
										sameName = true;
										boolean sameParams = true;
										int sameTypeCounter = 0;
										for (UMLParam abstractParam : totalMethods.get(i).getParameters()) {
											for (UMLParam childParam : childMethod.getParameters()) {
												if (abstractParam.getType().equals(childParam.getType())) {
													sameTypeCounter++;
												}
											}
										}
										sameParams = (sameTypeCounter == totalMethods.get(i).getParameters().size());
										if (!sameParams) {
											totalMethods.add(childMethod);
											size++;
										}
									}
									i++;
								}
								if (!sameName) {
									totalMethods.add(childMethod);
								}
							}
						}
						
						int overrides = 0;
						for (UMLMethod method : totalMethods) {
							for (UMLMethod parentMethod : parentClass.getMethods()) {
								if (method.getName().equals(parentMethod.getName())) {
									boolean sameParams = true;
									for (UMLParam childParam : method.getParameters()) {
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
						if (overrides < parentClass.getMethods().size()) {
							abstractClass.setStereotype(abstractClass.getStereotype() + "\\<\\<bad\\>\\>\\n");
						}
					}
					parentClass.setBackgroundColor("green");
					if (!parentClass.getStereotype().contains("component")) {
						parentClass.setStereotype(parentClass.getStereotype() + "\\n\\<\\<component\\>\\>\\n");
					}
				}
				for (UMLArrow arrow : this.arrowList) {
					if (childrenAndSupers.get(0).contains(arrow.getStart()) && childrenAndSupers.get(1).contains(arrow.getEnd())) {
						if (arrow.getLineType() == LineType.SOLID && arrow.getHeadType() == HeadType.OPEN) {
							arrow.setLabel("\\<\\<decorates\\>\\>");
						}
					}
				}
			}
		}
	}
	
	
	private ArrayList<String> getSupersWithFields(UMLClass abstractClass, ArrayList<UMLClass> parents) {
		ArrayList<String> supers = new ArrayList<>();
		ArrayList<String> parentNames = new ArrayList<>();

		for (UMLClass parent : parents) {
			parentNames.add(parent.getName());
		}
		
		for (UMLField field : abstractClass.getFields()) {
			String nice = ClassNameHandler.getNiceFromDot(field.getType());
//			String nice = field.getType();
			if (parentNames.contains(nice)) {
				supers.add(nice);
			}
		}
		
		return supers;
	}
	
	private ArrayList<ArrayList<String>> getChildrenWithFields(ArrayList<UMLClass> children, ArrayList<UMLClass> parents) {
		ArrayList<ArrayList<String>> childrenAndParents = new ArrayList<>();
		ArrayList<String> childrenWithFields = new ArrayList<>();
		ArrayList<String> parentsToHighlight = new ArrayList<>();
		ArrayList<String> parentNames = new ArrayList<>();
		
		for (UMLClass parent : parents) {
			parentNames.add(parent.getName());
		}
		
		for (UMLClass child : children) {
			for (UMLField field : child.getFields()) {
				String nice = ClassNameHandler.getNiceFromDot(field.getType());
//				String nice = field.getType();
				if (parentNames.contains(nice)) {
					parentsToHighlight.add(nice);
					childrenWithFields.add(child.getName());
				}
			}
		}
		
		childrenAndParents.add(childrenWithFields);
		childrenAndParents.add(parentsToHighlight);
		
		return childrenAndParents;
	}
}
