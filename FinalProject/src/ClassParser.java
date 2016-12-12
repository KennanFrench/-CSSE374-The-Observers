import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class ClassParser implements Parser{

	@Override
	public void parse(ClassNode node) {
		String name;
		String[] fullName;
		Category category;
		ArrayList<UMLField> fields = new ArrayList<UMLField>();
		ArrayList<UMLMethod> methods;

		fullName = node.name.split("/");
		name = fullName[fullName.length-1];
		
		//System.out.println("public? "
		//		+ ((classNode.access & Opcodes.ACC_PUBLIC) > 0));
		//System.out.println("Extends: " + node.superName);
		//System.out.println("Implements: " + node.interfaces);
		if ((node.access & Opcodes.ACC_INTERFACE) > 0)
			category = Category.INTERFACE;
		else if ((node.access & Opcodes.ACC_ABSTRACT) > 0)
			category = Category.ABSTRACT;
		else
			category = Category.CLASS;
		
		List<FieldNode> fieldNodes = (List<FieldNode>) node.fields;
		
		
		for (FieldNode field : fieldNodes) {
			String[] fullFieldType;
			String fieldType;
			Visibility vis;
			
			fullFieldType =  (Type.getType(field.desc) + "").split("/");
			fieldType = fullFieldType[fullFieldType.length - 1];
			if ((field.access & Opcodes.ACC_PUBLIC) > 0) 
				 vis = Visibility.PUBLIC;
			else if ((field.access & Opcodes.ACC_PROTECTED) > 0)
				 vis = Visibility.PROTECTED;
			else 
				 vis = Visibility.PRIVATE;

			UMLField uField = new UMLField(vis, field.name, fieldType);
			fields.add(uField);
		}

		
		
		UMLClass uClass = new UMLClass(name, category, fields, methods);
		
	}

}
