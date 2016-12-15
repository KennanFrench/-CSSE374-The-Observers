import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

// FIXME: everything about this class is completely terribly designed.
// If your code even remotely resembles this class, you will be sad.
public class DesignParser {
	
	private ArrayList<UMLElement> classList;
	private ArrayList<UMLElement> arrowList;
	private Visibility runVis;
	
	public DesignParser() {
		this.classList = new ArrayList<UMLElement>();
		this.arrowList = new ArrayList<UMLElement>();
	}
	
	public void runParser(String[] args) throws IOException {
		
		CommandLineParser clParser = new CommandLineParser(args);
		clParser.parse();
		ArrayList<String> classes = clParser.getClassList();
		this.runVis = clParser.getRunVis();
		
		//move this to a different method once we figure out how recursive to make it
		int size = classes.size();
		int j = 0;
		while(j < size) {
			String className = classes.get(j);
			//System.out.println(className);
			ClassReader reader = new ClassReader(className);
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);
			
			if (!classes.contains(classNode.superName) && classNode.superName != null) {
				classes.add(classNode.superName);
				size++;
			}
			for (int i = 0; i < classNode.interfaces.size(); i++) {
				if (!classes.contains((String) classNode.interfaces.get(i))) {
					classes.add((String) classNode.interfaces.get(i));
					size++;
				}
			}
			j++;
		}

		for (String className : classes) {
			ClassReader reader = new ClassReader(className);
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);
			System.out.println("Extends: " + classNode.superName);
			System.out.println("Implements: " + classNode.interfaces);

			ClassParser parser = new ClassParser(classNode);
			parser.parse();
			classList.add(parser.getuClass());
			arrowList.addAll(parser.getArrows());
		}
	}

	// FIXME: is it GOOD DESIGN to have a class where everything is static?
	private static void printClass(ClassNode classNode) {
		System.out.println("Class's JVM internal name: " + classNode.name);
		System.out.println("User-friendly name: "
				+ Type.getObjectType(classNode.name).getClassName());
		System.out.println("public? "
				+ ((classNode.access & Opcodes.ACC_PUBLIC) > 0));
		System.out.println("Extends: " + classNode.superName);
		System.out.println("Implements: " + classNode.interfaces);

	}

	private static void printFields(ClassNode classNode) {
		// Print all fields (note the cast; ASM doesn't store generic
		// data with its Lists)
		List<FieldNode> fields = (List<FieldNode>) classNode.fields;
		for (FieldNode field : fields) {
			System.out.println("	Field: " + field.name);
			System.out.println("	Internal JVM type: " + field.desc);
			System.out.println("	User-friendly type: "
					+ Type.getType(field.desc));
			// Query the access modifiers with the ACC_* constants.

			System.out.println("	public? "
					+ ((field.access & Opcodes.ACC_PUBLIC) > 0));
			// How do you tell if something has default access? (ie no
			// access modifiers?)

			System.out.println();
		}
	}

	private static void printMethods(ClassNode classNode) {
		List<MethodNode> methods = (List<MethodNode>) classNode.methods;
		for (MethodNode method : methods) {
			System.out.println("	Method: " + method.name);
			System.out
					.println("	Internal JVM method signature: " + method.desc);

			System.out.println("	Return type: "
					+ Type.getReturnType(method.desc).getClassName());

			System.out.println("	Args: ");
			for (Type argType : Type.getArgumentTypes(method.desc)) {
				System.out.println("		" + argType.getClassName());
				// FIXME: what is the argument's VARIABLE NAME?
			}

			// Query the access modifiers with the ACC_* constants.
			System.out.println("	public? "
					+ ((method.access & Opcodes.ACC_PUBLIC) > 0));
			System.out.println("	static? "
					+ ((method.access & Opcodes.ACC_STATIC) > 0));
			// How do you tell if something has default access? (ie no
			// access modifiers?)

			System.out.println();

			// Print the method's instructions
			printInstructions(method);
		}
	}

	private static void printInstructions(MethodNode methodNode) {
		InsnList instructions = methodNode.instructions;
		for (int i = 0; i < instructions.size(); i++) {

			// We don't know immediately what kind of instruction we have.
			AbstractInsnNode insn = instructions.get(i);

			// Now we have to cast the instruction to its correct type based on
			// the opCode.
			// FIXME: this code has POOR DESIGN.
			if (insn instanceof MethodInsnNode) {
				// A method call of some sort; what other useful fields does
				// this object have?
				MethodInsnNode methodCall = (MethodInsnNode) insn;
				System.out.println("		Call method: " + methodCall.owner + " "
						+ methodCall.name);
			} else if (insn instanceof VarInsnNode) {
				// Some some kind of variable *LOAD or *STORE operation.
				VarInsnNode varInsn = (VarInsnNode) insn;
				int opCode = varInsn.getOpcode();
				// See VarInsnNode.setOpcode for the list of possible values of
				// opCode. These are from a variable-related subset of Java
				// opcodes.
			}
			// There are others...
			// This list of direct known subclasses may be useful:
			// http://asm.ow2.org/asm50/javadoc/user/org/objectweb/asm/tree/AbstractInsnNode.html
		}
	}

	public ArrayList<UMLElement> getClassList() {
		return this.classList;
	}

	public ArrayList<UMLElement> getArrowList() {
		return this.arrowList;
	}

	public Visibility getRunVis() {
		return runVis;
	}
}
