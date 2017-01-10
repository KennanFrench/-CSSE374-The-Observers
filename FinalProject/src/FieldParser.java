import java.util.ArrayList;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class FieldParser implements Parser {

	private FieldNode field;
	private UMLField uField;
	private ArrayList<String> uClassList;
	
	public FieldParser(FieldNode field) {
		this.field = field;
		this.uClassList = new ArrayList<String>();
	}
	@Override
	public void parse() {
		String[] fullFieldTypeList;
		String fieldType;
		String fullFieldType;
		Visibility vis;
		
		fullFieldType = NameChanger.removeStart(Type.getType(field.desc) + "");
		fullFieldTypeList =  fullFieldType.split("/");
		fieldType = fullFieldTypeList[fullFieldTypeList.length - 1];
		if ((field.access & Opcodes.ACC_PUBLIC) > 0) 
			 vis = Visibility.PUBLIC;
		else if ((field.access & Opcodes.ACC_PROTECTED) > 0)
			 vis = Visibility.PROTECTED;
		else 
			 vis = Visibility.PRIVATE;
		
		if (!fieldType.equals("") && field != null) {
			this.uField = new UMLField(vis, field.name, fieldType);
			//only add to classlist if it starts with L; change this when namechange.removestart gets changed
			this.uClassList.add(NameChanger.removeEnd(NameChanger.getDotName(fullFieldType)));
		}
		
	}
	public FieldNode getField() {
		return field;
	}
	public UMLField getuField() {
		return uField;
	}
	
	public ArrayList<String> getuClassList() {
		return this.uClassList;
	}

}
