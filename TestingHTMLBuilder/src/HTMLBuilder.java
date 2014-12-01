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
    // TODO: Move these to utils.
    public static final String HTML_LOAD_LOCATION = "file:///Users/theProfessional/Documents/gitRepos/eecs285/resources/demo_website/index.html";
    public static final String HTML_SAVE_LOCATION = "/Users/theProfessional/Documents/gitRepos/eecs285/resources/demo_website/index.html";
    private static int id_counter = 0;
    public String generatedHTML = "";

    // This element will always be the parent element used to append a list onto
    // the end of us.
    public Element runningParent;

    // Last element inserted into the structure.
    public Element newestElement;

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

	public String htmlClass = "";

	// This is the string that will be used write the style options to the
	// element. EX: <p style="font-family: User Selected Font Here"></p>
	public String style = "";

	public String AddOpeningTag() {
	    String result = "<" + type;
	    result += style;
	    result += htmlClass;
	    if (selfTerminating()) {
		result += "/>";
		return result;
	    }
	    result += ">";
	    result += content;
	    if (type == "div" || type == "span") {
		return result;
	    }
	    return result += "</" + type + ">";
	}

	public String AddClosingTag() {
	    if (selfTerminating() || !(type == "div" || type == "span")) {
		return "";
	    }
	    return "</" + type + ">";
	}

	private boolean selfTerminating() {
	    return (type == "img" || type == "link" || type == "widget");
	}

	// Default Constructor produces ROOT element (body)
	public Element() {
	    ID = 0;
	    id_counter++;
	    parentID = -1;

	    type = "body";
	}

	public Element(int inParentID, String inType, String inContent,
		String inStyle, String inHtmlClass) {
	    ID = id_counter++;

	    parentID = inParentID;
	    type = inType;
	    content = inContent;

	    // Because the "style" varaiable has a prefix that must be kept
	    // constant,
	    if (inStyle != "") {
		style = String.format(" style=\"%s\"", inStyle);
	    }

	    if (inHtmlClass != "") {
		htmlClass = String.format(" class=\"%s\" ", inHtmlClass);
	    }

	    // Updates parent element's list of children.
	    elements.get(inParentID).childrenIDs.add(ID);
	}
	
	public String toString() {
	    return "<" + type + " ";
	}
    }

    public HTMLBuilder() {
	ROOT = new Element();
	try {
	    elements.put(ROOT.ID, ROOT);
	} catch (Exception e) {
	    System.out.println(ROOT.ID);
	}
    }

    public void insertAfter(int targetID, String inType, String inContent,
	    String inStyle, String inHtmlClass) {

	Element newElement = new Element(elements.get(targetID).parentID,
		inType, inContent, inStyle, inHtmlClass);

	elements.put(newElement.ID, newElement);
    }

    public void insertInto(int targetID, String inType, String inContent,
	    String inStyle, String inHtmlClass) {
	Element newElement = new Element(targetID, inType, inContent, inStyle,
		inHtmlClass);

	elements.put(newElement.ID, newElement);
    }

    public void insertElement(int inParentID, String inType, String inContent,
	    String inStyle, String inHtmlClass) {

	Element newElement = new Element(inParentID, inType, inContent,
		inStyle, inHtmlClass);

	elements.put(newElement.ID, newElement);
    }

    // TODO: Add insertElement method that allows for elements to be inserted
    // between two other elements with the same parent id.

    public void traverseAndBuild(Element ROOT) {
	// Based on type, construct element.
	System.out.println("Started Trav&Build : Element :" + ROOT.ID);
	if (ROOT.selfTerminating()) {
	    generatedHTML += buildWidget(ROOT);
	} else {
	    generatedHTML += ROOT.AddOpeningTag();
	    if (!ROOT.childrenIDs.isEmpty()) {
		for (int i = 0; i < ROOT.childrenIDs.size(); i++) {
		    traverseAndBuild(elements.get(ROOT.childrenIDs.get(i)));
		}
	    }
	    generatedHTML += ROOT.AddClosingTag();
	}
    }

    private String buildWidget(Element ROOT) {
	utils constants = new utils();
	return constants.widgets.get(ROOT.content);
    }

    public void build() {
	// Insert <head> HTML. This resets existing HTML.
	generatedHTML = utils.HEAD_HTML;

	// Next, we traverse the existing elements to construct them
	// sequentially. Starting with the root element, body.
	traverseAndBuild(ROOT);

	generatedHTML += "</html>";

	// We the write the changes to the file.
	try {
	    updateProjectFiles();
	} catch (IOException e) {
	    System.out.println("ERROR: Could not update project files.");
	    e.printStackTrace();
	}
    }

    private void updateProjectFiles() throws IOException {
	File htmlFile = new File(HTML_SAVE_LOCATION);
	// False overwrites the file. True appends to the end of the file.
	FileWriter fooWriter = new FileWriter(htmlFile, false);
	fooWriter.write(generatedHTML);
	System.out.println(generatedHTML);
	fooWriter.close();
    }

    public void recursiveRemove(Element elt) {
	// If the thing has children ...
	if (!elt.childrenIDs.isEmpty()) {
	    for (int childID : elt.childrenIDs) {
		recursiveRemove(elements.get(childID));
	    }
	}
	System.out.println("Removing Elt : " + elt.ID);
	// Remove elt from Parent Element childrenID list.
	for (int i = 0; i < elements.get(elt.parentID).childrenIDs.size(); i++) {
	    if (elements.get(elt.parentID).childrenIDs.get(i) == elt.ID) {
		elements.get(elt.parentID).childrenIDs.remove(i);
	    }
	}
	elements.remove(elt.ID);
    }

    public void updateElement(Element elt, String inType, String inStyle,
	    String inContent) {
	elt.type = inType;
	elt.style = String.format(" style=\"%s\"", inStyle);
	elt.content = inContent;
    }

    public Element getRoot() {
	return ROOT;
    }

    public Element getElement(int eltID) {
	return elements.get(eltID);
    }

    public ArrayList<Element> arrayListOfElements(Element ROOT) {
	ArrayList<Element> temp = new ArrayList<Element>();
	temp.add(ROOT);
	for (int i = 0; i < ROOT.childrenIDs.size(); i++) {
	    temp.addAll(arrayListOfElements(elements.get(ROOT.childrenIDs
		    .get(i))));
	}
	return temp;

    }

    public void createTemplateAlpha() {
	insertElement(ROOT.ID, "div", "", "height: 25px;", "container-fluid");
	insertElement(ROOT.ID, "div", "",
		"height: 300px; background-color: #FFAAAA;", "container");
	insertInto(ROOT.childrenIDs.get(1), "div", "", "", "row");
	insertInto(3, "div", "", "", "col-sm-4");
	insertInto(4, "h1", "A Template For You!", "", "");
	insertAfter(3, "div", "", "margin-top: 25px;", "col-md-8");
	// <h4>
	// This is where a short description of how great a person you are would
	// go.
	// </h4>
	// <h6>
	// If anyone thought that about you.
	// </h6>
	insertInto(
		5,
		"h4",
		"Descriptions of things! All kinds- any single thing you can think of!",
		"", "");
	insertAfter(6, "h6", "Updated only on Wednesdays.", "", "");
    }
}
