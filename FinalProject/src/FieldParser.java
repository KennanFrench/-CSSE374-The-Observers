import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class FieldParser implements Parser {

	private FieldNode field;
	private UMLField uField;
	
	public FieldParser(FieldNode field) {
		this.field = field;
	}
	@Override
	public void parse() {
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

		this.uField = new UMLField(vis, field.name, fieldType);
	}
	public FieldNode getField() {
		return field;
	}
	public UMLField getuField() {
		return uField;
	}

}
