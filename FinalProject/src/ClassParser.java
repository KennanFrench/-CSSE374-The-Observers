import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class ClassParser implements Parser {
	
	ClassNode node;
	
	public ClassParser(ClassNode node) {
		this.node = node;
	}

	@Override
	public UMLClass parse() {
		String name;
		String[] fullName;
		Category category;
		ArrayList<UMLField> fields = new ArrayList<UMLField>();
		ArrayList<UMLMethod> methods = new ArrayList<UMLMethod>();

		fullName = this.node.name.split("/");
		name = fullName[fullName.length-1];
		
		//System.out.println("public? "
		//		+ ((classNode.access & Opcodes.ACC_PUBLIC) > 0));
		//System.out.println("Extends: " + this.node.superName);
		//System.out.println("Implements: " + this.node.interfaces);
		
		// Create UMLArrows
		//if (this.node.superName.equals(""))
		
		// Get category
		if ((this.node.access & Opcodes.ACC_INTERFACE) > 0)
			category = Category.INTERFACE;
		else if ((this.node.access & Opcodes.ACC_ABSTRACT) > 0)
			category = Category.ABSTRACT;
		else
			category = Category.CLASS;
		
		List<FieldNode> fieldNodes = this.node.fields;
		List<MethodNode> methodNodes = this.node.methods;
		
		for (FieldNode field : fieldNodes) {
			FieldParser parser = new FieldParser(field);
			fields.add(parser.parse());
		}

		for (MethodNode method : methodNodes) {
			MethodParser parser = new MethodParser(method);
			methods.add(parser.parse());
		}
		
		
		return new UMLClass(name, category, fields, methods);
		
	}

}
