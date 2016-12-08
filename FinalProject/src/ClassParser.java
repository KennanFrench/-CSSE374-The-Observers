import java.util.ArrayList;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class ClassParser implements Parser{

	@Override
	public void parse(ClassNode node) {
		String name;
		String[] fullName;
		Category category;
		ArrayList<UMLField> fields;
		ArrayList<UMLMethod> methods;
		fullName = node.name.split("/");
		name = fullName[fullName.length-1];
		
		//System.out.println("public? "
		//		+ ((classNode.access & Opcodes.ACC_PUBLIC) > 0));
		System.out.println("Extends: " + classNode.superName);
		System.out.println("Implements: " + classNode.interfaces);
		
		
		UMLClass uClass = new UMLClass();
		
	}

}
