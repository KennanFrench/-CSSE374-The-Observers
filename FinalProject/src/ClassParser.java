import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
			ArrayList<String> collectionClasses = new ArrayList<String>();
			if (field.signature != null)
				collectionClasses = this.getCollectionClasses(field.signature);
			collectionClasses = getCleanList(collectionClasses);
			uClassList.addAll(collectionClasses);
			
			for (int i = 0; i < collectionClasses.size(); i++) {
				if (i != 0) {
					String collClass = collectionClasses.get(i);
					this.arrows.add(new UMLArrow(" 1..* ", ClassNameHandler.getNiceFromDot(name), ClassNameHandler.getNiceFromDot(collClass), HeadType.OPEN, LineType.SOLID));
				}

			}
			
			
			FieldParser parser = new FieldParser(field);
			parser.parse();
			UMLField uField = parser.getuField();
			fields.add(uField);
			uClassList.addAll(parser.getuClassList());
			
			//NOTE: primitives suck, 
			if (name != null && !name.equals("") && uField!= null && uField.getType() != null && !uField.getType().equals("")) {
				this.arrows.add(new UMLArrow(name, uField.getType(), HeadType.OPEN, LineType.SOLID));
			}
		}

		for (MethodNode method : methodNodes) {
			String returnType =  ClassNameHandler.getClassName(Type.getReturnType(method.desc) +"");
			ArrayList<String> collectionClasses = new ArrayList<String>();
			if (method.signature != null && !returnType.equals(""))
				collectionClasses = this.getCollectionClasses(method.signature);
			collectionClasses = getCleanList(collectionClasses);
			uClassList.addAll(collectionClasses);
			
			for (int i = 0; i < collectionClasses.size(); i++) {
				if (i != 0) {
					String collClass = collectionClasses.get(i);
					boolean addArrow = false;
					for (UMLElement arrow : this.arrows) {
						// Does this work?
						if (!(((UMLArrow) arrow).getStart().equals(name) && ((UMLArrow) arrow).getEnd().equals(collClass) && ((UMLArrow) arrow).getHeadType().equals(HeadType.OPEN) && ((UMLArrow) arrow).getLineType().equals(LineType.SOLID))) {
							addArrow = true;
						}
					}
					if (addArrow)
						this.arrows.add(new UMLArrow(" 1..* ", ClassNameHandler.getNiceFromDot(name), ClassNameHandler.getNiceFromDot(collClass), HeadType.OPEN, LineType.DASHED));
				}
			}
			
			
			MethodParser parser = new MethodParser(method);
			parser.parse();
			UMLMethod uMethod = parser.getuMethod();
			methods.add(uMethod);

			ArrayList<String> parserClassList = parser.getuClassList();
			uClassList.addAll(parserClassList);
			
			for (String className : parserClassList) {
				boolean addArrow = true;
				//boolean bidirectional = false;
				for (UMLElement arrow : this.arrows) {
					// Does this work?

					if (((UMLArrow) arrow).getStart().equals(name) && ((UMLArrow) arrow).getEnd().equals(className) && ((UMLArrow) arrow).getHeadType().equals(HeadType.OPEN) && ((UMLArrow) arrow).getLineType().equals(LineType.SOLID)) {
						addArrow = false;
					}
					/*if (((UMLArrow) arrow).getStart().equals(className) && ((UMLArrow) arrow).getEnd().equals(name) && ((UMLArrow) arrow).getHeadType().equals(HeadType.OPEN) && ((UMLArrow) arrow).getLineType().equals(LineType.DASHED)) {
						bidirectional = true;
					}*/
					
				}
				if (addArrow) {
					/*if (bidirectional) {
						this.arrows.add(new UMLArrow("", name, ClassNameHandler.getNiceFromDot(className), HeadType.OPEN, LineType.DASHED, true));
					} else {*/
						this.arrows.add(new UMLArrow(name, ClassNameHandler.getNiceFromDot(className), HeadType.OPEN, LineType.DASHED));
					//}
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

	public ArrayList<String> getCollectionClasses(String signature){
		if (signature.contains("ObjectR") && !signature.contains("URL") && !signature.contains("CRL"))
			System.out.println("break here");

		ArrayList<String> collectionClasses = new ArrayList<String>();
		if(signature.equals(""))
			return collectionClasses;
		
		int indexSemi;
		// base cases
		if (!signature.contains("<")) {
			indexSemi = signature.indexOf(';');
			if(indexSemi != signature.length()-1) {
				int closeBracket =  signature.indexOf('>');
				// Remove errors, no closing bracket?
				if (indexSemi + 1 > closeBracket)
					return collectionClasses;
				
				collectionClasses.addAll(getInnerClasses(signature.substring(0, closeBracket)));
				collectionClasses.addAll(getCollectionClasses(signature.substring(closeBracket+1)));
				return collectionClasses;
			}
			collectionClasses.add(signature);
			return collectionClasses;
		}
		
		int openBracketIndex = signature.indexOf("<");
		int closeBracketIndex = signature.indexOf(">");
		if (openBracketIndex < closeBracketIndex) {
			String current = signature.substring(0, openBracketIndex);
			if (current.contains(";"))
			{
				collectionClasses.addAll(getInnerClasses(current));
			}
			else
				collectionClasses.add(current);

			collectionClasses.addAll(getCollectionClasses(signature.substring(openBracketIndex+1, closeBracketIndex+1)));
		}
		else {
			System.out.println("break here");
		}
		collectionClasses.addAll(getCollectionClasses(signature.substring(closeBracketIndex+1)));

		if (openBracketIndex+1 > closeBracketIndex+1)
			System.out.println("break here");

		return collectionClasses;
	}
	
	private ArrayList<String> getInnerClasses(String current) {
		ArrayList<String> toReturn = new ArrayList<String>(Arrays.asList(current.split(";")));
		return toReturn;
	}

	public ArrayList<String> getCleanList(ArrayList<String> dirtyList) {
		ArrayList<String> cleanList = new ArrayList<String>();
		
		for (String dirty : dirtyList) {
			dirty = dirty.replaceAll(";", "");
			dirty = dirty.replaceAll("<", "");
			dirty = dirty.replaceAll(">", "");
			dirty = dirty.replaceAll(":", "");
			dirty = dirty.replaceAll("-", "");
			dirty = dirty.replaceAll("\\+", "");
			dirty = dirty.replaceAll("\\*", "");

			int openIndex = dirty.indexOf('(');
			int closeIndex = dirty.indexOf(')');
			
			if (openIndex < 0 || closeIndex < 0)
				dirty = dirty.replaceAll("[()]","");
			else {
				StringBuffer dirtyB = new StringBuffer(dirty);
				dirtyB.delete(openIndex, closeIndex+1);
				dirty = dirtyB.toString();
			}

				

			if(!dirty.equals("")) {
				String toAdd = ClassNameHandler.removeStart(ClassNameHandler.removeEnd(ClassNameHandler.getDotName(dirty)));
				if(!toAdd.equals(""))
					cleanList.add(toAdd);
			}
		}
		
		return cleanList;
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
