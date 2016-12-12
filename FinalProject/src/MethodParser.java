import java.util.ArrayList;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class MethodParser implements Parser {

	MethodNode method;
	public MethodParser(MethodNode method) {
		this.method = method;
	}

	@Override
	public UMLMethod parse() {
		String[] fullMethodType;
		String methodType;
		String name = this.method.name;
		ArrayList<UMLParam> parameters = new ArrayList<UMLParam>();

		Visibility vis;
		if ((method.access & Opcodes.ACC_PUBLIC) > 0) 
			 vis = Visibility.PUBLIC;
		else if ((method.access & Opcodes.ACC_PROTECTED) > 0)
			 vis = Visibility.PROTECTED;
		else 
			 vis = Visibility.PRIVATE;

		boolean isAbstract;
		if ((method.access & Opcodes.ACC_ABSTRACT) > 0)
			isAbstract = true;
		else
			isAbstract = false;
		
		fullMethodType =  (Type.getType(method.desc) + "").split(".");
		methodType = fullMethodType[fullMethodType.length - 1];
		
		for (Type argType : Type.getArgumentTypes(method.desc)) {
			parameters.add(new UMLParam("", argType.getClassName())); //TODO: Figure out how to get method variable names
		}
		
		return new UMLMethod(name, vis, isAbstract, methodType, parameters);
	}

}
