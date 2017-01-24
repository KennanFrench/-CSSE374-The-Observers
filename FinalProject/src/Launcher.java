import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

public class Launcher {
	/**
	 * Reads in a list of Java Classes and reverse engineers their design.
	 * 
	 * @param args
	 *            : the names of the classes, separated by spaces. For example:
	 *            java example.DesignParser java.lang.String
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	

	public static void main(String[] args) throws IOException {

		// DONE: Learn how to create separate Run Configurations so you can run
		// this code without changing the arguments each time.

		DesignParser parser = new DesignParser();
		
		parser.runParser(args);
		
		ArrayList<IUMLElement> design = (ArrayList<IUMLElement>) parser.getClassList().clone();
		design.addAll(parser.getArrowList());
		
		UMLFactory arrListConverter = new UMLFactory();
		ArrayList<UMLClass> classList = arrListConverter.createClassList(parser.getClassList());
		ArrayList<UMLArrow> arrowList = arrListConverter.createArrowList(parser.getArrowList());
		ArrayList<AbstractDetector> detectors = new ArrayList<AbstractDetector>();
//		detectors.add(new TestDetector(classList, arrowList));
		detectors.add(new SingletonDetector(classList, arrowList));
		DesignDetector detector = new DesignDetector(detectors, parser.getClassList(), parser.getArrowList());
		detector.runDetectors();
		
		DesignConverter converter = new DesignConverter(design, parser.getRunVis(), "ILoveThisProject");
		converter.convert();
		
		FileRunner fr = new FileRunner();
		fr.run(converter, "test.gv");

		PictureRunner picR = new PictureRunner();
		picR.run(converter, "test.gv");
		
		PrintRunner pr = new PrintRunner();
		pr.run(converter, "test.gv");
		
		/*for (UMLClass uClass : parser.classList) {
			ClassConverter converter = new ClassConverter(uClass, Visibility.PRIVATE);
			converter.convert();
		}
		*/
		// Give uml elements (in constructor? or in convert? 

			// ASM's ClassReader does the heavy lifting of parsing the compiled
			// Java class.
			// DONE: verify you have your JavaDocs set up so Eclipse can load
			// ASM's JavaDocs and tell you what this is.
			//ClassReader reader = new ClassReader(className);

			// There are NO ASM ClassVisitors, MethodVisitors, or FieldVisitors
			// here.
			// These ASM classes are dead to you. Do NOT use them at any point
			// during the term.

			// ClassNode contains all of the data about a parsed class
			// Do NOT subclass ClassNode.
			// Do NOT override any methods starting with the word "visit";
			// these methods are dead to you.
			//ClassNode classNode = new ClassNode();
			
			// Tell the Reader to parse the specified class and store its data
			// in our ClassNode.
			// EXPAND_FRAMES means: I want my code to work. Always pass this.
			//reader.accept(classNode, ClassReader.EXPAND_FRAMES);

			// Now we can navigate the classNode and look for things we are
			// interested in.
			//UMLClass uClass = new UMLClass();
			//ClassParser cParser = new ClassParser();
			//cParser.parse(classNode);
			
			//printClass(classNode);

			//printFields(classNode);

			//printMethods(classNode);
			
			// TODO: Use GOOD DESIGN to parse the classes of interest and store
			// them.
		}
}
