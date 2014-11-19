package eecs285.proj4.DreamTeam.WebViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

// Lets get a gameplan down.
//	This class is going to extend a JPanel.
//	It's going to basically be an all-in-one
//	container for the website preview window.
//	It's placement will be determined by the
//	main GUI file. 

public class WebpagePreview extends JPanel {
    // This is the location of "template.html" on my machine. It WILL BE
    // DIFFERENT on your machine. If you want to know where it is, right click
    // the file in your system explorer (finder/windows explorer) and open with
    // your favorite browser. Copy the URL from the browser and replace it with
    // this string below.
    public String htmlLocation = "file:///Users/theProfessional/Documents/gitRepos/eecs285/resources/template.html";
    public String altLocation = "/Users/theProfessional/Documents/gitRepos/eecs285/resources/template.html";
    public String currentHTML = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>RAWRA</title></head><body><h1>THIS IS A HEADER</h1><p>This is part of a paragraph.</p></body></html>";

    // FOR DEBUGGING ONLY
    public static final String testHTML = "<!DOCTYPE html> <html lang=\"en\"><head><meta charset=\"UTF-8\"><title>RAWRA</title></head><body><h1>EVERYTHING HAS CHANGED</h1><p>This is CRAZY.</p></body></html>";

    private static final String BASE_HTML_FIRST_HALF = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Document</title></head><body>";
    private static final String BASE_HTML_SECOND_HALF = "</body></html>";

    private static String headerHTML;
    private static String paragraphHTML;

    // Web View
    private JEditorPane website = new JEditorPane();

    public WebpagePreview() {
	// Add Web View
	try {
	    website.setPage(htmlLocation);
	} catch (Exception e) {
	    System.err.println("Failed to Load Page");
	    System.exit(ERROR);
	}
	website.setEditable(false);

	// Web View Container
	JScrollPane websiteContainer = new JScrollPane(website);
	websiteContainer.setVisible(true);
	website.setPreferredSize(new Dimension(500, 400));
	add(websiteContainer);
    }

    /**
     * Refreshes the web view by modifying the
     * 
     * @throws IOException
     */
    private void refreshPage() throws IOException {
	
	File htmlFile = new File(altLocation);
	// False overwrites the file. True appends to the end of the file.
	FileWriter fooWriter = new FileWriter(htmlFile, false);
	fooWriter.write(currentHTML);
	fooWriter.close();

	Document doc = website.getDocument();
	doc.putProperty(Document.StreamDescriptionProperty, null);

	website.setPage(htmlLocation);

    }

    /**
     * Updates the string representing the header content. Helper function for
     * updatePage.
     * 
     * @param headerContent
     */
    public void updateHeader(String headerContent) {

	headerHTML = "<h1>" + headerContent + "</h1>";
	generateTemplate();

    }

    /**
     * Similar in form and function to updateHeader.
     * 
     * @param paragraphContent
     */
    public void updateParagraph(String paragraphContent) {

	paragraphHTML = "<p>" + paragraphContent + "</p>";
	generateTemplate();

    }

    /**
     * Assembles a string to write to the "template.html" file. Calls
     * "refreshPage" to reflect changes in WebpagePreview GUI.
     */
    private void generateTemplate() {

	String updatedHTML = "";

	updatedHTML += BASE_HTML_FIRST_HALF;
	updatedHTML += headerHTML;
	updatedHTML += paragraphHTML;
	updatedHTML += BASE_HTML_SECOND_HALF;

	currentHTML = updatedHTML;

	try {
	    refreshPage();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}