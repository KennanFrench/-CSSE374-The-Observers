import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class ClassParser implements Parser {
	
	private ClassNode node;
	private ArrayList<String> uClassList;
	private ArrayList<UMLElement> arrows;
	private ArrayList<String> classList;
	private UMLClass uClass;
	
	public ClassParser(ClassNode node, ArrayList<String> classList) {
		this.node = node;
		this.arrows = new ArrayList<UMLElement>();
		this.classList = classList;
		this.uClassList = new ArrayList<String>();
		//for (int i = 0; i < classList.size(); i++) {
		//	this.classList.set(i, Launcher.getNiceName(classList.get(i)));
		//}
	}

	@Override
	public void parse() {
		String name;
		//String[] fullName;
		Category category;
		ArrayList<UMLField> fields = new ArrayList<UMLField>();
		ArrayList<UMLMethod> methods = new ArrayList<UMLMethod>();

		name = ClassNameHandler.getNiceFromSlash(this.node.name);
		
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
			UMLField uField = parser.getuField();
			fields.add(uField);
			uClassList.addAll(parser.getuClassList());
			
			//NOTE: primitives suck, 
			if (name != null && uField!= null && uField.getType() != null) {
				this.arrows.add(new UMLArrow(name, uField.getType(), HeadType.OPEN, LineType.SOLID));
			}
		}

		for (MethodNode method : methodNodes) {
			MethodParser parser = new MethodParser(method);
			parser.parse();
			UMLMethod uMethod = parser.getuMethod();
			methods.add(uMethod);

			ArrayList<String> parserClassList = parser.getuClassList();
			uClassList.addAll(parserClassList);
			
			for (String className : parserClassList) {
				boolean addArrow = true;
				for (UMLElement arrow : this.arrows) {
					if (((UMLArrow) arrow).getStart().equals(name) && ((UMLArrow) arrow).getEnd().equals(className) && ((UMLArrow) arrow).getHeadType().equals(HeadType.OPEN) && ((UMLArrow) arrow).getLineType().equals(LineType.SOLID)) {
						addArrow = false;
					}
				}
				if (addArrow) {
					this.arrows.add(new UMLArrow(name, ClassNameHandler.getNiceFromDot(className), HeadType.OPEN, LineType.DASHED));
				}
			}
		}
		
		this.uClass = new UMLClass(name, category, fields, methods);
		
		// Create UMLArrows
		String tempName = ClassNameHandler.getNiceFromSlash(this.node.superName);
		String dot = ClassNameHandler.getDotName(this.node.superName);
		
		// TODO HERE need to only add arrows (And below) if not in list
		if (this.node.superName != null && this.classList.contains(dot))
		{
			arrows.add(new UMLArrow(this.uClass.getName(), tempName, HeadType.CLOSED, LineType.SOLID));
		}
		
		for (int i = 0; i < this.node.interfaces.size(); i++) {
			String interName = this.node.interfaces.get(i) + "";
			tempName = ClassNameHandler.getNiceFromSlash(this.node.interfaces.get(i) + "");
			String dotName = ClassNameHandler.getDotName(interName);
			if (this.classList.contains(dotName)) {
				arrows.add(new UMLArrow(this.uClass.getName(), tempName, HeadType.CLOSED, LineType.DASHED));
			}
		}
	}

	public ClassNode getNode() {
		return node;
	}

	public ArrayList<String> getuClassList() {
		return uClassList;
	}
	
	public UMLElement getuClass() {
		return this.uClass;
	}

	public ArrayList<UMLElement> getArrows() {
		return arrows;
	}

}
