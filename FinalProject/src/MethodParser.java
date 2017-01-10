import java.util.ArrayList;
import java.util.Collection;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class MethodParser implements Parser {

	private MethodNode method;
	private UMLMethod uMethod;
	private ArrayList<String> uClassList;
	
	public MethodParser(MethodNode method) {
		this.method = method;
		this.uClassList = new ArrayList<String>();
	}

	@Override
	public void parse() {
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
		String temp =   (Type.getReturnType(method.desc).getClassName());
		fullMethodType =  (Type.getReturnType(method.desc).getClassName()).split("/");
		methodType = fullMethodType[fullMethodType.length - 1];
		this.uClassList.add(methodType);
		
		Type[] types = Type.getArgumentTypes(method.desc);
		
		for (int i = 0; i < types.length; i++) {
			parameters.add(new UMLParam("arg" + i, types[i].getClassName()));
			this.uClassList.add(types[i].getClassName());
		}
		
		this.uMethod = new UMLMethod(name, vis, isAbstract, methodType, parameters);
	}

	public MethodNode getMethod() {
		return method;
	}

	public UMLMethod getuMethod() {
		return uMethod;
	}

	public ArrayList<String> getuClassList() {
		return this.uClassList;
	}

}
