public class ConverterFactory {

	public Converter createConverter(UMLElement element, Visibility runViz) {
		if (element instanceof UMLClass) {
			return new ClassConverter((UMLClass) element, runViz);
		} else if (element instanceof UMLArrow) {
			return new ArrowConverter((UMLArrow) element, runViz);
		}
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid UML type");
		}
		return null;
	}
}
