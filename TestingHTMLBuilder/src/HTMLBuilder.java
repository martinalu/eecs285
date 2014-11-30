import java.util.ArrayList;
import java.util.Map;

/**
 * This is what we're gonna do.
 * 
 * body is the root element. It's ID is 0 and is initialized by default. Each
 * Element has a data structure of child Elements. The HTML string is
 * constructed by traversing through these data structures in order. The
 * children structures form a tree, and the construction is a depth first tree
 * traversal.
 * 
 * 
 * @author theProfessional
 *
 */
public class HTMLBuilder {
    public static final String HTML_LOCATION = "file://localhost/Users/theProfessional/Documents/gitRepos/eecs285/resources/demo_website/index.html";
    private static int id_counter = 0;

    // TODO: A vector is not gonna work. Use a map
    // of ID-Element pairs.

    // Master data structure of all Elements.
    // ID -> Element.
    private static Map<Integer, Element> elements;

    private class Element {
	public final int ID;
	public ArrayList<Integer> childrenIDs;

	public int parentID;
	public String type;
	public String content;

	public Element() {
	    ID = id_counter++;
	    parentID = 0;
	    type = "div";

	    elements.get(parentID).childrenIDs.add(ID);
	}

	public Element(int inParentID, String inType, String inContent) {
	    ID = id_counter++;

	    parentID = inParentID;
	    type = inType;
	    content = inContent;

	    // Updates parent element's list of children.
	    elements.get(parentID).childrenIDs.add(ID);
	}
    }

    // TODO: HTMLBuilder

    public HTMLBuilder() {
	Element body = new Element();
    }

    public HTMLBuilder(String indexHTMLPath) {
	Element body = new Element();
    }

    // TODO: Update these functions to coincide with the data structure setup.
    public void insertElement(int inParentID, String inType, String inContent) {
	// What needs to happen when I insert an element?

	// The element needs to be created.
	Element newElement = new Element(inParentID, inType, inContent);
	// We need to set the childElts array of the parent.
	elements.get(inParentID).childrenIDs.add(newElement.ID);

	elements.put(inParentID, newElement);
    }
}
