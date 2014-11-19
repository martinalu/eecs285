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

//TODO: Convert to a layout that supports resizing.

public class main_window extends JFrame {

    private JPanel contentPane;
    private JTextField headerTextField;
    private JTextArea paragraphTextArea;
    private static WebpagePreview website = new WebpagePreview();

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

	JLabel HeaderLabel = new JLabel("Header Value:");
	HeaderLabel.setBounds(14, 26, 95, 16);
	contentPane.add(HeaderLabel);

	headerTextField = new JTextField();
	headerTextField.setText("Default Value");
	headerTextField.setBounds(121, 26, 134, 28);
	contentPane.add(headerTextField);
	headerTextField.setColumns(10);

	JButton headerButton = new JButton("Update");
	headerButton.setBounds(6, 54, 103, 29);
	contentPane.add(headerButton);

	// * Content Options - User can select ONE of TWO fonts. Georgia or
	// Arial.

	final JComboBox<String> headerContentComboBox = new JComboBox<String>(
		new String[] { "Arial", "Georgia" });
	headerContentComboBox.setBounds(159, 339, 134, 27);
	headerContentComboBox.setBounds(121, 55, 134, 27);
	contentPane.add(headerContentComboBox);

	paragraphTextArea = new JTextArea();
	paragraphTextArea.setText("Default Value");
	// Turn on word wrap.
	paragraphTextArea.setLineWrap(true);

	JScrollPane paragraphTextAreaContainer = new JScrollPane(
		paragraphTextArea);
	paragraphTextAreaContainer.setBounds(99, 130, 196, 179);
	contentPane.add(paragraphTextAreaContainer);

	JLabel paragraphLabel = new JLabel("Content:");
	paragraphLabel.setBounds(11, 211, 76, 16);
	contentPane.add(paragraphLabel);

	JButton paragraphButton = new JButton("Append Paragraph");
	paragraphButton.setBounds(0, 338, 147, 29);
	contentPane.add(paragraphButton);

	// * Content Options - User can select from ONE of TWO font weights. 8
	// and 14.

	final JComboBox<String> paragraphContentComboBox = new JComboBox<String>(
		new String[] { "8", "14" });
	paragraphContentComboBox.setBounds(159, 339, 134, 27);
	contentPane.add(paragraphContentComboBox);

	JButton ExportWebsiteButton = new JButton("Export Website");
	ExportWebsiteButton.setBounds(57, 451, 171, 29);
	contentPane.add(ExportWebsiteButton);

	JButton AddImageButton = new JButton("Add Image");
	AddImageButton.setBounds(57, 410, 161, 29);
	contentPane.add(AddImageButton);

	// //////////////////////////////////
	// Action Listener Implementation //
	// //////////////////////////////////

	headerButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		website.updateHeader(headerTextField.getText(),
			headerContentComboBox.getSelectedItem().toString());
	    }
	});

	paragraphButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		website.updateParagraph(paragraphTextArea.getText(),
			paragraphContentComboBox.getSelectedItem().toString());
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
}
