/**
 * This is what we're gonna do.
 * 
 * How will people interface with this thing? What are the things that the
 * application needs to do?
 * 
 * @author theProfessional
 *
 */
public class HTMLBuilder {
    public static final String HTML_LOCATION = "file://localhost/Users/theProfessional/Documents/gitRepos/eecs285/resources/demo_website/index.html";
    private static int id_counter = 0;

    private class Element {
	public final int ID;

	public int parentID;
	public int[] childrenIDs;

	public String type;

	public Element() {
	    ID = id_counter++;
	}
    }

    public int createElement(int parentID) {

	Element newElement = new Element();
	return newElement.ID;
    }
}
