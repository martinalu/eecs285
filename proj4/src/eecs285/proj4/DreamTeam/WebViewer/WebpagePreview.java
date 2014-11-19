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
    public String testHTML = "<!DOCTYPE html> <html lang=\"en\"><head><meta charset=\"UTF-8\"><title>RAWRA</title></head><body><h1>EVERYTHING HAS CHANGED</h1><p>This is CRAZY.</p></body></html>";

    // Web View
    private JEditorPane website = new JEditorPane();

    public WebpagePreview() {

	try {
	    website.setPage(htmlLocation);
	} catch (Exception e) {
	    System.err.println("Failed to Load Page");
	    System.exit(ERROR);
	}
	website.setEditable(false);
	// add(website);

	// Web View Container
	JScrollPane websiteContainer = new JScrollPane(website);
	websiteContainer.setVisible(true);
	website.setPreferredSize(new Dimension(500, 400));
	add(websiteContainer);
    }

    // Create a method to "Refresh the Window"
    public void updatePage() throws IOException {
	File htmlFile = new File(altLocation);
	FileWriter fooWriter = new FileWriter(htmlFile, false); // true to
								// append
								// false to
								// overwrite.
	fooWriter.write(testHTML);
	fooWriter.close();

	Document doc = website.getDocument();
	doc.putProperty(Document.StreamDescriptionProperty, null);

	website.setPage(htmlLocation);

	System.out.println("Call recieved");

    }
}