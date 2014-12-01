import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    public static final String HTML_LOCATION = "/Users/theProfessional/Documents/gitRepos/eecs285/resources/demo_website/index.html";
    private static int id_counter = 0;
    public String generatedHTML = "";

    // TODO: A vector is not gonna work. Use a map
    // of ID-Element pairs.

    // Master data structure of all Elements.
    // ID -> Element.
    private static Map<Integer, Element> elements = new HashMap<Integer, Element>();

    // This will always initialize a body tag with the values found in the
    // default element constructor.
    private Element ROOT;

    public class Element {
	public final int ID;
	public ArrayList<Integer> childrenIDs = new ArrayList<Integer>();

	public int parentID;
	public String type = "";

	// Content is the stuff that goes inbetween the brackets.
	// EX: <p>ContentVar</p>
	public String content = "";

	// This is the string that will be used write the style options to the
	// element. EX: <p style="font-family: User Selected Font Here"></p>
	public String style = " style=\"\"";

	// TODO: What about img src? Or any non-style parameters?
	public String AddOpeningTag() {
	    String result = "<" + type;
	    // Format correctly so style is concatenated properly.
	    result += style;
	    result += ">";
	    result += content;
	    return result;
	}

	// TODO: What about self-terminating tags.
	public String AddClosingTag() {
	    return "</" + type + ">";
	}

	// Default Constructor produces ROOT element (body)
	public Element() {
	    ID = 0;
	    id_counter++;
	    parentID = -1;
	    type = "body";
	}

	public Element(int inParentID, String inType, String inContent,
		String inStyle) {
	    ID = id_counter++;

	    parentID = inParentID;
	    type = inType;
	    content = inContent;

	    // Because the "style" varaiable has a prefix that must be kept
	    // constant,
//	    style += inStyle;

	    // Updates parent element's list of children.
	    elements.get(inParentID).childrenIDs.add(ID);
	}
    }

    // TODO: HTMLBuilder Constructor
    public HTMLBuilder() {
	ROOT = new Element();
	try {
	    elements.put(ROOT.ID, ROOT);	    
	}
	catch (Exception e){
	    System.out.println(ROOT.ID);
	}
    }

    // TODO: Update these functions to coincide with the data structure setup.
    public void insertElement(int inParentID, String inType, String inContent,
	    String inStyle) {
	// What needs to happen when I insert an element?

	// The element needs to be created.
	Element newElement = new Element(inParentID, inType, inContent, inStyle);
	// We need to set the childElts array of the parent.
	elements.get(inParentID).childrenIDs.add(newElement.ID);

	elements.put(newElement.ID, newElement);
    }

    // TODO: What do we do about self terminating tags? (img, for instance)
    // Self terminating tags will always be leaf nodes with an empty string for
    // the "content" member variable .

    public void traverseAndBuild(Element ROOT) {
	// Based on type, construct element.
	generatedHTML += ROOT.AddOpeningTag();
	for (int childID : ROOT.childrenIDs) {
	    traverseAndBuild(elements.get(childID));
	}
	generatedHTML += ROOT.AddClosingTag();
    }

    public void build() {
	// Insert <head> HTML. This resets existing HTML.
	generatedHTML = utils.HEAD_HTML;

	// Next, we traverse the existing elements to construct them
	// sequentially. Starting with the root element, body.
	traverseAndBuild(ROOT);

	// We the write the changes to the file.
	try {
	    updateProjectFiles();
	} catch (IOException e) {
	    System.out.println("ERROR: Could not update project files.");
	    e.printStackTrace();
	}
    }

    private void updateProjectFiles() throws IOException {
	File htmlFile = new File(HTML_LOCATION);
	// False overwrites the file. True appends to the end of the file.
	FileWriter fooWriter = new FileWriter(htmlFile, false);
	fooWriter.write(generatedHTML);
	fooWriter.close();
    }

    public Element getRoot() {
	return ROOT;
    }
}
