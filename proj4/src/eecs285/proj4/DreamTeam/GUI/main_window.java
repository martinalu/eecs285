package eecs285.proj4.DreamTeam.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class main_window extends JFrame
{

  private JPanel contentPane;
  private JTextField Header;
  private JTextArea Paragraph;

  /**
   * Create the frame.
   */
  public main_window()
  {
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
    
    JTextArea output_area = new JTextArea();
    output_area.setBounds(305, 26, 372, 465);
    contentPane.add(output_area);
    
    JLabel HeaderLabel = new JLabel("Create Header:");
    HeaderLabel.setBounds(14, 26, 95, 16);
    contentPane.add(HeaderLabel);
    
    Header = new JTextField();
    Header.setText("Default Value");
    Header.setBounds(121, 26, 134, 28);
    contentPane.add(Header);
    Header.setColumns(10);
    
    JButton HeaderButton = new JButton("Add Header");
    HeaderButton.setBounds(6, 54, 103, 29);
    contentPane.add(HeaderButton);
    
    JComboBox comboBox = new JComboBox();
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
    
    JComboBox comboBox_1 = new JComboBox();
    comboBox_1.setBounds(159, 339, 134, 27);
    contentPane.add(comboBox_1);
    
    JButton ExportWebsiteButton = new JButton("Export Website");
    ExportWebsiteButton.setBounds(57, 451, 171, 29);
    contentPane.add(ExportWebsiteButton);
    
    JButton AddImageButton = new JButton("Add Image");
    AddImageButton.setBounds(57, 410, 161, 29);
    contentPane.add(AddImageButton);
  }
  
  JMenuBar makeMenuBar(){

    JMenuItem ExitProgram = new JMenuItem("Exit Program");
    ExitProgram.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      { 
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
