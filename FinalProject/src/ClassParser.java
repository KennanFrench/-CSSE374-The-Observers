import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class ClassParser implements Parser {
	
	private ClassNode node;
	private UMLClass uClass;
	private ArrayList<UMLElement> arrows;
	
	public ClassParser(ClassNode node) {
		this.node = node;
		this.arrows = new ArrayList<UMLElement>();
	}

	@Override
	public void parse() {
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
			parser.parse();
			fields.add(parser.getuField());
		}

		for (MethodNode method : methodNodes) {
			MethodParser parser = new MethodParser(method);
			parser.parse();
			methods.add(parser.getuMethod());
		}
		
		
		this.uClass = new UMLClass(name, category, fields, methods);
		
		// Create UMLArrows
		if (this.node.superName != null)
		{
			arrows.add(new UMLArrow(this.uClass.getName(), this.node.superName, HeadType.CLOSED, LineType.SOLID));
		}
		
		for (int i = 0; i < this.node.interfaces.size(); i++) {
			arrows.add(new UMLArrow(this.uClass.getName(), this.node.interfaces.get(i) +"",  HeadType.CLOSED, LineType.DASHED));
		}
	}

	public ClassNode getNode() {
		return node;
	}

	public UMLClass getuClass() {
		return uClass;
	}

	public ArrayList<UMLElement> getArrows() {
		return arrows;
	}

}
