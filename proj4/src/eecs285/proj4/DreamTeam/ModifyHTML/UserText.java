package eecs285.proj4.DreamTeam.ModifyHTML;

import javax.swing.JTextArea;
import javax.swing.text.html.HTMLDocument;

public class UserText {

	private HTMLDocument document;
	private String htmlText = "";
	private static String initialText = "<html>\n<head>\n<title>set the title here</title>\n</head>\n<body>\n Color and font test:\n <ul>\n  <li><font color=red>red</font></li>\n  <li><font color=blue>blue</font></li>\n  <li><font color=green>green</font></li>\n  <li><font size=-2>small</font></li>\n  <li><font size=+2>large</font></li>\n  <li><i>italic</i></li>\n  <li><b>bold</b></li>\n </ul>\n <br/>\n <hr/>\n</body>\n</html>";

	
	public UserText(JTextArea textArea)
	{
	   textArea.setText(initialText);
	}
	  
	public void setText(String newHTMLText)
	{
		this.htmlText = newHTMLText;
	}

}