package eecs285.proj4.DreamTeam.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import eecs285.proj4.DreamTeam.WebViewer.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLEditorKit.InsertHTMLTextAction;
import javax.swing.text.Element;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import eecs285.proj4.DreamTeam.ModifyHTML.*;

public class main_window extends JFrame {

    private JPanel contentPane;
    private JTextField headerTextField;
    private JTextArea Paragraph;
    private HTMLDocument document;
    private static HTML.Tag tag;
    private static JEditorPane output_area = new JEditorPane();
    private static HTMLEditorKit kit = new HTMLEditorKit();
    private static Document doc = (HTMLDocument) kit.createDefaultDocument();
    private static Element element;
    private static WebpagePreview website = new WebpagePreview();

    private static String initialText = "<html>\n<head>\n<title>set the title here</title>\n"
	    + "</head>\n<body>\n <h1>Html Document test: First header</h1>\n <ul>\n  <li><font color=red>red</font>"
	    + "</li>\n  <li><font color=green>green</font>"
	    + "</li>\n  <li><font size=-2>small</font></li>\n "
	    + "  <li><i>italic</i></li>\n  <li><b>bold</b></li>\n </ul>\n <br/>\n"
	    + "<p color= orange>This is a paragraph</p>\n"
	    + " <hr/>\n</body>\n</html>";

    /**
     * Create the frame.
     */
    public main_window() {

	super("This is the prototype");

	setJMenuBar(makeMenuBar());

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 689, 524);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JLabel label = new JLabel("***Preview stuff***");
	label.setBounds(472, -122, 61, 268);
	contentPane.add(label);

	// New HTML Rendering Code.
	website.setBounds(325, 26, 500, 700);
	contentPane.add(website);

	// Old HTML Rendering Code.

	// output_area.setBounds(305, 26, 372, 465);
	// output_area.setEditorKit(kit);
	// output_area.setDocument(doc);
	// output_area.setText(initialText);
	// contentPane.add(output_area);

	JLabel HeaderLabel = new JLabel("Create Header:");
	HeaderLabel.setBounds(14, 26, 95, 16);
	contentPane.add(HeaderLabel);

	headerTextField = new JTextField();
	headerTextField.setText("Default Value");
	headerTextField.setBounds(121, 26, 134, 28);
	contentPane.add(headerTextField);
	headerTextField.setColumns(10);

	JButton HeaderButton = new JButton("Add Header");
	HeaderButton.setBounds(6, 54, 103, 29);
	contentPane.add(HeaderButton);

	final JComboBox<String> comboBox = new JComboBox<String>(new String[] {
		"<body>", "<h1>", "<p>" });
	comboBox.setBounds(159, 339, 134, 27);
	comboBox.setBounds(121, 55, 134, 27);
	contentPane.add(comboBox);

	Paragraph = new JTextArea();
	Paragraph.setText("Default Value");
	Paragraph.setColumns(10);
	Paragraph.setBounds(99, 130, 196, 179);
	contentPane.add(Paragraph);

	JLabel lblAppendParagraph = new JLabel("Content:");
	lblAppendParagraph.setBounds(11, 211, 76, 16);
	contentPane.add(lblAppendParagraph);

	JButton AppendParagraph = new JButton("Append Paragraph");
	AppendParagraph.setBounds(0, 338, 147, 29);
	contentPane.add(AppendParagraph);

	final JComboBox<String> comboBox_1 = new JComboBox<String>(
		new String[] { "<body>", "<h1>", "<p>" });
	comboBox_1.setBounds(159, 339, 134, 27);
	contentPane.add(comboBox_1);

	JButton ExportWebsiteButton = new JButton("Export Website");
	ExportWebsiteButton.setBounds(57, 451, 171, 29);
	contentPane.add(ExportWebsiteButton);

	JButton AddImageButton = new JButton("Add Image");
	AddImageButton.setBounds(57, 410, 161, 29);
	contentPane.add(AddImageButton);
	
	
	////////////////////////////////////
	// Action Listener Implementation //
	////////////////////////////////////
	
	
	HeaderButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		website.updateHeader(headerTextField.getText());
	    }
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
    }

    JMenuBar makeMenuBar() {

	JMenuItem ExitProgram = new JMenuItem("Exit Program");
	ExitProgram.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		dispose();
	    }
	});

	JMenuBar menuBar = new JMenuBar();
	JMenu Starter = new JMenu("Starter Menu");
	Starter.add(ExitProgram);

	menuBar.add(Starter);
	return menuBar;

    }

    public static void addHeader(String a) throws BadLocationException {
	String insertHeader = "<h1>" + a + "</h1>";
	// int body = doc.getElement("body");
	int pos = 2;
	doc.insertString(pos, insertHeader, null);
	output_area.setDocument(doc);
    }

}
