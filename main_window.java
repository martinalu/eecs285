package eecs285.proj4.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * This class creates the FedexSimulator JFrame and is the main GUI class.
 */

public class main_window extends JFrame
{
  /**
   * Constructor. This creates the JFrame that controls the simulation.
   */

  
  public main_window()
  {
    
    
    //making the main stuff
    super("This is the prototype");
    JPanel framePanel = new JPanel();
    framePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(framePanel);
    framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.X_AXIS));
    
    //separate it into two panels
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();    
    framePanel.add(leftPanel);
    framePanel.add(rightPanel);
    
    leftPanel.add(createFeaturesPanel());
    rightPanel.add(createOutputPanel());
    
    
    setJMenuBar(makeMenuBar());
    pack();

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
 
  JPanel createFeaturesPanel(){
    //base features panel
    JPanel FeaturesPanel = new JPanel();
    //FeaturesPanel.setLayout(new BoxLayout(FeaturesPanel, BoxLayout.Y_AXIS));
    FeaturesPanel.setLayout(new GridLayout(3,1));
    //smaller parts within this panel
    
    //header content
    JPanel HeaderContent = new JPanel(new FlowLayout());
    JLabel HeaderContentLabel = new JLabel("Header Content");
    JTextArea inputHeader = new JTextArea("Default Value");
    HeaderContent.add(HeaderContentLabel);
    HeaderContent.add(inputHeader);
    FeaturesPanel.add(HeaderContent);
    
    //paragraph content
    JPanel ParagraphContent = new JPanel(new FlowLayout());
    JLabel ParagraphContentLabel = new JLabel("Paragraph Content");
    JTextArea inputParagraph = new JTextArea("Default Value");
    ParagraphContent.add(ParagraphContentLabel);
    ParagraphContent.add(inputParagraph);
    FeaturesPanel.add(ParagraphContent);
    
    JButton addImage = new JButton("Add Image");
    FeaturesPanel.add(addImage);
    
    return FeaturesPanel;
  }
  
  JPanel createOutputPanel(){
    JPanel OutputPanel = new JPanel();
    //OutputPanel.setLayout(new BoxLayout(OutputPanel,BoxLayout.Y_AXIS));
    OutputPanel.setLayout(new BorderLayout());
    JLabel preview = new JLabel("------  PREVIEW  -------");
    OutputPanel.add(preview, BorderLayout.NORTH);
    JTextArea output = new JTextArea("");
    output.setSize(new Dimension(1000,500));
    output.setEditable(false);
    JScrollPane realOutput = new JScrollPane(output);
    realOutput.setSize(new Dimension(1000,500));
    OutputPanel.add(realOutput, BorderLayout.SOUTH);
    return OutputPanel;
  }
  
  JPanel createHeaderMiniPanel(){
    JPanel headerPanel = new JPanel();
    return headerPanel;
  }
  // ---------------------------------------------------------------------------
  
}
