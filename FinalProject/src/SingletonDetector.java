import java.util.ArrayList;

import org.objectweb.asm.Opcodes;

public class SingletonDetector extends AbstractDetector {

	public SingletonDetector(ArrayList<UMLClass> classes, ArrayList<UMLArrow> arrows) {
		super(classes, arrows);
	}

	@Override
	public void detectPattern() {
		for (UMLClass currentClass : this.classList) {
			if (isSingleton(currentClass)) {
				currentClass.setColor("blue");
				currentClass.setStereotype("\\n\\<\\<Singleton\\>\\>\\n");
			}
		}
	}
	
	private boolean isSingleton(UMLClass c) {
		boolean hasPrivateConstructor = false;
		boolean hasSingletonMethod = false;
		boolean hasStaticField = false;

		for (UMLMethod m : c.getMethods()) {
			if (m.getName().equals("<init>")) {
				hasPrivateConstructor = (m.getVisibility() == Visibility.PRIVATE);
			}
			if (ClassNameHandler.getNiceFromDot(m.getType()).equals(c.getName()) &&
					m.getVisibility() == Visibility.PUBLIC &&
					m.isStatic()) {
				hasSingletonMethod = true;
			}
		}
		
		for (UMLField f : c.getFields()) {
			if (f != null) {
				if (f.isStatic() && ClassNameHandler.getNiceFromDot(f.getType()).equals(c.getName())) {
					hasStaticField = true;
				}
			}
		}
		return hasPrivateConstructor && hasSingletonMethod && hasStaticField;
	}

}
